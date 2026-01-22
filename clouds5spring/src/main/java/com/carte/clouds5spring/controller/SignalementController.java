package com.carte.clouds5spring.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.service.RouteProblemeService;
import com.carte.clouds5spring.service.SignalementSyncService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/signalements")
public class SignalementController {

    private final RouteProblemeService routeProblemeService;
    private final SignalementSyncService signalementSyncService;

    public SignalementController(RouteProblemeService routeProblemeService, SignalementSyncService signalementSyncService) {
        this.routeProblemeService = routeProblemeService;
        this.signalementSyncService = signalementSyncService;
    }

    
    // Liste Signalements FireBase
    @GetMapping
    @Operation(summary = "Liste des signalements récupérés depuis Firebase")
    public ApiResponse<List<FirebaseRouteProblemeDTO>> getAll() throws Exception {
        return ApiResponse.success(signalementSyncService.syncAndGetAllSignalements());
    }

    // 🔹 Détails d’un signalement
    @GetMapping("/{id}")
    public ApiResponse<RouteProbleme> getById(@PathVariable Integer id) {
        return ApiResponse.success(routeProblemeService.getById(id));
    }
}
