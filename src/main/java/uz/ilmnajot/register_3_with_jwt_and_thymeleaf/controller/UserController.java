package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.common.ApiResponse;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.model.request.UserForm;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/register")
    public HttpEntity<ApiResponse> registerGC(@RequestBody UserForm form){

    }

}
