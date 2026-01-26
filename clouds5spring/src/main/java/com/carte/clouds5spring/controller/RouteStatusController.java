package com.carte.clouds5spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carte.clouds5spring.dto.ApiResponse;
import com.carte.clouds5spring.dto.ChangeStatusRequest;
import com.carte.clouds5spring.service.RouteStatusService;

@RestController
@RequestMapping("/admin/route-status")
public class RouteStatusController 
{
    private final RouteStatusService service;

    public RouteStatusController(RouteStatusService service) {
        this.service = service;
    }

    /**
     * Change le status d'un signalement.
     * @param routeProblemeId l'id du signalement à modifier (dans l'URL)
     * @param request contient le nouvel status
     */
    @PutMapping("/change/{routeProblemeId}")
    public ResponseEntity<ApiResponse<Object>> changeStatus(
            @PathVariable Integer routeProblemeId,
            @RequestBody ChangeStatusRequest request) 
    {
        service.changeStatus(routeProblemeId, request.getRouteStatusId());

        return ResponseEntity.ok(
                new ApiResponse<>("success", null, null)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllStatus() 
    {
        return ResponseEntity.ok(
                new ApiResponse<>("success", service.getAll(), null)
        );
    }
}
