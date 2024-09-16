package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {


    @GetMapping
    public HttpEntity<String> sayHello(){
        return ResponseEntity.ok("Hello World");
    }
}
