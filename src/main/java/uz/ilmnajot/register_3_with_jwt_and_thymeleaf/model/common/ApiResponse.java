package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.common;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;

    private boolean success;

    private Object info;

    private HttpStatus status;


    public ApiResponse(String message, boolean success, Object info) {
        this.message = message;
        this.success = success;
        this.info = info;
    }

    public ApiResponse(boolean success, Object info, HttpStatus status) {
        this.success = success;
        this.info = info;
        this.status = status;
    }
}
