package com.carte.clouds5spring.controller;


import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.service.FirebaseNotificationService;
import com.carte.clouds5spring.service.SignalementSyncService;
import com.carte.clouds5spring.service.UserSyncService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin/sync")
public class SyncController 
{
    private final SignalementSyncService signalementSyncService;
    private final UserSyncService userSyncService;
    private final FirebaseNotificationService firebaseNotificationService;

    public SyncController(SignalementSyncService signalementSyncService, UserSyncService userSyncService, FirebaseNotificationService firebaseNotificationService) {
        this.signalementSyncService = signalementSyncService;
        this.userSyncService = userSyncService;
        this.firebaseNotificationService = firebaseNotificationService;
    }


    @GetMapping("/firebase")
    @Operation(summary = "Synchronisation firebase postgres")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès")
    })
    public String getAllFromFirebase() throws Exception 
    {
        userSyncService.syncUsersToFirebase();
        userSyncService.syncUsersFromFirebase();
        // signalementSyncService.syncLocalToFirebase();
        signalementSyncService.syncLocalToFirebaseAndNotify();
        signalementSyncService.syncFirebaseToLocal();
        return "Synchronisation terminée";
    }

    
}
