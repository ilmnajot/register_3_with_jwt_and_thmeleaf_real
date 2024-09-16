package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service;

import org.springframework.security.core.userdetails.UserDetails;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.entity.User;

import java.util.HashMap;
import java.util.Map;

public interface JWTService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);

}
