package fast.campus.authservice.service.encryption;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptService {

    private final PasswordEncoder passwordEncoder;

    public String encrypt(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matches(String source, String target) {
        return passwordEncoder.matches(source, target);
    }
}
