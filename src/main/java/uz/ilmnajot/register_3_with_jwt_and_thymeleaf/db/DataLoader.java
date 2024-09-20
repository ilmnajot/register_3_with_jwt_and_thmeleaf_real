package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.Role;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Authority;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository.RoleRepository;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository.UserRepository;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.utile.AppConstants;

import java.util.Arrays;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {

            Role user = roleRepository.save(
                    Role
                            .builder()
                            .name(AppConstants.USER)
                            .authorities(
                                    Arrays.asList(
                                            Authority.GET_USERS,
                                            Authority.GET_USER
                                    )
                            )
                            .build());
            Role admin = roleRepository.save(
                    Role.builder()
                            .name(AppConstants.ADMIN)
                            .authorities(Arrays.asList(
                                    Authority.GET_USERS,
                                    Authority.GET_USER,
                                    Authority.DELETE_USER,
                                    Authority.UPDATE_USER,
                                    Authority.ADD_USER
                            ))
                            .build());

            userRepository.save(
                    User
                            .builder()
                            .firstName("admin")
                            .lastName("admin")
                            .username("admin@gmail.com")
                            .role(admin)
                            .password(passwordEncoder.encode("admin"))
                            .build());

            userRepository.save(
                    User
                            .builder()
                            .firstName("user")
                            .lastName("user")
                            .username("user@gmail.com")
                            .role(user)
                            .password(passwordEncoder.encode("user"))
                            .build());
        }
    }
}

