package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository.UserRepository;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.CustomUserDetailsService;

import java.util.ArrayList;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
