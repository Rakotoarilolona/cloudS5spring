package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.*;
import com.carte.clouds5spring.entity.*;
import com.carte.clouds5spring.repository.*;
import com.carte.clouds5spring.models.*;
import com.carte.clouds5spring.exception.NotFoundException;


import com.carte.clouds5spring.hutil.Hjson;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
@Service
public class Hservice {

    private final RouteProblemeRepository routeProblemeRepository;
    private final RouteStatusRepository routeStatusRepository;
    private final HistoriqueStatusRouteRepository historiqueStatusRouteRepository;

    public Hservice(RouteProblemeRepository routeProblemeRepository, 
        RouteStatusRepository routeStatusRepository, HistoriqueStatusRouteRepository historiqueStatusRouteRepository) 
    {
        this.routeProblemeRepository = routeProblemeRepository;
        this.routeStatusRepository = routeStatusRepository;
        this.historiqueStatusRouteRepository = historiqueStatusRouteRepository;
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
    public StatHistorique getStatHistoriqueByStatus(Integer id_status,Integer id_status_suivant)
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
        if(id_status_suivant!=null)
        {
            List<HistoriqueStatusRoute> problemeListNext = historiqueStatusRouteRepository.findByStatusId(id_status_suivant);
            List <Double> delaisList = new ArrayList<>();
            
            for(HistoriqueStatusRoute hsr : problemeList)
            {
                for(HistoriqueStatusRoute hsrNext : problemeListNext)
                {
                    RouteProbleme rp = hsr.getRouteProbleme();
                    RouteProbleme rpNext = hsrNext.getRouteProbleme();
                    if(rp.getId() == rpNext.getId())
                    {
                        LocalDateTime timestamp = hsr.getDateHistorique();
                        LocalDateTime timestampNext = hsrNext.getDateHistorique();
                        double delais = java.time.Duration.between(timestamp, timestampNext).toMinutes();
                        delaisList.add(delais);
                        break;
                    }
                }
            }
            double sommeDelais = 0;
            for(Double d : delaisList)
            {
                sommeDelais += d;
            }
            double delaisMoyen = 0;
            if(delaisList.size() > 0)
            {
                delaisMoyen = sommeDelais / delaisList.size();
            }
            stat.setDelaisMoyen(delaisMoyen);
        }
        
        return stat;
    }
    public String getProblemeDashboard() {

        return Hjson.formatJson(null, "success", "Data fetched successfully");

    }
}