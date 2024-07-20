package fast.campus.authservice.controller.auth;

import fast.campus.authservice.controller.request.SimpleOtpRequestBody;
import fast.campus.authservice.controller.request.SimpleUserRequestBody;
import fast.campus.authservice.service.otp.OtpService;
import fast.campus.authservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OtpService otpService;
    private final UserService userService;

    @PostMapping("/api/v1/users/auth")
    public String auth(@RequestBody SimpleUserRequestBody requestBody) {
        return userService.auth(requestBody.getUserId(), requestBody.getPassword());
    }

    @PostMapping("/api/v1/otp/check")
    public boolean checkOtp(@RequestBody SimpleOtpRequestBody requestBody) {
        return otpService.checkOtp(requestBody.getUserId(), requestBody.getOtp());
    }
}
