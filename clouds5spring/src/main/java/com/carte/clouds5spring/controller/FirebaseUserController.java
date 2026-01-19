package com.carte.clouds5spring.controller;

import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.FirebaseUserDTO;
import com.carte.clouds5spring.service.FirebaseUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/firebase-users")
public class FirebaseUserController 
{
    private final FirebaseUserService firebaseUserService;

    public FirebaseUserController(FirebaseUserService firebaseUserService) {
        this.firebaseUserService = firebaseUserService;
    }

    @GetMapping
    public ApiResponse<List<FirebaseUserDTO>> getAllFirebaseUsers() throws Exception {
        return ApiResponse.success(
            firebaseUserService.getAllFirebaseUsersDTO()
        );
    }
}
