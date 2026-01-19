package com.carte.clouds5spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carte.clouds5spring.dto.UserUpdateRequest;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.entity.UserRole;
import com.carte.clouds5spring.exception.ApiException;
import com.carte.clouds5spring.repository.UserRepository;
import com.carte.clouds5spring.repository.UserRoleRepository;
import com.carte.clouds5spring.security.constant.SecurityConstants;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void unblockUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        user.setNbrTentative(0);
        user.setBlockedAt(null);
        userRepository.save(user);
    }

    @Override
    public List<User> getBlockedUsers() {
        return userRepository.findByNbrTentativeGreaterThanEqual(
            SecurityConstants.MAX_TENTATIVE
        );
    }

    @Override
    public void updateMyProfile(String emailConnected, UserUpdateRequest req) {

        User user = userRepository.findByEmail(emailConnected)
                .orElseThrow(() -> new ApiException("User not found"));

        if (req.getEmail() != null) {
            user.setEmail(req.getEmail());
        }

        if (req.getPassword() != null) {
            user.setPassword(req.getPassword()); // (voir remarque sécurité plus bas)
        }

        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer userId, UserUpdateRequest req) 
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        if (req.getEmail() != null) {
            user.setEmail(req.getEmail());
        }

        if (req.getPassword() != null) {
            user.setPassword(req.getPassword());
        }

        if (req.getRoleId() != null) {
            UserRole role = roleRepository.findById(req.getRoleId())
                    .orElseThrow(() -> new ApiException("Role not found"));
            user.setUserRole(role);
        }

        userRepository.save(user);
    }



}
