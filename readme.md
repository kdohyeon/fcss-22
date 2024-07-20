## 모듈 소개
- `auth-service`
  - 사용자/인증을 담당하는 서버로 사용자 생성, 사용자 ID/PW 검증, OTP 검증을 담당한다. 
  - `8081` 포트로 애플리케이션이 구동된다.
- `movie-service`
  - 로그인 엔드포인트를 제공하는 서비스
  - `8080` 포트로 애플리케이션이 구동된다.

## 환경 설정

### 로컬 환경에서 MySQL 데이터베이스 구동하기

- MySQL 데이터베이스를 구동하기 위해서는 `docker-compose` 을 활용한다.
- `./docker-compose.yml` 파일을 실행한다.
- 파일을 실행시키는 방법은 아래 커맨드를 참고한다.

```bash
# docker-compose.yml 파일이 존재하는 경로에서 아래 커맨드를 실행
$ docker-compose -f ./docker-compose.yml up -d

### 전체 도커 컨테이너 목록 확인
$ docker ps -a
```

## `auth-service` 테스트 시나리오

### 1. 새로운 사용자 등록

- 비밀번호가 BCrypt 암호화가 되어 저장되어야 한다.

```http request
POST /api/v1/users
Content-Type: application/json

{
  "userId": "danny.kim",
  "password": "12345"
}
```

### 2. 사용자 인증

- 사용자 인증에 성공을 하면 OTP 가 발급된다.
- OTP 값은 6자리 숫자이다.

```http request
POST /api/v1/users/auth
Content-Type: application/json

{
  "userId": "danny.kim",
  "password": 12345
}
```

### 3. OTP 인증

- OTP 까지 비교한다.

```http request
POST /api/v1/otp/check
Content-Type: application/json

{
  "userId": "danny.kim",
  "otp": "123456"
}
```

## `movie-service` 테스트 시나리오

### 0. 준비

- `auth-service` 애플리케이션 구동
- `POST /api/v1/users` 엔드포인트를 사용하여 사용자 등록

### 1. 사용자 ID/PW 검증

- 사용자 ID 와 PW 를 헤더에 담아 `http://localhost:8080/login` 을 호출한다.
- 호출에 성공하면 데이터베이스의 OTP 테이블에 값이 생성된 것을 확인한다.

예시

```bash
$ curl -H "username:danny.kim" -H "password:12345" http://localhost:8080/login
```

### 2. 사용자 ID/OTP 검증

- 데이터베이스에 있는 OTP 와 사용자 ID 를 헤더에 담아 다시 `http://localhost:8080/login` 을 호출한다.
- 응답으로 반환되는 `Authorization` 을 확인한다.

예시

```bash
$ curl -v -H "username:danny.kim" -H "otp:412312" http://localhost:8080/login
```

### 3. `/api/v1/healthcheck` 엔드포인트 호출

- 2번 과정에서 전달받은 `Authorization` 으로 `/api/v1/healthcheck` 을 호출한다.

예시
```bash
$ curl -H "Authorization:eyJhbGciOiJIUzI1NiJ9
.eyJ1c2VybmFtZSI6ImRhbm55LmtpbSJ9
.mFdBB99e_8xsDRyXS1UxkQA4SItauqSVZzEcqsYM-qo" http://localhost:8080/api/v1/healthcheck
```
