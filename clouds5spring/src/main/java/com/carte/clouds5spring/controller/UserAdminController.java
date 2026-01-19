package com.carte.clouds5spring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.UserUpdateRequest;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.service.UserService;

@RestController
@RequestMapping("/admin/users")
public class UserAdminController 
{
    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{id}/unblock")
    public ResponseEntity<ApiResponse<Object>> unblockUser(@PathVariable Integer id) {

        userService.unblockUser(id);

        return ResponseEntity.ok(
            ApiResponse.success(null)
        );
    }

    @GetMapping("/blocked")
    public ResponseEntity<ApiResponse<List<User>>> getBlockedUsers() {

        List<User> users = userService.getBlockedUsers();

        return ResponseEntity.ok(
            ApiResponse.success(users)
        );
    }

    @PutMapping("/users/me")
    public ResponseEntity<ApiResponse<Object>> updateMyProfile(
            @RequestBody UserUpdateRequest req,
            Authentication authentication) {

        String email = authentication.getName();

        userService.updateMyProfile(email, req);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<ApiResponse<Object>> updateUser(
            @PathVariable Integer id,
            @RequestBody UserUpdateRequest req) {

        userService.updateUser(id, req);

        return ResponseEntity.ok(ApiResponse.success(null));
    }



}
