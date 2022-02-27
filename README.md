# WHOAMI backend

# API Documentation
## 1. USER
### 1.1. 

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



