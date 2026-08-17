package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.dto.LoginRequest;
import com.example.gestioncommerciale.dto.LoginResponse;
import com.example.gestioncommerciale.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);


    }
    @GetMapping("/test")
    public String test() {
        return "JWT works!";
    }

}
