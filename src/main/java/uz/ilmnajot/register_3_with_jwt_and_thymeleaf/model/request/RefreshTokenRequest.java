package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String token;
}
