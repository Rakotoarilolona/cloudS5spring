package com.carte.clouds5spring.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.carte.clouds5spring.dto.HistoriqueStatusRouteDto;
import com.carte.clouds5spring.entity.HistoriqueStatusRoute;
import com.carte.clouds5spring.repository.HistoriqueStatusRouteRepository;

import java.util.List;

@Service
public class HistoriqueStatusRouteService 
{
    @Autowired
    private HistoriqueStatusRouteRepository historiqueStatusRouteRepository;

    public HistoriqueStatusRouteService(HistoriqueStatusRouteRepository historiqueStatusRouteRepository) {
        this.historiqueStatusRouteRepository = historiqueStatusRouteRepository;
    }

    // public List<HistoriqueStatusRoute> getHistoriqueStatusBySignalementId(Integer signalementId) {
    //     return historiqueStatusRouteRepository
    //         .findByRouteProblemeIdOrderByDateHistoriqueDesc(signalementId);
    // }


    public List<HistoriqueStatusRouteDto> getHistoriqueStatusBySignalementId(Integer routeProblemeId) 
    {
        return historiqueStatusRouteRepository
                .findByRouteProblemeIdOrderByDateHistoriqueDesc(routeProblemeId)
                .stream()
                .map(h -> new HistoriqueStatusRouteDto(
                        h.getId(),
                        h.getDateHistorique(),
                        h.getRouteProbleme().getId(),
                        h.getRouteStatus().getId(),
                        h.getRouteStatus().getLabel()
                ))
                .toList();
    }
}
