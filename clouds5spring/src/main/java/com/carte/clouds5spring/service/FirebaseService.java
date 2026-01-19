package com.carte.clouds5spring.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService 
{
    // public Map<String, Object> getSignalement(String firebaseId) 
    // {
    //     DatabaseReference ref = FirebaseDatabase.getInstance()
    //             .getReference("signalements")
    //             .child(firebaseId);

    //     final Map<String, Object>[] data = new Map[1];

    //     ref.addListenerForSingleValueEvent(new ValueEventListener() 
    //     {
    //         public void onDataChange(DataSnapshot snapshot) {
    //             data[0] = (Map<String, Object>) snapshot.getValue();
    //         }

    //         public void onCancelled(DatabaseError error) {}
    //     });

    //     return data[0];
    // }

    public Map<String, Object> getSignalement(String firebaseId) throws Exception  
    {
        Firestore db = FirestoreClient.getFirestore();

        DocumentSnapshot doc = db.collection("signalements")
            .document(firebaseId)
            .get()
            .get();

        return doc.getData();
    }

}
