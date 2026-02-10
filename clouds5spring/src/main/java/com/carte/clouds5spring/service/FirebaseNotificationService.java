package com.carte.clouds5spring.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FirebaseNotificationService {

    public void sendNotification(String fcmToken, String status, String updatedAt, String description) {
        try {
            Notification notification = Notification.builder()
                .setTitle("Mise à jour de votre signalement")
                .setBody("Le statut de votre signalement "+ description + " est maintenant \"" + status + "\" (" + updatedAt + ")")
                .build();

            Message message = Message.builder()
                .setToken(fcmToken)
                .setNotification(notification)
                .putData("status", status)
                .putData("updatedAt", updatedAt)
                .build();

            FirebaseMessaging.getInstance().send(message);
            System.out.println("✅ Notification envoyée à " + fcmToken);
        } catch (Exception e) {
            System.err.println("⚠️ Erreur lors de l’envoi FCM : " + e.getMessage());
        }
    }
}
