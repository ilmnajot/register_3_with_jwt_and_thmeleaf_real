package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
}
