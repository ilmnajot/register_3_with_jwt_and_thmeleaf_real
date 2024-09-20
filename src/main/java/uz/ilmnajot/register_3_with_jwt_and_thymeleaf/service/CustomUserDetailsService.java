package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {
     UserDetails loadUserByUsername(String username);
}
