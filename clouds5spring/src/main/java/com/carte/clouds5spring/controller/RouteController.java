package com.carte.clouds5spring.controller;

import org.springframework.web.bind.annotation.*;
import com.carte.clouds5spring.service.Hservice;
@RestController
@RequestMapping("/api/data")
public class RouteController 
{
    private final Hservice hservice;
    public RouteController(Hservice hservice) {
        this.hservice = hservice;
    }
    @GetMapping("/routeprobleme")
    public String getRouteProbleme() {
        return hservice.getProblemeRoutier();
    }

    @GetMapping("/routeprobleme/{id}")
    public String getRouteProblemeById(@PathVariable String id) {
        return hservice.getProblemeDetail(id);
    }

    @GetMapping("/routeprobleme/dashboard")
    public String getRouteProblemeDashboard() {
        return hservice.getProblemeDashboard();
    }
}
