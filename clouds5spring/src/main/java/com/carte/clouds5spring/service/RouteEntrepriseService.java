package com.carte.clouds5spring.service;

import com.carte.clouds5spring.entity.RouteEntreprise;
import com.carte.clouds5spring.repository.RouteEntrepriseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RouteEntrepriseService 
{

    private final RouteEntrepriseRepository routeEntrepriseRepository;

    public RouteEntrepriseService(RouteEntrepriseRepository routeEntrepriseRepository) {
        this.routeEntrepriseRepository = routeEntrepriseRepository;
    }

    public List<RouteEntreprise> findAll() {
        return routeEntrepriseRepository.findAll();
    }
}

