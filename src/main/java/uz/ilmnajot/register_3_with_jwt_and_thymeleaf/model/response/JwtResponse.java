package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.response;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;
    private String refreshToken;
}
