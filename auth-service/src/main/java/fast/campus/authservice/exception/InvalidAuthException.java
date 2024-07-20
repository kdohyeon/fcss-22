package fast.campus.authservice.exception;

public class InvalidAuthException extends RuntimeException {
    public InvalidAuthException() {
        super("사용자 ID 또는 비밀번호가 유효하지 않습니다.");
    }
}
