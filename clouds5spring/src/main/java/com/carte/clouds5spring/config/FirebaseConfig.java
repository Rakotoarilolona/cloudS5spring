package com.carte.clouds5spring.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
public class FirebaseConfig 
{
    @PostConstruct
    public void initFirebase() {
        try {
            InputStream serviceAccount =
                getClass().getClassLoader()
                    .getResourceAsStream("clouds5mobile-firebase-adminsdk-fbsvc-b25e2a3a9f.json");

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (Exception e) {
            throw new RuntimeException("Firebase initialization failed", e);
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}
