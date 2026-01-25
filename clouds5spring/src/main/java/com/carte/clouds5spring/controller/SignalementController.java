package com.carte.clouds5spring.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.AssignEntrepriseDto;
import com.carte.clouds5spring.dto.RouteProblemeDto;
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
    public ApiResponse<List<RouteProblemeDto>> getAll() throws Exception {
        return ApiResponse.success(routeProblemeService.getAll());
    }


    @GetMapping("/envoi")
    @Operation(summary = "Envoi des signalements locals vers FireBase")
    public ApiResponse<String> envoiFireBase () throws Exception {
        signalementSyncService.syncLocalToFirebase();
        return ApiResponse.success("Synchronisé avec succes");
    }

    // 🔹 Détails d’un signalement
    @GetMapping("/{id}")
    public ApiResponse<RouteProbleme> getById(@PathVariable Integer id) {
        return ApiResponse.success(routeProblemeService.getById(id));
    }

    @PutMapping("/{id}/assign")
    public ApiResponse<String> assignEntreprise(
            @PathVariable Integer id,
            @RequestBody AssignEntrepriseDto dto
    ) {
        routeProblemeService.assignEntreprise(id, dto);
        return ApiResponse.success("Entreprise assignée avec succès");
    }



}
