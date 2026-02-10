package com.carte.clouds5spring.controller;

import org.springframework.web.bind.annotation.*;

import com.carte.clouds5spring.service.Hservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/data")
@Tag(name = "RouteProbleme", description = "Endpoints liés aux problèmes routiers")
public class RouteController 
{
    private final Hservice hservice;
    public RouteController(Hservice hservice) {
        this.hservice = hservice;
    }
    @GetMapping("/routeprobleme")
    @Operation(summary = "Liste des problèmes routiers")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès")
    })
    public String getRouteProbleme() {
        return hservice.getProblemeRoutier();
    }

    @GetMapping("/routeprobleme/{id}")
    @Operation(summary = "Détail d'un problème routier")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès")
    })
    public String getRouteProblemeById(
        @Parameter(description = "Identifiant du problème routier", required = true)
        @PathVariable String id
    ) {
        return hservice.getProblemeDetail(id);
    }

    @GetMapping("/routeprobleme/dashboard")
    @Operation(summary = "Dashboard des problèmes routiers")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès")
    })
    public String getRouteProblemeDashboard() {
        return hservice.getProblemeDashboard();
    }

    @PostMapping("/prixforfaitaire")
    @Operation(summary = "modifier forfaitaire")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès")
    })
    public String updatePrixForfaitaire(
        @Parameter(description = "Montant du prix forfaitaire", required = true)
        @RequestParam double montant
    ) {
        return hservice.updatePrixForfaitaire(montant);
    }
    @GetMapping("/prixforfaitaire")
    @Operation(summary = "Récupérer le prix forfaitaire")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès")
    })
    public String getPrixForfaitaire() {
        return hservice.getPrixForfaitaire();
    }
}
