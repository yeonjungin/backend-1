# WHOAMI backend

# API Documentation
## 1. USER
### 1.1. 사용자 회원가입
#### 1.1.1. URL
POST :/signup

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
POST: /login

#### 1.2.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| userId | String | 회원 아이디 |
| password | String | 회원 비밀번호 |

#### 1.2.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |
| refreshToken | String | 갱신을 위한 토큰 |

#### 1.2.4. Http code
- 201 : Created
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그인 실패)

### 1.3. 사용자 로그인 (토큰 갱신 = 재발급)
#### 1.3.1. URL
PATCH :/reissue

#### 1.3.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |
| refreshToken | String | 갱신을 위한 토큰 |

#### 1.3.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |
| refreshToken | String | 갱신을 위한 토큰 |


#### 1.3.4. Http code
- 201 : Created
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)

### 1.4. 사용자 로그인 (토큰 검증) -> << 검증은 발급, 로그인 절차에 포함 >>


### 1.5. 사용자 로그아웃 (Access token : BlackList, Refresh token : delete in redis)
#### 1.5.1. URL
DELETE: /api/logout

#### 1.5.2. Request
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |
| refreshToken | String | 갱신을 위한 토큰 |

#### 1.5.3. Response
없음

#### 1.5.4. Http code
- 201 : Created
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)
- 401 : Unauthorized (로그아웃 실패)

### 1.6. 사용자 탈퇴
#### 1.6.1 URL
DELETE :/users/delete

#### 1.6.2. Request
1) Header

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

2) Body

없음

#### 1.6.3. Response
없음

#### 1.6.4. Http code
- 200 : Ok
- 409 : Conflict (리소스가 충돌 혹은 삭제 시 연관된 데이터가 남아있는 경우)

### 1.7. 사용자 비밀번호/휴대전화번호/이메일주소/알림여부 수정
#### 1.7.1. URL
PATCH: /users/update

#### 1.6.2. Request
1) Header

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

2) Body

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| password | String | 회원 비밀번호 |
| phone_num | String | 휴대전화번호 |
| email | String | 이메일 주소 |
| is_receive_notification | Boolean | 알림 설정 여부 |

#### 1.7.3. Response
없음

#### 1.7.4. Http code
- 201 : Created
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)
- 409 : Conflict (리소스가 충돌 혹은 삭제 시 연관된 데이터가 남아있는 경우)

### 1.8. 사용자 프로필 추가 및 수정
#### 1.8.1 URL
PATCH :/users/profile

#### 1.8.2. Request
1) Header

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

2) Body

| Parameter |  Type  | Description | Content Type    |
|-----------|:------:|-----------|-----------------|
| userId | String | 회원 아이디    | application/json |
| profile |  File  | 프로필 사진 파일 | image/jpg |

#### 1.8.3. Response
| Parameter  |  Type  | Description |
|------------|:------:|-------------|
| profileUrl | String | 프로필 경로      |

#### 1.8.4. Http code
- 201 : Created
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)

### 1.9. 사용자 프로필 삭제
#### 1.9.1 URL
PATCH :/users/profileDelete

#### 1.9.2. Request
1) Header

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

2) Body

 없음

#### 1.9.3. Response
없음

#### 1.9.4. Http code
- 200 : Ok
- 400 : Bad Request (애초에 parameter를 잘못 전달하거나 없는 경우)


### 1.10. 팔로잉 및 팔로워 추가
#### 1.10.1. URL
PUT :/users/follow

#### 1.10.2. Request
1) Header

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

2) Body

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| followerId | String | 팔로우 되는 유저 아이디 |

#### 1.10.3. Response
| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| followId | Number | 팔로우 번호 |
| followerId | String | 팔로우 되는 유저 아이디 |
| followingId | String | 팔로잉 하는 유저 아이디 |

#### 1.10.4. Http code
- 201 : Created
- 409 : Conflict (이미 존재하는 리소스(팔로우)를 중복 요청했을 경우)

### 1.11. 팔로잉 및 팔로워 삭제
#### 1.11.1. URL
DELETE :/users/unfollow

#### 1.11.2. Request
1) Header

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| accessToken | String | 인증을 위한 토큰 |

2) Body

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| followerId | String | 팔로우 되는 유저 아이디 |

#### 1.11.3. Response
없음

#### 1.11.4. Http code
- 200 : Ok
- 400 : Bad Request (애초에 팔로우 관계가 아닌 경우)

## 2. answer
### 2.1. 특정 user의 전체 답글 조회
#### 2.1.1. GET /answer/{memberId}/list
#### 2.1.2. Request
없음

#### 2.1.3. Response
[
    {
        "answerId": 1,
        "answerContents": "답글예시1입니다"
    },
    {
        "answerId": 2,
        "answerContents": "답글예시2입니다"
    }
]

#### 2.1.4. Http Code
- 200 : OK


### 2.2. 특정 답글 조회
#### 2.2.1. GET /answer/{answerId}
#### 2.2.2. Request
없음

