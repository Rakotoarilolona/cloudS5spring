package com.carte.clouds5spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.service.RouteProblemeService;

@RestController
@RequestMapping("/admin/signalements")
public class AdminSignalementController {

    private final RouteProblemeService routeProblemeService;

    public AdminSignalementController(RouteProblemeService routeProblemeService) {
        this.routeProblemeService = routeProblemeService;
    }

    // 🔹 Changer le statut d’un signalement
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Object>> updateStatus(
            @PathVariable Integer id,
            @RequestParam Integer statusId) {

        routeProblemeService.updateStatus(id, statusId);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
