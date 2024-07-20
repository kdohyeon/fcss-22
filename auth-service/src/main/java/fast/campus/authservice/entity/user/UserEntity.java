package fast.campus.authservice.entity.user;

import fast.campus.authservice.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String userId;

    @Column
    private String password;

    public UserEntity(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User toDomain() {
        return new User(userId, password);
    }
}
