package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repositoruy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
