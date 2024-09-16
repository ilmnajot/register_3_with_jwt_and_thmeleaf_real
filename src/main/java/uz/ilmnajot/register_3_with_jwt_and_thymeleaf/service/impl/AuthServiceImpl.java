package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Role;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.RefreshTokenRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignInRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignUpRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.response.JwtResponse;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository.UserRepository;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.AuthService;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.JWTService;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public User signUp(SignUpRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);

    }

    @Override
    public JwtResponse signIn(SignInRequest request) {
        Authentication token = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwt);
        jwtResponse.setRefreshToken(refreshToken);
        return jwtResponse;
    }

    @Override
    public JwtResponse refreshToken(RefreshTokenRequest request) {
        String username = jwtService.extractUsername(request.getToken());
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        if (jwtService.isTokenValid(request.getToken(), user)){
            var jwt = jwtService.generateToken(user);
            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setToken(jwt);
            jwtResponse.setRefreshToken(request.getToken());
            return jwtResponse;
        }
        return null;
    }
}
