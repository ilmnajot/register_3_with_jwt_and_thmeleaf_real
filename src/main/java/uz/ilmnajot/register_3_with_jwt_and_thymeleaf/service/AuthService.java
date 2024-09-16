package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service;

import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.RefreshTokenRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignInRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.SignUpRequest;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.response.JwtResponse;

public interface AuthService {
    User signUp(SignUpRequest request);

    JwtResponse signIn(SignInRequest request);

    JwtResponse refreshToken(RefreshTokenRequest request);
}
