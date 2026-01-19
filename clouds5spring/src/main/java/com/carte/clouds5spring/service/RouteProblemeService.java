package com.carte.clouds5spring.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.entity.RouteStatus;
import com.carte.clouds5spring.exception.ApiException;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.carte.clouds5spring.repository.RouteStatusRepository;
import com.carte.clouds5spring.service.FirebaseService;


@Service
public class RouteProblemeService 
{
    private final RouteProblemeRepository routeProblemeRepository;
    private final RouteStatusRepository statusRepository;
    private final FirebaseService firebaseService;

    public RouteProblemeService(
        RouteProblemeRepository routeProblemeRepository,
        RouteStatusRepository statusRepository,
        FirebaseService firebaseService
    ) {
        this.routeProblemeRepository = routeProblemeRepository;
        this.statusRepository = statusRepository;
        this.firebaseService = firebaseService;
    }

    public List<RouteProbleme> getAll() 
    {
        return routeProblemeRepository.findAll();
    }

    public RouteProbleme getById(Integer id) 
    {
        return routeProblemeRepository.findById(id).orElse(null);
    }

    public void saveFromFirebase(String firebaseId) throws Exception
    {
        Map<String, Object> data = firebaseService.getSignalement(firebaseId);

        RouteProbleme rp = new RouteProbleme();
        rp.setFirebaseId(firebaseId);
        rp.setSurface(new BigDecimal(data.get("surface").toString()));
        rp.setBudget(new BigDecimal(data.get("budget").toString()));

        RouteStatus status = statusRepository.findByLabel(
            data.get("status").toString()
        );

        rp.setRouteStatus(status);

        routeProblemeRepository.save(rp);
    }

    public void updateStatus(Integer id, Integer statusId) 
    {
        RouteProbleme rp = routeProblemeRepository.findById(id)
                .orElseThrow(() -> new ApiException("Signalement introuvable"));

        RouteStatus status = statusRepository.findById(statusId)
                .orElseThrow(() -> new ApiException("Statut invalide"));

        rp.setRouteStatus(status);
        rp.setUpdatedAt(LocalDateTime.now());

        routeProblemeRepository.save(rp);

        // Optionnel : MAJ Firebase
        // firebaseService.updateStatus(rp.getFirebaseId(), status.getLabel());
        
    }

}
