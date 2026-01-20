package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.RouteProblemeDto;
import com.carte.clouds5spring.entity.RouteEntreprise;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.exception.NotFoundException;

import com.carte.clouds5spring.entity.RouteStatus;
import com.carte.clouds5spring.dto.RouteStatusDto;

import com.carte.clouds5spring.dto.RouteEntrepriseDto;

import com.carte.clouds5spring.hutil.Hjson;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.carte.clouds5spring.repository.RouteStatusRepository;
import com.carte.clouds5spring.models.RouteDashboard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class Hservice {

    private final RouteProblemeRepository routeProblemeRepository;
    private final RouteStatusRepository routeStatusRepository;
    public Hservice(RouteProblemeRepository routeProblemeRepository, 
        RouteStatusRepository routeStatusRepository) 
    {
        this.routeProblemeRepository = routeProblemeRepository;
        this.routeStatusRepository = routeStatusRepository;
    }

    public String getProblemeRoutier() {
        List<RouteProbleme> problemeList = routeProblemeRepository.findAll();
        if (problemeList.isEmpty()) {
            throw new NotFoundException("No data found");
        }

        List<RouteProblemeDto> dtoList = new ArrayList<>(problemeList.size());
        for (int i = 0; i < problemeList.size(); i++) {
            dtoList.add(problemeList.get(i).toDto());
        }

        String data = Hjson.toJson(dtoList);
        return Hjson.formatJson(data, "success", "Data fetched successfully");
    }

    public String getProblemeDetail(String id) {
        Optional<RouteProbleme> probleme = routeProblemeRepository.findById(Integer.valueOf(id));
        if (probleme.isEmpty()) {
            throw new NotFoundException("Data not found for id: " + id);
        }
        RouteProblemeDto dto = probleme.get().toDto();
        String data = Hjson.toJson(dto);
        return Hjson.formatJson(data, "success", "Data fetched successfully");
    }

    public String getProblemeDashboard() {
        List<RouteProbleme> problemeList = routeProblemeRepository.findAll();
        if (problemeList.isEmpty()) {
            throw new NotFoundException("No data found");
        }
        List<RouteEntreprise> entrepriseListEntity = new ArrayList<>();
        for(RouteProbleme probleme : problemeList) {
            RouteEntreprise entreprise = probleme.getRouteEntreprise();
            if (entreprise != null && !entrepriseListEntity.contains(entreprise)) {
                entrepriseListEntity.add(entreprise);
            }
        }
        List<RouteEntrepriseDto> entrepriseList = new ArrayList<>(entrepriseListEntity.size());
        for (int i = 0; i < entrepriseListEntity.size(); i++) {
            entrepriseList.add(entrepriseListEntity.get(i).toDto());
        }

        List<RouteProblemeDto> dtoList = new ArrayList<>(problemeList.size());
        for (int i = 0; i < problemeList.size(); i++) {
            dtoList.add(problemeList.get(i).toDto());
        }
        List<RouteStatus> statusList = routeStatusRepository.findAll();
        List<RouteStatusDto> statusDtoList = new ArrayList<>(statusList.size());
        for (int i = 0; i < statusList.size(); i++) {
            statusDtoList.add(statusList.get(i).toDto());
        }
        RouteDashboard routes = RouteDashboard.calcul(dtoList, statusDtoList, entrepriseList);
        String data = Hjson.toJson(routes);
        return Hjson.formatJson(data, "success", "Data fetched successfully");
    }
}