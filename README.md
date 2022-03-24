# WHOAMI backend

# API Documentation
## 1. USER
### 1.1. 사용자 회원가입
#### 1.1.1. URL
POST :/users

#### 1.1.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |
| password | String | 회원 비밀번호 |
| name | String | 이름 |
| registry_num  | String | 주민번호 |
| phone_num | String | 휴대전화번호 |
| email | String | 이메일 주소 |
| is_receive_notification | Boolean | 알림 설정 여부 |
| is_admin | Boolean | 관리자 여부 |
| profile | String | 프로필 사진 저장 경로 |

#### 1.1.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |
| email | String | 이메일 주소 |
| name | String | 이름 |

#### 1.1.4 Http code
- 201 : Created
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)
- 409 : Conflict (이미 존재하는 리소스(닉네임)를 중복 요청했을 경우)

### 1.2. 사용자 로그인 (토큰 발급)  
#### 1.2.1. URL
POST: /users/login/{userId}/{password}

#### 1.2.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |
| password | String | 회원 비밀번호 |

#### 1.2.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| refreshToken | String | 갱신을 위한 토큰 |
| accessToken | String | 인증을 위한 토큰 |

#### 1.2.4. Http code
- 201 : Created 
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인 실패)

### 1.3. 사용자 로그인 (토큰 갱신)  
#### 1.3.1. URL
PATCH :/users/login/refreshment/{refreshToken}/{accessToken}

#### 1.3.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| refreshToken | String | 갱신을 위한 토큰 |

#### 1.3.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

#### 1.3.4. Http code
- 201 : Created 
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)

### 1.4. 사용자 로그인 (토큰 검증)  
#### 1.4.1. URL
POST: /users/login/verification/{accessToken}

#### 1.4.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

#### 1.4.3. Response
없음

#### 1.4.4. Http code
- 201 : Created 
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인 실패)

### 1.5. 사용자 탈퇴 
#### 1.5.1 URL
DELETE :/users/{userId}

#### 1.5.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |

#### 1.5.3. Response
없음

#### 1.5.4. Http code
- 200 : Ok
- 409 : Conflict (리소스가 충돌 혹은 삭제 시 연관된 데이터가 남아있는 경우)

### 1.6. 사용자 비밀번호/휴대전화번호/이메일주소/알림여부 수정
#### 1.6.1. URL
PATCH: /users/{userId}

#### 1.6.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |
| password | String | 회원 비밀번호 |
| phone_num | String | 휴대전화번호 |
| email | String | 이메일 주소 |
| is_receive_notification | Boolean | 알림 설정 여부 |

#### 1.6.3. Response
없음

#### 1.6.4. Http code
- 201 : Created 
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)

### 1.7. 사용자 프로필 추가, 수정, 삭제
#### 1.7.1 URL
PATCH :/users/{userId}/{profile}

#### 1.7.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |
| profile | String | 프로필 사진 저장 경로 |

#### 1.7.3. Response
없음

#### 1.7.4. Http code
- 201 : Created 
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)

### 1.8. 사용자 리스트 조회 (admin만 가능)
#### 1.8.1. URL
GET :/users/list/{userId}

#### 1.8.2. Request
userId가 없을 경우 모든 data 반환
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |

#### 1.8.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |
| name | String | 이름 |
| phone_num | String | 휴대전화번호 |
| email | String | 이메일 주소 |

#### 1.8.4. Http code
- 200 : Ok
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (익명의 사용자의 접근을 차단함)
- 403 : Forbidden (관리자 권한이 없음)

### 1.9. 팔로잉 및 팔로워 추가
#### 1.9.1. URL
PUT :/follow/{followedId}/{followingId}

#### 1.9.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| followedId | String | 팔로우 되는 유저 아이디 |
| followingId | String | 팔로잉 하는 유저 아이디 |

#### 1.9.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| followId | Number | 팔로우 번호 |
| followedId | String | 팔로우 되는 유저 아이디 |
| followingId | String | 팔로잉 하는 유저 아이디 |

#### 1.9.4. Http code
- 201 : Created
- 401 : Unauthorized (로그인이 되어 있지 않아 팔로우 기능에 대한 접근을 차단함)

### 1.10. 팔로잉 및 팔로워 삭제
#### 1.10.1. URL
DELETE :/follow/{followedId}/{followingId}

#### 1.10.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| followedId | String | 팔로우 되는 유저 아이디 |
| followingId | String | 팔로잉 하는 유저 아이디 |

#### 1.10.3. Response
없음

#### 1.10.4. Http code
- 200 : Ok
- 401 : Unauthorized (로그인이 되어 있지 않아 팔로우 기능에 대한 접근을 차단함)

## 2. ARTICLE
### 2.1. 답글 조회
#### 2.1.1. GET /answers/list/{userId}
#### 2.1.2. Request
userId가 없을 경우 모든 data 반환
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId |  Number  | 회원 번호       |

