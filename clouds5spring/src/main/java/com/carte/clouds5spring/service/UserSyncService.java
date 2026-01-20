package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.UserDto;
import com.carte.clouds5spring.config.FirebaseConfig;
import com.carte.clouds5spring.dto.FirebaseUserDTO;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.entity.UserRole;
import com.carte.clouds5spring.repository.UserRepository;
import com.carte.clouds5spring.repository.UserRoleRepository;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Synchronisation Firebase -> PostgreSQL
     * Retourne la liste des DTO pour affichage JSON
     */
    // @Transactional
    // public List<UserDto> syncAndGetAllUsers() throws Exception 
    // {
    //     List<FirebaseUserDTO> firebaseUsers = firebaseUserService.getAllFirebaseUsersDTO();
    //     List<UserDto> dtoList = new ArrayList<>();

    //     for (FirebaseUserDTO fu : firebaseUsers) 
    //     {
    //         User user;
    //         boolean isNew = false;

    //         // Vérifie si l'utilisateur existe déjà
    //         user = userRepository.findByFirebaseUid(fu.getUid())
    //                 .orElse(null);

    //         if (user == null) 
    //         {
    //             // Nouvel utilisateur → création
    //             UserRole defaultRole = roleRepository.findByLabel(fu.getRole())
    //                     .orElseThrow(() -> new RuntimeException("Default role missing"));

    //             user = new User();
    //             user.setFirebaseUid(fu.getUid());
    //             user.setEmail(fu.getEmail());
    //             user.setPseudo(fu.getPseudo());
    //             user.setNbrTentative(0);
    //             user.setUserRole(defaultRole);

    //             userRepository.save(user);
    //             isNew = true;
    //         }

    //         // Crée le DTO pour affichage
    //         UserDto dto = new UserDto();
    //         dto.setId(user.getId());
    //         dto.setEmail(user.getEmail());
    //         dto.setPseudo(user.getPseudo());
    //         dto.setNbrTentative(user.getNbrTentative());
    //         dto.setUserRoleId(user.getUserRole() != null ? user.getUserRole().getId() : null);

    //         dtoList.add(dto);
    //     }

    //     return dtoList; // renvoie tous les DTO pour JSON
    // }

     @Transactional
    public List<UserDto> syncAndGetAllUsers() throws Exception 
    {
        // 1️⃣ Récupérer les utilisateurs depuis Firebase
        List<FirebaseUserDTO> firebaseUsers = firebaseUserService.getAllFirebaseUsersDTO();

        for (FirebaseUserDTO fu : firebaseUsers) 
        {
            User user = userRepository.findByFirebaseUid(fu.getUid()).orElse(null);

            if (user == null) {
                UserRole defaultRole = roleRepository.findByLabel(fu.getRole())
                        .orElseThrow(() -> new RuntimeException("Default role missing"));

                user = new User();
                user.setFirebaseUid(fu.getUid());
                user.setEmail(fu.getEmail());
                user.setPseudo(fu.getPseudo());
                user.setNbrTentative(0);
                user.setUserRole(defaultRole);

                userRepository.save(user);
                userRepository.flush(); // <== garantit que l'id est généré
            }
        }

        // 2️⃣ Récupérer tous les users de PostgreSQL pour construire le DTO
        List<User> allUsers = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();

        for (User u : allUsers) {
            UserDto dto = new UserDto();
            dto.setId(u.getId());
            dto.setEmail(u.getEmail());
            dto.setPseudo(u.getPseudo());
            dto.setNbrTentative(u.getNbrTentative());
            dto.setUserRoleId(u.getUserRole() != null ? u.getUserRole().getId() : null);

            dtoList.add(dto);
        }

        return dtoList;
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
    public String syncUsersToFirebase() throws Exception {

        try {
        // 1️⃣ Récupérer les utilisateurs PostgreSQL
        List<User> users = userRepository.findAll();

        Firestore firestore = FirebaseConfig.getFirestore();

        for (User user : users) {
                Map<String, Object> data = new HashMap<>();
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

        return "mety"; }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