#### 2.2.3. Response
{
    "answerId": 2,
    "answerContents": "답글예시2입니다"
}

#### 2.2.4. Http Code
- 200 : OK


### 2.3. 답글 내용 작성
#### 2.3.1. POST /answer
#### 2.3.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| answerContents |  String  | 답글 내용       |

#### 2.3.3. Response
없음

#### 2.3.4. Http Code
- 200 : OK
- 401 : Unauthorized (로그인하지 않은 사용자가 답변을 작성하려고 한 경우)


### 2.4. 답글 내용 수정
#### 2.4.1. PATCH /answer/{answerId}
#### 2.4.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| answerContents |  String  | 수정할 답글 내용  |

#### 2.4.3. Response
없음

#### 2.4.4. Http Code
- 200 : OK
- 403 : Forbidden (글작성자가 아닌 사용자가 답변을 수정하려고 한 경우)


### 2.5. 답글 내용 삭제
#### 2.5.1. DELETE /answer/{answerId}
#### 2.5.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body
없음

#### 2.5.3. Response
없음

#### 2.5.4. Http Code
- 200 : OK
- 403 : Forbidden (글작성자가 아닌 사용자가 답변을 삭제하려고 한 경우)


### 2.6. 답글 좋아요 추가
#### 2.6.1. POST /likeAnswer/{answerId}
#### 2.6.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body
없음

#### 2.6.3. Response
없음

#### 2.6.4. Http Code
- 200 : OK
- 400 : Bad Request (이미 좋아요 한 사용자가 동일한 답글에 좋아요를 중복해서 요청한 경우)


### 2.7. 답글 좋아요 삭제
#### 2.7.1. DELETE /likeAnswer/{answerId}
#### 2.7.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body
없음

#### 2.7.3. Response
없음

#### 2.7.4. Http Code
- 200 : OK
- 400 : Bad Request (이미 좋아요를 삭제한 사용자가 동일한 답글에 좋아요 삭제를 중복해서 요청한 경우)



## 3. comment
### 3.1. 특정 answer의 전체 댓글 조회
#### 3.1.1. GET /comment/{answerId}/comment/list
#### 3.1.2. Request
없음

#### 3.1.3. Response
[
    {
        "commentId": 1,
        "contents": "댓글수정예시1입니다."
    },
    {
        "commentId": 2,
        "contents": "댓글수정예시2입니다."
    }
]

#### 3.1.4. Http Code
- 200 : OK


### 3.2. 특정 댓글 조회
#### 3.2.1. GET /comment/{commentId}
#### 3.2.2. Request
없음

#### 3.2.3. Response
{
    "commentId": 1,
    "commentContents": "댓글예시1입니다"
}

#### 3.2.4. Http Code
- 200 : OK


### 3.3. 댓글 내용 작성
#### 3.3.1. POST /answer
#### 3.3.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| commentContents |  String  | 댓글 내용 |

#### 3.3.3. Response
없음

#### 3.3.4. Http Code
- 200 : OK
- 401 : Unauthorized (로그인하지 않은 사용자가 댓글을 작성하려고 한 경우)


### 3.4. 댓글 내용 수정
#### 3.4.1. PATCH /comment/{commentId}
#### 3.4.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| commentContents |  String  | 수정할 댓글 내용  |

#### 3.4.3. Response
없음

#### 3.4.4. Http Code
- 200 : OK
- 403 : Forbidden (글작성자가 아닌 사용자가 댓글을 수정하려고 한 경우)


### 3.5. 댓글 내용 삭제
#### 3.5.1. DELETE /comment/{commentId}
#### 3.5.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body
없음

#### 3.5.3. Response
없음

#### 3.5.4. Http Code
- 200 : OK
- 403 : Forbidden (글작성자가 아닌 사용자가 댓글을 삭제하려고 한 경우)


### 3.6. 댓글 좋아요 추가
#### 3.6.1. POST /likeComment/{commentId}
#### 3.6.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body
없음

#### 3.6.3. Response
없음

#### 3.6.4. Http Code
- 200 : OK
- 400 : Bad Request (이미 좋아요 한 사용자가 동일한 댓글에 좋아요를 중복해서 요청한 경우)


### 3.7. 댓글 좋아요 삭제
#### 3.7.1. DELETE /likeComment/{commentId}
#### 3.7.2. Request
1) Header
KEY : accessToken
VALUE : 로그인 시 얻은 인증 토큰

2) Body
없음

#### 3.7.3. Response
없음

#### 3.7.4. Http Code
- 200 : OK
- 400 : Bad Request (이미 좋아요를 삭제한 사용자가 동일한 댓글에 좋아요 삭제를 중복해서 요청한 경우)


## 4. QUESTION
### 4.1. 날짜별 질문 조회
#### 4.1.1. Url
GET : /question/{startDate}/{endDate}
 
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
| articleId |  Number  | 질문 번호       |
| contents  |  String  | 질문 내용       |
| date      | Datetime | 질문 날짜       |

#### 4.1.4. Http Code  
- 200 : 성공

