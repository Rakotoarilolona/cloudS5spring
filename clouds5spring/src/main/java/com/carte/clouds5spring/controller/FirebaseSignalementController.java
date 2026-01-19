package com.carte.clouds5spring.controller;

import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.service.FirebaseSignalementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin/firebase-signalements")
public class FirebaseSignalementController 
{
    private final FirebaseSignalementService firebaseSignalementService;

    public FirebaseSignalementController(FirebaseSignalementService service) {
        this.firebaseSignalementService = service;
    }

    @GetMapping
    public ApiResponse<List<FirebaseRouteProblemeDTO>> getAllFromFirebase()
            throws Exception {

        return ApiResponse.success(
            firebaseSignalementService.getAllSignalementsDTO()
        );
    }
}
