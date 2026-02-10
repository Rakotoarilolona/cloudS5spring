package com.carte.clouds5spring.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.carte.clouds5spring.dto.AssignEntrepriseDto;
import com.carte.clouds5spring.dto.RouteProblemeDto;
import com.carte.clouds5spring.entity.RouteEntreprise;
import com.carte.clouds5spring.entity.RouteProbleme;
import com.carte.clouds5spring.entity.RouteStatus;
import com.carte.clouds5spring.exception.ApiException;
import com.carte.clouds5spring.repository.RouteEntrepriseRepository;
import com.carte.clouds5spring.repository.RouteProblemeRepository;
import com.carte.clouds5spring.repository.RouteStatusRepository;

import jakarta.transaction.Transactional;


@Service
public class RouteProblemeService 
{
    private final RouteProblemeRepository routeProblemeRepository;
    private final RouteStatusRepository statusRepository;
    private final FirebaseService firebaseService;
    private final RouteEntrepriseRepository entrepriseRepository;


    public RouteProblemeService(
        RouteProblemeRepository routeProblemeRepository,
        RouteStatusRepository statusRepository,
        FirebaseService firebaseService,
        RouteEntrepriseRepository entrepriseRepository
    ) {
        this.routeProblemeRepository = routeProblemeRepository;
        this.statusRepository = statusRepository;
        this.firebaseService = firebaseService;
        this.entrepriseRepository = entrepriseRepository;
    }

    // public List<RouteProbleme> getAll() 
    // {
    //     return routeProblemeRepository.findAll();
    // }

    // recuperer la liste depuis la bdd postgres (local)
    public List<RouteProblemeDto> getAll() throws Exception 
    {
        return routeProblemeRepository.findAllDto();
    }

    // public List<FirebaseRouteProblemeDTO> getAll() throws Exception {
    //     Firestore db = FirestoreClient.getFirestore();
    //     CollectionReference ref = db.collection("signalements");

    //     List<FirebaseRouteProblemeDTO> dtoList = new ArrayList<>();

    //     for (QueryDocumentSnapshot doc : ref.get().get().getDocuments()) {
    //         Map<String, Object> data = doc.getData();

    //         data.put("firebaseId", doc.getId());

    //         FirebaseRouteProblemeDTO dto = new FirebaseRouteProblemeDTO();
    //         dto.setFirebaseId(getString(data, "firebaseId", null));
    //         dto.setSurface(getBigDecimal(data, "surface"));
    //         dto.setBudget(getBigDecimal(data, "budget"));
    //         dto.setStatus(getString(data, "status", null));
    //         dto.setEntreprise(getString(data, "entreprise", null));
    //         dto.setDescription(getString(data, "description", null));

    //         dto.setIdStatus(getString(data, "idStatus", null));
    //         dto.setIdEntreprise(getString(data, "idEntreprise", null));

    //         Object locObj = data.get("localisation");
    //         if (locObj instanceof GeoPoint geoPoint) {
    //             dto.setLatitude(BigDecimal.valueOf(geoPoint.getLatitude()));
    //             dto.setLongitude(BigDecimal.valueOf(geoPoint.getLongitude()));
    //         } else {
    //             dto.setLatitude(null);
    //             dto.setLongitude(null);
    //         }

    //         dtoList.add(dto);
    //     }

    //     return dtoList;
    // }

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

    private String getString(Map<String,Object> map, String key, String defaultValue) {
        Object value = map.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    private BigDecimal getBigDecimal(Map<String,Object> map, String key) {
        Object value = map.get(key);
        return value != null ? new BigDecimal(value.toString()) : BigDecimal.ZERO;
    }

    @Transactional
    public void assignEntreprise(Integer id, AssignEntrepriseDto dto) {

        RouteProbleme rp = routeProblemeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Signalement introuvable"));

        RouteEntreprise entreprise = entrepriseRepository.findById(dto.getEntrepriseId())
            .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));

        rp.setRouteEntreprise(entreprise);
        rp.setBudget(dto.getBudget());
        rp.setNiveau(dto.getNiveau());

        routeProblemeRepository.save(rp);
    }

}
