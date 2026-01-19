package com.carte.clouds5spring.service;

import com.carte.clouds5spring.entity.*;
import com.carte.clouds5spring.repository.*;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
