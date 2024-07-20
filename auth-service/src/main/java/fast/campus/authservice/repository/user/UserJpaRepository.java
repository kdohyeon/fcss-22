package fast.campus.authservice.repository.user;

import fast.campus.authservice.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityByUserId(String userId);
}
