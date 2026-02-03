package com.carte.clouds5spring.controller;

import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.service.SignalementSyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin/firebase-signalements")
public class SignalementSyncController 
{
    private final SignalementSyncService signalementSyncService;

    public SignalementSyncController(SignalementSyncService service) {
        this.signalementSyncService = service;
    }

    @GetMapping
    public ApiResponse<List<FirebaseRouteProblemeDTO>> getAllFromFirebase()
            throws Exception {

        return ApiResponse.success(
            signalementSyncService.syncFirebaseToLocal()
        );
    }
}