#### 2.1.3. Response

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| answerId |  Number  | 답글 번호       |
| updatedAt |  Datetime  | 날짜       |
| userId |  Number  | 회원 번호       |
| answerContents |  String  | 답글 내용       |
| tagId |  Number  | 태그 번호       |
| tagContents |  Number  | 태그 내용       |

#### 2.1.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)

### 2.2. 답글 내용 작성
#### 2.2.1. POST /answers
#### 2.2.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId |  Number  | 회원 번호       |
| contents |  String  | 답글 내용       |
| tagContents |  Number  | 태그 내용       |

#### 2.2.3. Response

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| answerId |  Number  | 답글 번호       |
| updatedAt |  Datetime  | 날짜       |
| userId |  Number  | 회원 번호       |
| answerContents |  String  | 답글 내용       |
| tagId |  Number  | 태그 번호       |
| tagContents |  Number  | 태그 내용       |

#### 2.2.4. Http Code
- 201 : Created
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 답변을 작성하려고 한 경우)

### 2.3. 답글 내용 수정
#### 2.3.1. PATCH /answers/{answer}
#### 2.3.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| answerId |  Number  | 답글 번호       |
| answerContents |  String  | 답글 내용       |
| tagContents |  Number  | 태그 내용       |

#### 2.3.3. Response

없음

#### 2.3.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 답변을 수정하려고 한 경우)
- 403 : Forbidden (글작성자가 아닌 사용자가 답변을 수정하려고 한 경우)

### 2.4. 답글 내용 삭제
#### 2.4.1. DELETE /answers/{answerId}
#### 2.4.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| answerId |  Number  | 답글 번호       |

#### 2.4.3. Response

없음

#### 2.4.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 답변을 삭제하려고 한 경우)
- 403 : Forbidden (글작성자가 아닌 사용자가 답변을 삭제하려고 한 경우)

### 2.5. 답글 좋아요 추가
#### 2.5.1. POST /answers/likes
#### 2.5.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| answerId |  Number  | 답글 번호       |
| userId |  Number  | 회원 번호       |

#### 2.5.3. Response

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| likeId |  Number  | 좋아요 번호       |
| answerId |  Number  | 답글 번호       |
| userId |  Number  | 회원 번호       |


#### 2.5.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 좋아요를 누른 경우)

### 2.6. 답글 좋아요 삭제
#### 2.6.1. DELETE /answers/likes/{likeId}
#### 2.6.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| likeId |  Number  | 좋아요 번호       |

#### 2.6.3. Response

없음

### 2.7. 댓글 좋아요 추가
#### 2.7.1. POST /comments/likes
#### 2.7.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| commentsId |  Number  | 댓글 번호       |
| userId |  Number  | 회원 번호       |

#### 2.7.3. Response

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| likeId |  Number  | 좋아요 번호       |
| commentsId |  Number  | 댓글 번호       |
| userId |  Number  | 회원 번호       |


#### 2.7.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 좋아요를 누른 경우)

### 2.8. 댓글 좋아요 삭제
#### 2.8.1. DELETE /comments/likes/{likeId}
#### 2.8.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| likeId |  Number  | 좋아요 번호       |

#### 2.8.3. Response

없음

#### 2.8.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)

### 2.9. 댓글 작성
#### 2.9.1. POST /comments
#### 2.9.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId |  Number  | 회원 번호       |
| comments |  String  | 댓글 내용       |
| answerId |  Number  | 답글 번호       |
| upperCommentsId |  String  | 윗댓글 번호       |

#### 2.9.3. Response

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| commentsId |  Number  | 댓글 번호       |
| updatedAt |  Datetime  | 날짜       |
| userId |  Number  | 회원 번호       |
| answerId |  Number  | 답글 번호       |
| upperCommentsId |  String  | 윗댓글 번호       |

#### 2.9.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 댓글을 작성하려고 한 경우)

### 2.10. 댓글 삭제
#### 2.10.1. DELETE /comments/{commentId}
#### 2.10.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| commentsId |  Number  | 댓글 번호       |

#### 2.10.3. Response

없음

#### 2.10.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 댓글을 삭제하려고 한 경우)
- 403 : Forbidden (글작성자가 아닌 사용자가 댓글을 삭제하려고 한 경우)

### 2.10. 댓글 수정
#### 2.10.1 PATCH /comments/{commentId}
#### 2.10.2. Request

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId |  Number  | 회원 번호       |
| comments |  String  | 댓글 내용       |
| answerId |  Number  | 답글 번호       |
| upperCommentsId |  String  | 윗댓글 번호       |

#### 2.10.3. Response

없음

#### 2.10.4. Http Code
- 200 : OK
- 400 : Bad Request (parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인하지 않은 사용자가 댓글을 수정하려고 한 경우)
- 403 : Forbidden (글작성자가 아닌 사용자가 댓글을 수정하려고 한 경우)

## 3. QUESTION
### 3.1. 날짜별 질문 조회
#### 3.1.1. Url
GET : /question/{startDate}/{endDate}
 
