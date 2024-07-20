package fast.campus.authservice.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OtpCodeUtilTest {
    @Test
    @DisplayName("6자리 숫자값이 나와야 함")
    public void test1() {
        // given & when
        String otp = OtpCodeUtil.generateOtpCode();

        // then
        // 1. 숫자값이어야 할 것
        Assertions.assertTrue(otp.chars().allMatch(Character::isDigit));

        // 2. 6자리 일 것
        Assertions.assertEquals(6, otp.length());
    }
}