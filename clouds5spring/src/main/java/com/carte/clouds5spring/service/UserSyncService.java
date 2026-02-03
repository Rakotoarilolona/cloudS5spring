package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.UserDto;
import com.carte.clouds5spring.config.FirebaseConfig;
import com.carte.clouds5spring.dto.FirebaseUserDTO;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.entity.UserRole;
import com.carte.clouds5spring.repository.UserRepository;
import com.carte.clouds5spring.repository.UserRoleRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserSyncService 
{
    private final FirebaseUserService firebaseUserService;
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;

    public UserSyncService(FirebaseUserService firebaseUserService,
                           UserRepository userRepository,
                           UserRoleRepository roleRepository) {
        this.firebaseUserService = firebaseUserService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void createAuthUser(User user) throws Exception {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setEmailVerified(false)
                .setPassword(user.getPassword()) 
                .setDisabled(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
    }


    @Transactional
    public String syncUsersFromFirebase() throws Exception {
    try {
        Firestore firestore = FirebaseConfig.getFirestore();
        CollectionReference usersRef = firestore.collection("users");
        ApiFuture<QuerySnapshot> querySnapshot = usersRef.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        for (QueryDocumentSnapshot doc : documents) {
            Map<String, Object> data = doc.getData();

            Integer idUser = ((Number) data.get("id_user")).intValue();
            String email = (String) data.get("email");
            String password = (String) data.get("password");
            String pseudo = (String) data.get("pseudo");
            String roleLabel = (String) data.get("role");
            Integer nbrTentative = data.get("nbrTentative") != null
                    ? ((Number) data.get("nbrTentative")).intValue()
                    : 0;
            String firebaseUid = (String) data.get("firebaseUid");

            // 🔹 Vérifie si l'utilisateur existe déjà en base
            Optional<User> existingOpt = userRepository.findById(idUser);
            User user;

            if (existingOpt.isPresent()) {
                user = existingOpt.get();
                user.setFirebaseUid(firebaseUid);
                user.setNbrTentative(nbrTentative);
                userRepository.save(user);
                return "Utilisateur mis à jour : " + user.getEmail();
            } else {
                user = new User();
                // user.setId(idUser);
                user.setEmail(email);
                user.setPassword(password);
                user.setPseudo(pseudo);
                user.setFirebaseUid(firebaseUid);
                user.setNbrTentative(nbrTentative);
                if (roleLabel != null) {
                    UserRole role = roleRepository.findByLabel(roleLabel).orElse(null);
                    user.setUserRole(role);
                } else {
                    user.setUserRole(null);
                }
                userRepository.save(user);
            }
        }

            return "Synchronisation des utilisateurs terminée.";
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Erreur lors de la synchronisation depuis Firebase : " + e.getMessage());
    }
}


    @Transactional
    public String syncUsersToFirebase() throws Exception {

        try {
            List<User> users = userRepository.findAll();

            Firestore firestore = FirebaseConfig.getFirestore();

            for (User user : users) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("id_user", user.getId());
                    data.put("email", user.getEmail());
                    data.put("password", user.getPassword());
                    data.put("pseudo", user.getPseudo());
                    data.put("role", user.getUserRole() != null ? user.getUserRole().getLabel() : null);
                    data.put("nbrTentative", user.getNbrTentative());
                    data.put("firebaseUid", user.getFirebaseUid());
                    data.put("updatedAt", new java.util.Date());

                    WriteResult result = firestore.collection("users")
                        .document(String.valueOf(user.getId()))
                        .set(data)
                        .get();
                try {
                    createAuthUser(user);
                } catch (Exception e) {
                    if (!e.getMessage().contains("EMAIL_EXISTS")) {
                        e.printStackTrace();
                    }
                }
            }

            return "mety"; 
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
