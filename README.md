# WHOAMI backend

# API Documentation
## 1. USER
### 1.1. 

## 2. ARTICLE
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



