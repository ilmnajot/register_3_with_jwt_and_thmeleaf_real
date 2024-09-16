package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Role;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository.UserRepository;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {

            Optional<User> adminByRole = userRepository.findByRole(Role.ADMIN);
            if (adminByRole.isEmpty()) {
                userRepository.save(
                        User
                                .builder()
                                .firstName("admin")
                                .lastName("admin")
                                .username("admin@gmail.com")
                                .role(Role.ADMIN)
                                .password(passwordEncoder.encode("admin"))
                                .build());
            } else {
                userRepository.save(
                        User
                                .builder()
                                .firstName("user")
                                .lastName("user")
                                .username("user@gmail.com")
                                .role(Role.USER)
                                .password(passwordEncoder.encode("user"))
                                .build());
            }
        }
    }
}