### 4.2. 날짜별 질문 신규 작성 (admin만 가능)
#### 4.2.1. Url
POST : /question

#### 4.2.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| date      | Datetime | 질문 날짜       |
| contents  |  String  | 질문 내용       |

#### 4.2.3. Response

| Parameter  |   Type   | Description |
|------------|:--------:|-------------|
| questionId |  Number  | 생성된 질문 id   |

#### 4.2.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (admin 아닌 사용자로인한 권한없음)

### 4.3. 날짜별 질문 삭제 (admin만 가능) 
#### 4.3.1. Url
DELETE : /question/{questionId}


#### 4.3.2. Request

| Parameter  |   Type   | Description |
|------------|:--------:|-------------|
| questionId |  Number  | 질문 id       |

#### 4.3.3. Response
없음

#### 4.3.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (admin 아닌 사용자로인한 권한없음)

### 4.4. 날짜별 질문 수정 (admin만 가능) 
#### 4.4.1. Url
PATCH : /question/{questionId}

#### 4.4.2. Request

| Parameter  |   Type   | Description |
|------------|:--------:|-------------|
| questionId |  Number  | 질문 id       |
| date       | Datetime | 질문 날짜       |
| contents   |  String  | 질문 내용       |

#### 4.4.3. Response
없음

#### 4.4.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (admin 아닌 사용자로인한 권한없음)


## 5. GUEST BOOK
### 5.1. 방명록 조회 
#### 5.1.1. Url
GET : /guestbook/{startDate}/{endDate}

#### 5.1.2. Request
startDate와 endDate가 없을 경우 모든 data 반환

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| startDate | Datetime | 검색 날짜 시작일   |
| endDate   | Datetime | 검색 날짜 마지막일  |

#### 5.1.3. Response
data의 배열

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| articleId | Number | 질문 번호       |
| contents  | String | 질문 내용       |
| date      | Datetime | 질문 날짜       |

#### 5.1.4. Http Code
- 200 : 성공

### 5.2. 방명록 신규 작성
#### 5.2.1. Url
POST : /guestbook/

#### 5.2.2. Request

| Parameter |  Type  | Description              |
|-----------|:------:|--------------------------|
| userId    | String | 방명록 주인 id (쓴 사람 말고 받는사람) |
| contents  | String | 방명록 내용                   |

#### 5.2.3. Response

| Parameter   |  Type  | Description |
|-------------|:------:|-------------|
| guestbookId | Number | 방명록 id      |

#### 5.2.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)

### 5.3. 방명록 수정 (방명록 글 쓴이만 가능)
#### 5.3.1. Url
PATCH : /guestbook/{guestbookId}

#### 5.3.2. Request


| Parameter   |   Type   | Description |
|-------------|:--------:|-------------|
| guestbookId |  Number  | 방명록 id      |
| contents    |  String  | 방명록 내용      |


#### 5.3.3. Response
없음

#### 5.3.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (방명록 글 쓴이 아닌 사용자로인한 권한없음)

### 5.4. 방명록 삭제 (방명록 글 쓴이만 가능) 
#### 5.4.1. Url
DELETE : /guestbook/{guestbookId}

#### 5.4.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| guestbookId |  Number  | 방명록 id      |

#### 5.4.3. Response
없음

#### 5.4.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (방명록 글 쓴이 아닌 사용자로인한 권한없음)

### 5.5. 방명록 댓글 신규 등록 (방명록 주인만 (받은 사람) 가능) 
#### 5.5.1. Url
POST : /guestbook/comment/{guestbookId}

#### 5.5.2. Request

| Parameter   |  Type  | Description |
|-------------|:------:|-------------|
| guestbookId | Number | 방명록 id      |
| contents    | String | 댓글 내용       |

#### 5.5.3. Response

| Parameter |  Type  | Description |
|-----------|:------:|-------------|
| commentId | Number | 댓글 id       |

#### 5.5.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (방명록 주인 아닌 사용자로인한 권한없음)

### 5.6. 방명록 댓글 수정 (댓글 주인만 가능)
#### 5.6.1. Url
PATCH : /guestbook/comment/{commentId}

#### 5.6.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| commentId |  Number  | 댓글 id       |
| contents  |  String  | 댓글 내용       |

#### 5.6.3. Response
없음

#### 5.6.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (댓글 주인 아닌 사용자로인한 권한없음)


### 5.7. 방명록 댓글 삭제 (댓글 주인만 가능) 
#### 5.7.1. Url
DELETE : /guestbook/comment/{commentId}

#### 5.7.2. Request

| Parameter |   Type   | Description |
|-----------|:--------:|-------------|
| commentId |  Number  | 댓글 id       |

#### 5.7.3. Response
없음

#### 5.7.4. Http Code
- 200 : 성공
- 400 : Bad Request (parameter를 잘못 전달하거나 없음)
- 401 : Unauthorized (익명의 사용자로인한 권한없음)
- 403 : Frobidden (댓글 주인 아닌 사용자로인한 권한없음)
