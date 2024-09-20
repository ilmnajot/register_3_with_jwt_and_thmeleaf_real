package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Authority;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);

    Optional<User> findByRole(Authority authority);
}
