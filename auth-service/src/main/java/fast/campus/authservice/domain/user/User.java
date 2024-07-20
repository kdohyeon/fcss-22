package fast.campus.authservice.domain.user;

import fast.campus.authservice.entity.user.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class User implements UserDetails {

    private final String userId;
    private final String password;

    public User(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.password = userEntity.getPassword();
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    public UserEntity toEntity() {
        return new UserEntity(userId, password);
    }
}
