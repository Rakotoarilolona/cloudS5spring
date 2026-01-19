package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.UserDto;
import com.carte.clouds5spring.dto.FirebaseUserDTO;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.entity.UserRole;
import com.carte.clouds5spring.repository.UserRepository;
import com.carte.clouds5spring.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSyncService {

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
    public List<UserDto> syncAndGetAllUsers() throws Exception {
        // 1️⃣ Récupérer les utilisateurs depuis Firebase
        List<FirebaseUserDTO> firebaseUsers = firebaseUserService.getAllFirebaseUsersDTO();

        for (FirebaseUserDTO fu : firebaseUsers) {
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
}
