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

@RestController
@RequestMapping("/signalements")
public class SignalementController {

    private final RouteProblemeService routeProblemeService;
    private final SignalementSyncService signalementSyncService;

    public SignalementController(RouteProblemeService routeProblemeService, SignalementSyncService signalementSyncService) {
        this.routeProblemeService = routeProblemeService;
        this.signalementSyncService = signalementSyncService;
    }

    // 🔹 Liste de tous les signalements
    @GetMapping
    public ResponseEntity<ApiResponse<List<FirebaseRouteProblemeDTO>>> getAll() throws Exception {
        return ResponseEntity.ok(
            // ApiResponse.success(routeProblemeService.getAll())
            ApiResponse.success(signalementSyncService.getAllSignalementsFromFirebase())
        );
    }

    // 🔹 Détails d’un signalement
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RouteProbleme>> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
            ApiResponse.success(routeProblemeService.getById(id))
        );
    }
}
