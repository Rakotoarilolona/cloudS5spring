package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.AuthResponse;
import com.carte.clouds5spring.dto.LoginRequest;
import com.carte.clouds5spring.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);

}
