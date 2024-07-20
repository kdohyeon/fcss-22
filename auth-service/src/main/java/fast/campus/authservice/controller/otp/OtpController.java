package fast.campus.authservice.controller.otp;

import fast.campus.authservice.controller.request.SimpleOtpRequestBody;
import fast.campus.authservice.service.otp.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OtpController {
    private final OtpService otpService;

    @PostMapping("/api/v1/otp/check")
    public boolean checkOtp(@RequestBody SimpleOtpRequestBody requestBody) {
        return otpService.checkOtp(requestBody.getUserId(), requestBody.getOtp());
    }
}
