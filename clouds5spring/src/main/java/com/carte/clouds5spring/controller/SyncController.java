package com.carte.clouds5spring.controller;


import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
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

    public SyncController(SignalementSyncService signalementSyncService, UserSyncService userSyncService) {
        this.signalementSyncService = signalementSyncService;
        this.userSyncService = userSyncService;
    }


    @GetMapping("/firebase")
    @Operation(summary = "Synchronisation firebase postgres")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès")
    })
    public String getAllFromFirebase() throws Exception 
    {
        userSyncService.syncAndGetAllUsers();
        userSyncService.syncUsersToFirebase();
        signalementSyncService.syncAndGetAllSignalements();
        return "Synchronisation terminée";
    }
}
