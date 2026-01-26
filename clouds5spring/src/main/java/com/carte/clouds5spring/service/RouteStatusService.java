package com.carte.clouds5spring.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.carte.clouds5spring.dto.RouteStatusDto;
import com.carte.clouds5spring.entity.HistoriqueStatusRoute;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.entity.RouteStatus;
import com.carte.clouds5spring.repository.HistoriqueStatusRouteRepository;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.carte.clouds5spring.repository.RouteStatusRepository;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class RouteStatusService 
{
    private final RouteProblemeRepository routeProblemeRepo;
    private final RouteStatusRepository routeStatusRepo;
    private final HistoriqueStatusRouteRepository historiqueRepo;

    public RouteStatusService(
            RouteProblemeRepository routeProblemeRepo,
            RouteStatusRepository routeStatusRepo,
            HistoriqueStatusRouteRepository historiqueRepo
    ) {
        this.routeProblemeRepo = routeProblemeRepo;
        this.routeStatusRepo = routeStatusRepo;
        this.historiqueRepo = historiqueRepo;
    }

    public List<RouteStatusDto> getAll() 
    {
        return routeStatusRepo.findAll()
                .stream()
                .map(entity -> {
                    RouteStatusDto dto = new RouteStatusDto();
                    dto.setId(entity.getId());
                    dto.setLabel(entity.getLabel());
                    dto.setValeur(entity.getValeur());
                    return dto;
                })
                .toList();
    }

    public void changeStatus(Integer routeProblemeId, Integer routeStatusId) 
    {
        RouteProbleme probleme = routeProblemeRepo.findById(routeProblemeId)
                .orElseThrow(() -> new RuntimeException("Signalement introuvable"));

        RouteStatus status = routeStatusRepo.findById(routeStatusId)
                .orElseThrow(() -> new RuntimeException("Status introuvable"));

        // 1️⃣ Mise à jour du status actuel
        probleme.setRouteStatus(status);
        routeProblemeRepo.save(probleme);

        // 2️⃣ Historisation
        HistoriqueStatusRoute hist = new HistoriqueStatusRoute();
        hist.setRouteProbleme(probleme);
        hist.setRouteStatus(status);
        hist.setDateHistorique(LocalDateTime.now());

        historiqueRepo.save(hist);

        // 3️⃣ Synchronisation Firebase
        syncFirebaseStatus(probleme, status);
    }

    private void syncFirebaseStatus(RouteProbleme probleme, RouteStatus status) 
    {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("signalements")
                                    .document(probleme.getId().toString());

        Map<String, Object> updates = new HashMap<>();
        updates.put("status", status.getLabel());
        updates.put("lastUpdate", FieldValue.serverTimestamp());

        docRef.update(updates);

    }
}
