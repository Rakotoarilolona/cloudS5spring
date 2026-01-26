package com.carte.clouds5spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carte.clouds5spring.dto.RouteEntrepriseDto;
import com.carte.clouds5spring.repository.RouteEntrepriseRepository;

@Service
public class RouteEntrepriseService 
{

    private final RouteEntrepriseRepository routeEntrepriseRepository;

    public RouteEntrepriseService(RouteEntrepriseRepository routeEntrepriseRepository) {
        this.routeEntrepriseRepository = routeEntrepriseRepository;
    }

    // public List<RouteEntreprise> findAll() {
    //     return routeEntrepriseRepository.findAll();
    // }

    public List<RouteEntrepriseDto> findAll() 
    {
        return routeEntrepriseRepository.findAll()
            .stream()
            .map(entity -> {
                RouteEntrepriseDto dto = new RouteEntrepriseDto();
                dto.setId(entity.getId());
                dto.setLabel(entity.getLabel());
                return dto;
            })
            .toList();
    }
}

