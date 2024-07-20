package fast.campus.authservice.controller.request;

import fast.campus.authservice.annotation.PasswordEncryption;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class EncryptedUserRequestBody {

    private final String userId;

    @PasswordEncryption
    private String password;

    @ConstructorProperties({"userId", "password"})
    public EncryptedUserRequestBody(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
