package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.repository.RouteEntrepriseRepository;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.carte.clouds5spring.repository.RouteStatusRepository;
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

    private final RouteEntrepriseRepository entrepriseRepository;

    private final RouteStatusRepository statusRepository;

    public SignalementSyncService(RouteProblemeRepository routeProblemeRepository, 
        RouteEntrepriseRepository r, RouteStatusRepository s) {
        this.routeProblemeRepository = routeProblemeRepository;
        this.entrepriseRepository = r;
        this.statusRepository = s;
    }


    public List<FirebaseRouteProblemeDTO> syncAndGetAllSignalements() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("signalements");

        List<FirebaseRouteProblemeDTO> dtoList = new ArrayList<>();

        for (QueryDocumentSnapshot doc : ref.get().get().getDocuments()) {
            Map<String, Object> data = doc.getData();

            data.put("firebaseId", doc.getId());

            FirebaseRouteProblemeDTO dto = new FirebaseRouteProblemeDTO();
            dto.setFirebaseId(getString(data, "firebaseId", null));
            dto.setSurface(getBigDecimal(data, "surface"));
            dto.setBudget(getBigDecimal(data, "budget"));
            dto.setStatus(getString(data, "status", null));
            dto.setEntreprise(getString(data, "entreprise", null));
            dto.setDescription(getString(data, "description", null));

            dto.setIdStatus(getString(data, "idStatus", null));
            dto.setIdEntreprise(getString(data, "idEntreprise", null));

            Object locObj = data.get("localisation");
            if (locObj instanceof GeoPoint geoPoint) {
                dto.setLatitude(BigDecimal.valueOf(geoPoint.getLatitude()));
                dto.setLongitude(BigDecimal.valueOf(geoPoint.getLongitude()));
            } else {
                dto.setLatitude(null);
                dto.setLongitude(null);
            }

            dtoList.add(dto);


            RouteProbleme rp = routeProblemeRepository.findByFirebaseId(dto.getFirebaseId()).orElse(null);

            if (rp == null) {
                rp = new RouteProbleme();
                rp.setFirebaseId(dto.getFirebaseId());
                rp.setUpdatedAt(LocalDateTime.now());
            }

            rp.setSurface(dto.getSurface());
            rp.setBudget(dto.getBudget());
            rp.setUpdatedAt(LocalDateTime.now());
            rp.setLatitude(dto.getLatitude());
            rp.setLongitude(dto.getLongitude());
            rp.setProblemeDescription(dto.getDescription());

            rp.setRouteEntreprise(null);
            rp.setRouteStatus(null);
            rp.setUser(null);

            if (dto.getIdEntreprise() != null) {
                rp.setRouteEntreprise(entrepriseRepository.findById(Long.valueOf(dto.getIdEntreprise())).orElse(null));
            }
            if (dto.getIdStatus() != null) {
                rp.setRouteStatus(statusRepository.findById(Integer.valueOf(dto.getIdStatus())).orElse(null));
            }
            routeProblemeRepository.save(rp);
        }

        return dtoList;
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
