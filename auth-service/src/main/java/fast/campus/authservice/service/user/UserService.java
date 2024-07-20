package fast.campus.authservice.service.user;

import fast.campus.authservice.domain.user.User;
import fast.campus.authservice.exception.InvalidAuthException;
import fast.campus.authservice.repository.auth.AuthRepository;
import fast.campus.authservice.service.encryption.EncryptService;
import fast.campus.authservice.service.otp.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final EncryptService encryptService;
    private final OtpService otpService;
    private final AuthRepository authRepository;

    public User createNewUser(String userId, String password) {
        return authRepository.createNewUser(new User(userId, password));
    }

    public String auth(String userId, String password) {
        User user = authRepository.getUserByUserId(userId);
        if (encryptService.matches(password, user.getPassword())) {
            return otpService.renewOtp(userId);
        }

        throw new InvalidAuthException();
    }
}
