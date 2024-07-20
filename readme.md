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