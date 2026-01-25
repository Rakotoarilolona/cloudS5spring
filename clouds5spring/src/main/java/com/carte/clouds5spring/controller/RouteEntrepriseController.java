package com.carte.clouds5spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carte.clouds5spring.entity.RouteEntreprise;
import com.carte.clouds5spring.service.RouteEntrepriseService;

@RestController
@RequestMapping("/entreprises")
public class RouteEntrepriseController {

    private final RouteEntrepriseService routeEntrepriseService;

    public RouteEntrepriseController(RouteEntrepriseService routeEntrepriseService) {
        this.routeEntrepriseService = routeEntrepriseService;
    }

    @GetMapping
    public List<RouteEntreprise> getEntreprises() {
        return routeEntrepriseService.findAll();
    }
}
