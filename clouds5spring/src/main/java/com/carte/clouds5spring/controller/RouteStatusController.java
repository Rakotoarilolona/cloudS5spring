package com.carte.clouds5spring.controller;

import com.carte.clouds5spring.dto.ChangeStatusRequest;
import com.carte.clouds5spring.service.RouteStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carte.clouds5spring.dto.ApiResponse;

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
}
