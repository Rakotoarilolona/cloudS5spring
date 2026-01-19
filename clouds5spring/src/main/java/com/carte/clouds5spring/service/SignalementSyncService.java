package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SignalementSyncService 
{
    private final RouteProblemeRepository routeProblemeRepository;

    public SignalementSyncService(RouteProblemeRepository routeProblemeRepository) {
        this.routeProblemeRepository = routeProblemeRepository;
    }

    public List<FirebaseRouteProblemeDTO> syncAndGetAllSignalements() throws Exception 
    {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("signalements");

        List<FirebaseRouteProblemeDTO> dtoList = new ArrayList<>();

        for (QueryDocumentSnapshot doc : ref.get().get().getDocuments()) {
            Map<String, Object> data = doc.getData();
            data.put("firebaseId", doc.getId()); // IMPORTANT pour DTO

            // --- 1️⃣ Construire le DTO pour affichage JSON ---
            FirebaseRouteProblemeDTO dto = new FirebaseRouteProblemeDTO();
            dto.setFirebaseId(getString(data, "firebaseId", ""));
            dto.setSurface(getBigDecimal(data, "surface"));
            dto.setBudget(getBigDecimal(data, "budget"));
            dto.setStatus(getString(data, "status", "INCONNU"));
            dto.setEntreprise(getString(data, "entreprise", "INCONNU"));

            dtoList.add(dto);

            // --- 2️⃣ Construire l'entité JPA et sauvegarder ---
            RouteProbleme rp = new RouteProbleme();
            rp.setFirebaseId(dto.getFirebaseId());
            rp.setSurface(dto.getSurface());
            rp.setBudget(dto.getBudget());
            rp.setUpdatedAt(LocalDateTime.now());

            //  Si tes colonnes @ManyToOne sont NOT NULL, tu dois fournir des entités existantes
            rp.setRouteEntreprise(null); 
            rp.setRouteStatus(null);      
            rp.setUser(null);            

            // rp.setRouteEntreprise(dto.getEntrepriseEntity());
            // rp.setRouteStatus(dto.getStatusEntity());
            // rp.setUser(dto.getUserEntity());

            routeProblemeRepository.save(rp);
        }

        return dtoList; // renvoyer les DTO pour affichage JSON
    }


    private String getString(Map<String,Object> map, String key, String defaultValue) {
        Object value = map.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    private BigDecimal getBigDecimal(Map<String,Object> map, String key) {
        Object value = map.get(key);
        return value != null ? new BigDecimal(value.toString()) : BigDecimal.ZERO;
    }


}
