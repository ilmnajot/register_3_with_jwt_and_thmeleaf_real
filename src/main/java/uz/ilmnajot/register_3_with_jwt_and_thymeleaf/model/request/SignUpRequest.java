package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String username;

    private String password;
}
