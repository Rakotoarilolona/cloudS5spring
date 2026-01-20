package com.carte.clouds5spring.controller;

import com.carte.clouds5spring.service.UserSyncService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carte.clouds5spring.dto.ApiResponse;

@RestController
@RequestMapping("/admin/sync")
public class UserSyncController 
{
    private final UserSyncService userSyncService;

    public UserSyncController(UserSyncService userSyncService) {
        this.userSyncService = userSyncService;
    }

    @PostMapping("/firebase-users")
    public ApiResponse<Object> syncUsers() throws Exception  {
        return ApiResponse.success(userSyncService.syncAndGetAllUsers());
    }

    @GetMapping("/users")
    public ApiResponse <Object> syncUserPostgres() throws Exception {
        return ApiResponse.success(userSyncService.syncUsersToFirebase());
    }
}
