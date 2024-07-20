package fast.campus.authservice.controller.user;

import fast.campus.authservice.controller.request.EncryptedUserRequestBody;
import fast.campus.authservice.domain.user.User;
import fast.campus.authservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public User createNewUser(@RequestBody EncryptedUserRequestBody requestBody) {
        return userService.createNewUser(requestBody.getUserId(), requestBody.getPassword());
    }
}
