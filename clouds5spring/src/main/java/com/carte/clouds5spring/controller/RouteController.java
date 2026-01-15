package com.carte.clouds5spring.controller;

import org.springframework.web.bind.annotation.*;
import com.carte.clouds5spring.service.Hservice;
@RestController
@RequestMapping("/api/data")
public class RouteController 
{
    @GetMapping("/routeprobleme")
    public String getRouteProbleme() {
        return Hservice.getProblemeRoutier();
    }

    @GetMapping("/routeprobleme/{id}")
    public String getRouteProblemeById(@PathVariable String id) {
        return Hservice.getProblemeDetail(id);
    }

    @GetMapping("/routeprobleme/dashboard")
    public String getRouteProblemeDashboard() {
        return Hservice.getProblemeDashboard();
    }
}
