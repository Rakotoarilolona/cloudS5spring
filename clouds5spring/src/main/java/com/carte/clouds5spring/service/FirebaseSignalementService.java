package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FirebaseSignalementService 
{
    public List<Map<String, Object>> getAllSignalements() throws Exception 
    {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("signalements");

        List<Map<String, Object>> result = new ArrayList<>();

        for (QueryDocumentSnapshot doc : ref.get().get().getDocuments()) {
            Map<String, Object> data = doc.getData();
            data.put("firebaseId", doc.getId()); // 🔑 IMPORTANT
            result.add(data);
        }

        return result;
    }

    public List<FirebaseRouteProblemeDTO> getAllSignalementsDTO() throws Exception 
    {
        List<FirebaseRouteProblemeDTO> list = new ArrayList<>();

        for (Map<String, Object> data : getAllSignalements()) {

            FirebaseRouteProblemeDTO dto = new FirebaseRouteProblemeDTO();
            dto.setFirebaseId(data.get("firebaseId").toString());
            dto.setSurface(new BigDecimal(data.get("surface").toString()));
            dto.setBudget(new BigDecimal(data.get("budget").toString()));
            dto.setStatus(data.get("status").toString());
            dto.setEntreprise(data.get("entreprise").toString());

            list.add(dto);
        }

        return list;
    }

}
