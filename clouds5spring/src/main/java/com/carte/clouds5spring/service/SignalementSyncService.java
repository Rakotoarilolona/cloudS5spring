package com.carte.clouds5spring.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher.Route;

import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.entity.HistoriqueStatusRoute;
import com.carte.clouds5spring.entity.Photo;
import com.carte.clouds5spring.repository.HistoriqueStatusRouteRepository;
import com.carte.clouds5spring.repository.RouteEntrepriseRepository;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.carte.clouds5spring.repository.RouteStatusRepository;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class SignalementSyncService 
{
    private final RouteProblemeRepository routeProblemeRepository;

    private final RouteEntrepriseRepository entrepriseRepository;

    private final RouteStatusRepository statusRepository;

    private final HistoriqueStatusRouteRepository historiqueStatusRouteRepository;

    public SignalementSyncService(RouteProblemeRepository routeProblemeRepository, 
        RouteEntrepriseRepository r, RouteStatusRepository s , HistoriqueStatusRouteRepository h) {
        this.routeProblemeRepository = routeProblemeRepository;
        this.entrepriseRepository = r;
        this.statusRepository = s;
        this.historiqueStatusRouteRepository = h;
    }


    public List<FirebaseRouteProblemeDTO> syncFirebaseToLocal () throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("signalements");

        List<FirebaseRouteProblemeDTO> dtoList = new ArrayList<>();

        for (QueryDocumentSnapshot doc : ref.get().get().getDocuments()) {
            Map<String, Object> data = doc.getData();

            data.put("firebaseId", doc.getId());

            FirebaseRouteProblemeDTO dto = new FirebaseRouteProblemeDTO();
            String updatedAt=getString(data, "updatedAt", null);
            LocalDateTime updatedAtLdt = null;
            if(updatedAt!=null)
            {
                Timestamp ts = doc.getTimestamp("updatedAt");
                updatedAtLdt = ts.toDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
            }
            if(updatedAtLdt==null)
            {
                updatedAtLdt = LocalDateTime.now();
            }
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
                rp.setUpdatedAt(updatedAtLdt);
            }

            rp.setSurface(dto.getSurface());
            rp.setBudget(dto.getBudget());
            rp.setUpdatedAt(updatedAtLdt);
            rp.setLatitude(dto.getLatitude());
            rp.setLongitude(dto.getLongitude());
            rp.setProblemeDescription(dto.getDescription());

            // Ne pas écraser les valeurs existantes si Firebase ne fournit pas les IDs.
            rp.setUser(null);

            Integer previousStatusId = rp.getRouteStatus() != null ? rp.getRouteStatus().getId() : null;

            if (dto.getIdEntreprise() != null) {
                rp.setRouteEntreprise(entrepriseRepository.findById(Integer.valueOf(dto.getIdEntreprise())).orElse(null));
            }
            int isNewStatus = 0;
            if (dto.getIdStatus() != null) {
                rp.setRouteStatus(statusRepository.findById(Integer.valueOf(dto.getIdStatus())).orElse(null));
            }
            else
            {
                isNewStatus = 1;
                rp.setRouteStatus(statusRepository.findById(1).orElse(null)); // Status "Nouveau" par défaut
            }

            // Photos: on met à jour la collection, puis on laisse cascade persist.
            Object image = data.get("images");
            List<Photo> photos = new ArrayList<>();
            if (image instanceof List<?>) {
                for (Object imgObj : (List<?>) image) {
                    if (imgObj instanceof String img && img != null && !img.isBlank()) {
                        try {
                            // Support "data:image/...;base64,..." ou base64 brut
                            if (img.startsWith("data:image/")) {
                                String[] parts = img.split(",", 2);
                                if (parts.length == 2) {
                                    img = parts[1];
                                }
                            }
                            Photo p = new Photo();
                            p.setBytes(java.util.Base64.getDecoder().decode(img));
                            p.setRouteProbleme(rp);
                            photos.add(p);
                        } catch (IllegalArgumentException e) {
                            System.err.println("Erreur de décodage base64: " + e.getMessage());
                        }
                    }
                }
            } else if (image instanceof String img && img != null && !img.isBlank()) {
                try {
                    if (img.startsWith("data:image/")) {
                        String[] parts = img.split(",", 2);
                        if (parts.length == 2) {
                            img = parts[1];
                        }
                    }
                    Photo p = new Photo();
                    p.setBytes(java.util.Base64.getDecoder().decode(img));
                    p.setRouteProbleme(rp);
                    photos.add(p);
                } catch (IllegalArgumentException e) {
                    System.err.println("Erreur de décodage base64: " + e.getMessage());
                }
            }
            if (image != null) {
                // Appliquer même si vide pour vider les anciennes photos.
                rp.setPhotos(photos);
            }

            // Sauver d'abord RouteProbleme: nécessaire avant tout historique.
            RouteProbleme savedRp = routeProblemeRepository.save(rp);

            // Historique: FK routeProbleme/routeStatus sont NOT NULL.
            if (isNewStatus==1) 
            {
                HistoriqueStatusRoute hist = new HistoriqueStatusRoute();
                hist.setRouteProbleme(savedRp);
                hist.setRouteStatus(statusRepository.findById(1).orElse(null)); // Status "Nouveau" par défaut
                hist.setDateHistorique(updatedAtLdt);
                historiqueStatusRouteRepository.save(hist);
            }
        }

        return dtoList;
    }


    public void syncLocalToFirebase() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("signalements");

        List<RouteProbleme> localProblemes = routeProblemeRepository.findAll();

        for (RouteProbleme rp : localProblemes) {
            Map<String, Object> data = new HashMap<>();

            // data.put("surface", rp.getSurface() != null ? rp.getSurface().doubleValue() : null);
            data.put("budget", rp.getBudget() != null ? rp.getBudget().doubleValue() : null);
            data.put("status", rp.getRouteStatus() != null ? rp.getRouteStatus().getLabel() : null);
            data.put("entreprise", rp.getRouteEntreprise() != null ? rp.getRouteEntreprise().getLabel() : null);
            data.put("idStatus", rp.getRouteStatus() != null ? rp.getRouteStatus().getId() : null);
            data.put("idEntreprise", rp.getRouteEntreprise() != null ? rp.getRouteEntreprise().getId() : null);
            data.put("updatedAt", Timestamp.now());

            // if (rp.getLatitude() != null && rp.getLongitude() != null) {
            //     data.put("localisation", new GeoPoint(
            //         rp.getLatitude().doubleValue(),
            //         rp.getLongitude().doubleValue()
            //     ));
            // }

            if (rp.getFirebaseId() == null || rp.getFirebaseId().isEmpty()) {
                
                DocumentReference newDoc = ref.document();
                rp.setFirebaseId(newDoc.getId());
                data.put("createdAt", Timestamp.now());
                newDoc.set(data).get();

                routeProblemeRepository.save(rp);
            } else {
                DocumentReference docRef = ref.document(rp.getFirebaseId());
                docRef.set(data, SetOptions.merge()).get();
            }
        }
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
