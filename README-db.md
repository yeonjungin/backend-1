# 데이터베이스 설계

# DDL Example

## member
```sql
CREATE TABLE member (
  member_id bigint(10) NOT NULL AUTO_INCREMENT,
  user_id varchar(30) NOT NULL,
  password varchar(60) NOT NULL,
  name varchar(14) NOT NULL,
  registry_num varchar(13) NOT NULL,
  phone_num varchar(11) NOT NULL,
  email varchar(254) NOT NULL,
  is_receive_notification tinyint(4) NOT NULL DEFAULT '1',
  profile tinytext,
  is_admin varchar(10) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (member_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
```

```sql
INSERT INTO member (user_id, password, name, registry_num, phone_num, email) 
VALUES ('id','password','name','010103','01011112222','example@gmail.com');
```
## visit
 
boolean 타입일 경우 default 을 지정할 경우 0 (false) 또는 1 (true) 로 지정해야 한다.
boolean은 tinyint(1)으로 표현되기도 한다.
```sql
CREATE TABLE visit (
  guestbook_id bigint(10) NOT NULL AUTO_INCREMENT,
  contents text,
  created_at datetime,
  updated_at datetime,
  owner_id bigint(10),
  writer_id bigint(10),
  is_updated boolean DEFAULT 0,
  FOREIGN KEY (owner_id) REFERENCES member (member_id),
  FOREIGN KEY (writer_id) REFERENCES member (member_id),
  PRIMARY KEY (guestbook_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
```


# DML Example

## 개인별 방명록 조회

특정 사용자의 방명록을 조회할 수 있도록 SQL 문을 작성해야 했다.
페이지1, 페이지2, 페이지3 등으로 한 페이지에 방문글 5개를 보여주기로 정했다.
이를 위해서는 페이징 기능이 필요하다.

먼저 최신 업데이트 날짜가 빠른 순으로 정렬해 방명록 글을 가져오는 쿼리문을 작성했다.
```sql
SELECT V.owner_id,V.contents, V.created_at,V.updated_at, M.name AS "username"
FROM
(SELECT guestbook_id,contents,created_at,updated_at,owner_id,writer_id
FROM visit ORDER BY updated_at DESC) V 
INNER JOIN member M
ON V.writer_id = M.member_id);
```

쿼리문이 복잡해 보여 재사용을 위한 뷰 v_visit 을 만들어보았다.
복잡한 질의를 뷰로 생성함으로써 관련 질의를 단순하게 작성할 수 있다.
조인만 처리하기 위해 매개변수 없이 뷰를 생성한 후 쿼리 할 때 동적 매개 변수를 적용하기로 했다.
```sql
DROP VIEW IF EXISTS v_visit;
CREATE VIEW v_visit
AS (SELECT V.owner_id,V.contents, V.created_at,V.updated_at, M.name AS "username"
FROM
(SELECT guestbook_id,contents,created_at,updated_at,owner_id,writer_id
FROM visit ORDER BY updated_at DESC) V 
INNER JOIN member M
ON V.writer_id = M.member_id);
``` 

다음으로 페이징 처리를 위한 SQL 을 작성했다.
MySQL 에서는 ORACLE 과 달리 ROWNUM을 통해서 검색된 결과에 자동적으로 순번이 들어가지 않는다.
따라서 WHERE 조건에 ROWNUM 변수를 초기화시켜 카운트가 증가할 때마다 ROWNUM 에 1 씩 증가시켜 순번을 얻었다.
아래는 owner_id 가 10 인 사용자의 방명록 중 2페이지 (6번 ~ 10번) 의 글을 가져오는 쿼리문을 작성한 것이다.
```sql
SELECT * FROM  (
SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, V.* 
FROM v_visit V, (SELECT @ROWNUM :=0) TMP
WHERE V.owner_id = 10
) TB
WHERE TB.ROWNUM BETWEEN 6 AND 10;
```

# PL/SQL

페이징 테스트를 하기 위해 많은 양의 더미 데이터가 필요했다.
따라서 프로시저를 생성해 더미 데이터를 삽입하기로 결정했다.
프로시저를 정의할 때 내부에 세미콜론을 사용하기 때문에 문법의 끝을 나타내는 구분자를 재정의할 필요성을 느꼈다.
DELIMITER 를 $$ 로 설정해 변경하고 프로시저 정의 후 다시 세미콜론 ; 로 변경해주었다.

임의의 사용자를 만드는 프로시저를 정의한다.
```sql
DELIMITER $$
DROP PROCEDURE IF EXISTS p_member_insert$$

CREATE PROCEDURE p_member_insert()
BEGIN
    DECLARE i INT DEFAULT 1;
        
    WHILE i <= 100 DO
        INSERT INTO member (user_id, password, name, registry_num, phone_num, email) 
        VALUES (CONCAT('id',i),'password',CONCAT('name',i),'0011290000000','01011111111','example@gmail.com');
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
```

임의의 방명록을 만드는 프로시저를 정의한다.
앞에서 생성한 member 의 아이디의 최대값이 n 이라면 마찬가지로 글 작성자의 아이디의 최대값은 n 이하여야 한다.
RAND 함수를 통해 0 에서 1 사이 임의의 수를 만든 후 n 을 곱하고 FLOOR 함수를 통해 소수점을 버려 0 ~ n-1 까지 숫자를 임의로 뽑는다.
그 다음 1을 더해 1 ~ n 범위를 충족시킨다. 
```sql
SELECT FLOOR(RAND() * 100) + 1 FROM DUAL;
```

방명록을 만드는 프로시저를 정의한다.
DECLARE continue handler for SQLEXCEPTION 을 이용해 에러가 발생하더라도 다음 쿼리를 계속 실행할 수 있도록 했다.
```sql
DELIMITER $$
DROP PROCEDURE IF EXISTS p_visit_insert$$
 
CREATE PROCEDURE p_visit_insert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE d_owner_id BIGINT(10);
    DECLARE d_writer_id BIGINT(10);
    DECLARE d_member_max_id INTEGER;

    /* SQLEXCEPTION이 발생한 경우 err에 -1을 set */
    DECLARE err INT default '0';
    DECLARE continue handler for SQLEXCEPTION set err = -1;

    SET d_member_max_id = (SELECT MAX(member_id) FROM member);
        
    WHILE i <= 2000 DO
        SET d_owner_id = FLOOR(RAND() * d_member_max_id) + 1;
        SET d_writer_id = FLOOR(RAND() * d_member_max_id) + 1;
        IF d_owner_id = d_member_max_id AND d_owner_id = d_writer_id THEN
            SET d_writer_id = d_writer_id - 1;
        END IF;
        IF d_owner_id = 1 AND d_owner_id = d_writer_id THEN
            SET d_writer_id = d_writer_id + 1;
        END IF;
        /* 트랜잭션 시작 */
        START TRANSACTION;

        INSERT INTO visit (contents, created_at, updated_at, owner_id, writer_id) 
        VALUES ('test', NOW(), NOW(), d_owner_id, d_writer_id);
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
```

프로시저를 실행한다.
```sql
CALL  p_member_insert;
CALL  p_visit_insert;
```

