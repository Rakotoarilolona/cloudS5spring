package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.*;
import com.carte.clouds5spring.entity.*;
import com.carte.clouds5spring.repository.*;
import com.carte.clouds5spring.models.*;
import com.carte.clouds5spring.exception.NotFoundException;


import com.carte.clouds5spring.hutil.Hjson;
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
    public StatHistorique getStatHistoriqueByStatus(int id_status)
    {
        StatHistorique stat =new StatHistorique();
        Optional<RouteStatus> status = routeStatusRepository.findById(id_status);
        if (status.isEmpty()) {
            throw new NotFoundException("Data not found for id_status: " + id_status);
        }
        RouteStatusDto statusDto = status.get().toDto();
        stat.setStatus(statusDto);
        List<HistoriqueStatusRoute> problemeList = historiqueStatusRouteRepository.findByStatusId(id_status);

        stat.setProblemeList(problemeList);
        return stat;
    }
    public String getProblemeDashboard() {

        return Hjson.formatJson(null, "success", "Data fetched successfully");

    }
}