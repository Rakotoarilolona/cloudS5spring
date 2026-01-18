package com.carte.clouds5spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.AuthResponse;
import com.carte.clouds5spring.dto.LoginRequest;
import com.carte.clouds5spring.dto.RegisterRequest;
import com.carte.clouds5spring.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService a) {
        this.authService = a;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> register(
            @RequestBody RegisterRequest req) {

        authService.register(req);

        return ResponseEntity.ok(
            ApiResponse.success(null)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest req) {

        AuthResponse authResponse = authService.login(req);

        return ResponseEntity.ok(
            ApiResponse.success(authResponse)
        );
    }

}
