package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.Role;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Authority;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.RefreshTokenRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignInRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignUpRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.response.JwtResponse;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository.RoleRepository;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.repository.UserRepository;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.AuthService;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.JWTService;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.utile.AppConstants;

import java.util.HashMap;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final RoleRepository roleRepository;



    @Override
    public User signUp(SignUpRequest request) {
        Role role = roleRepository.findByName(AppConstants.USER)
                .orElseThrow(() -> new EntityNotFoundException("no role found"));

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        return userRepository.save(user);

    }



    @Override
    public JwtResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
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
