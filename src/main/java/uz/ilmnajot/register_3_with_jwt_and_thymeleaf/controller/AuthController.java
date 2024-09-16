package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.RefreshTokenRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignInRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignUpRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.response.JwtResponse;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public HttpEntity<User> registerUser(@RequestBody SignUpRequest request) {
        User user = authService.signUp(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signIn")
    public HttpEntity<JwtResponse> loginUser(@RequestBody SignInRequest request) {
        JwtResponse jwtResponse = authService.signIn(request);
        return jwtResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/refreshToken")
    public HttpEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        JwtResponse jwtResponse = authService.refreshToken(request);
        return jwtResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
