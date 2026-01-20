package com.carte.clouds5spring.controller;

import com.carte.clouds5spring.exception.NotFoundException;
import com.carte.clouds5spring.hutil.Hjson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HGlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException e) {
        String message = e.getMessage();
        if (message == null || message.isBlank()) {
            message = "Not found";
        }

        String safeMessage = escapeJsonString(message);
        String body = Hjson.formatJson("", "error", "404 Not Found: " + safeMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception e) {
        String message = e.getMessage();
        if (message == null || message.isBlank()) {
            message = e.getClass().getSimpleName();
        }

        String safeMessage = escapeJsonString(message);
        String body = Hjson.formatJson("", "error", "500 Internal Server Error: " + safeMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private static String escapeJsonString(String raw) {
        return raw
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n")
                .replace("\t", "\\t");
    }
}