package com.carte.clouds5spring.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuthResponse {
    // public UUID token;
    public String token;
    public LocalDateTime expiresAt;
}