#### 3.1.2. Request  
startDate와 endDate가 없을 경우 모든 data 반환

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| startDate | Datetime | 검색 날짜 시작일   |
| endDate   | Datetime | 검색 날짜 마지막일  |

#### 3.1.3. Response  
data의 배열  

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| articleId |  Number  | 질문 번호       |
| contents  |  String  | 질문 내용       |
| date      | Datetime | 질문 날짜       |

#### 3.1.4. Http Code  
- 200 : 성공

### 3.2. 날짜별 질문 신규 작성 (admin만 가능)
#### 3.2.1. Url
POST : /question

#### 3.2.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| date      | Datetime | 질문 날짜       |
| contents  |  String  | 질문 내용       |

#### 3.2.3. Response

| Parameter  |   Type   | Description |
|------------|:--------:|-------------|
| questionId |  Number  | 생성된 질문 id   |

#### 3.2.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (admin 아닌 사용자로인한 권한없음)

### 3.3. 날짜별 질문 삭제 (admin만 가능) 
#### 3.3.1. Url
DELETE : /question/{questionId}


#### 3.3.2. Request

| Parameter  |   Type   | Description |
|------------|:--------:|-------------|
| questionId |  Number  | 질문 id       |

#### 3.3.3. Response
없음

#### 3.3.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (admin 아닌 사용자로인한 권한없음)

### 3.4. 날짜별 질문 수정 (admin만 가능) 
#### 3.4.1. Url
PATCH : /question/{questionId}

#### 3.4.2. Request

| Parameter  |   Type   | Description |
|------------|:--------:|-------------|
| questionId |  Number  | 질문 id       |
| date       | Datetime | 질문 날짜       |
| contents   |  String  | 질문 내용       |

#### 3.4.3. Response
없음

#### 3.4.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (admin 아닌 사용자로인한 권한없음)


## 4. GUEST BOOK
### 4.1. 방명록 조회 
#### 4.1.1. Url
GET : /guestbook/{startDate}/{endDate}

#### 4.1.2. Request
startDate와 endDate가 없을 경우 모든 data 반환

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| startDate | Datetime | 검색 날짜 시작일   |
| endDate   | Datetime | 검색 날짜 마지막일  |

#### 4.1.3. Response
data의 배열

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| articleId | Number | 질문 번호       |
| contents  | String | 질문 내용       |
| date      | Datetime | 질문 날짜       |

#### 4.1.4. Http Code
- 200 : 성공

### 4.2. 방명록 신규 작성
#### 4.2.1. Url
POST : /guestbook/


#### 4.2.2. Request

| Parameter |  Type  | Description              |
|-----------|:------:|--------------------------|
| userId    | String | 방명록 주인 id (쓴 사람 말고 받는사람) |
| contents  | String | 방명록 내용                   |

#### 4.2.3. Response

| Parameter   |  Type  | Description |
|-------------|:------:|-------------|
| guestbookId | Number | 방명록 id      |

#### 4.2.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)

### 4.3. 방명록 수정 (방명록 글 쓴이만 가능)
#### 4.3.1. Url
PATCH : /guestbook/{guestbookId}

#### 4.3.2. Request


| Parameter   |   Type   | Description |
|-------------|:--------:|-------------|
| guestbookId |  Number  | 방명록 id      |
| contents    |  String  | 방명록 내용      |


#### 4.3.3. Response
없음

#### 4.3.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (방명록 글 쓴이 아닌 사용자로인한 권한없음)

### 4.4. 방명록 삭제 (방명록 글 쓴이만 가능) 
#### 4.4.1. Url
DELETE : /guestbook/{guestbookId}

#### 4.4.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| guestbookId |  Number  | 방명록 id      |

#### 4.4.3. Response
없음

#### 4.4.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (방명록 글 쓴이 아닌 사용자로인한 권한없음)

### 4.5. 방명록 댓글 신규 등록 (방명록 주인만 (받은 사람) 가능) 
#### 4.5.1. Url
POST : /guestbook/comment/{guestbookId}


#### 4.5.2. Request

| Parameter   |  Type  | Description |
|-------------|:------:|-------------|
| guestbookId | Number | 방명록 id      |
| contents    | String | 댓글 내용       |

#### 4.5.3. Response

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| commentId | Number | 댓글 id       |

#### 4.5.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (방명록 주인 아닌 사용자로인한 권한없음)

### 4.6. 방명록 댓글 수정 (댓글 주인만 가능)
#### 4.6.1. Url
PATCH : /guestbook/comment/{commentId}

#### 4.6.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| commentId |  Number  | 댓글 id       |
| contents  |  String  | 댓글 내용       |

#### 4.6.3. Response
없음

#### 4.6.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (댓글 주인 아닌 사용자로인한 권한없음)


### 4.7. 방명록 댓글 삭제 (댓글 주인만 가능) 
#### 4.7.1. Url
DELETE : /guestbook/comment/{commentId}

#### 4.7.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| commentId |  Number  | 댓글 id       |

#### 4.7.3. Response
없음

#### 4.7.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (댓글 주인 아닌 사용자로인한 권한없음)



