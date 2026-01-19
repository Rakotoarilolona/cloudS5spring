package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.FirebaseUserDTO;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.entity.UserRole;
import com.carte.clouds5spring.repository.UserRepository;
import com.carte.clouds5spring.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    @Transactional
    public void syncFirebaseUsersToPostgres() throws Exception 
    {
        List<FirebaseUserDTO> firebaseUsers =
            firebaseUserService.getAllFirebaseUsersDTO();

        for (FirebaseUserDTO fu : firebaseUsers) 
        {
            if (!userRepository.existsByFirebaseUid(fu.getUid()))
            {
                UserRole defaultRole = roleRepository.findByLabel(fu.getRole())
                .orElseThrow(() -> new RuntimeException("Default role missing"));

                User user = new User();
                user.setFirebaseUid(fu.getUid());
                user.setEmail(fu.getEmail());
                user.setNbrTentative(0);
                user.setUserRole(defaultRole);
                user.setPseudo(fu.getPseudo());

                userRepository.save(user);
            }
        }
    }
}
