package fast.campus.authservice.service.otp;

import fast.campus.authservice.repository.auth.AuthRepository;
import fast.campus.authservice.util.OtpCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final AuthRepository authRepository;

    public boolean checkOtp(String userId, String sourceOtp) {
        String targetOtp = authRepository.getOtp(userId);
        return targetOtp.equals(sourceOtp);
    }

    public String renewOtp(String userId) {
        String newOtp = OtpCodeUtil.generateOtpCode();
        authRepository.upsertOtp(userId, newOtp);
        return newOtp;
    }
}
