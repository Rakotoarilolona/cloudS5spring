package com.carte.clouds5spring.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
