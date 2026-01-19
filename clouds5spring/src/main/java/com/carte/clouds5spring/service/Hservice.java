package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.RouteProblemeDto;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.hutil.Hjson;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.carte.clouds5spring.models.RouteDashboard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class Hservice {

    private final RouteProblemeRepository routeProblemeRepository;

    public Hservice(RouteProblemeRepository routeProblemeRepository) {
        this.routeProblemeRepository = routeProblemeRepository;
    }

    public String getProblemeRoutier() {
        List<RouteProbleme> problemeList = routeProblemeRepository.findAll();
        if (problemeList.isEmpty()) {
            throw new RuntimeException("No data found");
        }

        List<RouteProblemeDto> dtoList = new ArrayList<>(problemeList.size());
        for (int i = 0; i < problemeList.size(); i++) {
            dtoList.add(problemeList.get(i).toDto());
        }

        String data = Hjson.toJson(dtoList);
        return Hjson.formatJson(data, "success", "Data fetched successfully");
    }

    public String getProblemeDetail(String id) {
        Optional<RouteProbleme> probleme = routeProblemeRepository.findById(Long.parseLong(id));
        if (probleme.isEmpty()) {
            throw new RuntimeException("Data not found for id: " + id);
        }
        RouteProblemeDto dto = probleme.get().toDto();
        String data = Hjson.toJson(dto);
        return Hjson.formatJson(data, "success", "Data fetched successfully");
    }

    public String getProblemeDashboard() {
        List<RouteProbleme> problemeList = routeProblemeRepository.findAll();
        if (problemeList.isEmpty()) {
            throw new RuntimeException("No data found");
        }
        List<RouteProblemeDto> dtoList = new ArrayList<>(problemeList.size());
        for (int i = 0; i < problemeList.size(); i++) {
            dtoList.add(problemeList.get(i).toDto());
        }
        RouteDashboard routes = RouteDashboard.calcul(dtoList);
        String data = Hjson.toJson(routes);
        return Hjson.formatJson(data, "success", "Data fetched successfully");
    }
}