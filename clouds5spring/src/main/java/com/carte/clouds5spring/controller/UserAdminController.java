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
import com.carte.clouds5spring.dto.RegisterRequest;
import com.carte.clouds5spring.dto.UserUpdateRequest;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.service.AuthService;
import com.carte.clouds5spring.service.UserService;
import com.carte.clouds5spring.dto.UserDto;
import com.carte.clouds5spring.dto.UserRoleDto;

@RestController
@RequestMapping("/admin/users")
public class UserAdminController 
{
    private final UserService userService;
    private final AuthService authService;

    public UserAdminController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {

        List<UserDto> users = userService.getAllUsers();

        return ResponseEntity.ok(
            ApiResponse.success(users)
        );
    }

    @PostMapping("/{id}/unblock")
    public ResponseEntity<ApiResponse<Object>> unblockUser(@PathVariable Integer id) {

        userService.unblockUser(id);

        return ResponseEntity.ok(
            ApiResponse.success(null)
        );
    }

    @GetMapping("/blocked")
    public ResponseEntity<ApiResponse<List<UserDto>>> getBlockedUsers() {

        List<User> users = userService.getBlockedUsers();
        List<UserDto> usersDto = users.stream()
            .map(User::toDto)
            .toList();
        return ResponseEntity.ok(
            ApiResponse.success(usersDto)
        );
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<Object>> updateMyProfile(
            @RequestBody UserUpdateRequest req,
            Authentication authentication) {

        String email = authentication.getName();

        userService.updateMyProfile(email, req);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateUser(
            @PathVariable Integer id,
            @RequestBody UserUpdateRequest req) {

        userService.updateUser(id, req);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> register(
            @RequestBody RegisterRequest req) 
    {
        authService.register(req);

        return ResponseEntity.ok(
            ApiResponse.success(null)
        );
    }
     


}
