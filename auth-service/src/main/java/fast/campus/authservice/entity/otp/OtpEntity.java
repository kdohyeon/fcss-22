package fast.campus.authservice.entity.otp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "otp")
@NoArgsConstructor
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String userId;

    @Column
    private String otpCode;

    public OtpEntity(String userId, String otpCode) {
        this.userId = userId;
        this.otpCode = otpCode;
    }

    public void renewOtp(String newOtpCode) {
        this.otpCode = newOtpCode;
    }
}
