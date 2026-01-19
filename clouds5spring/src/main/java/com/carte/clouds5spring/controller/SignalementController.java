package com.carte.clouds5spring.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.service.RouteProblemeService;

@RestController
@RequestMapping("/signalements")
public class SignalementController {

    private final RouteProblemeService routeProblemeService;

    public SignalementController(RouteProblemeService routeProblemeService) {
        this.routeProblemeService = routeProblemeService;
    }

    // 🔹 Liste de tous les signalements
    @GetMapping
    public ResponseEntity<ApiResponse<List<RouteProbleme>>> getAll() {
        return ResponseEntity.ok(
            ApiResponse.success(routeProblemeService.getAll())
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
