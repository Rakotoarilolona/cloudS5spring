package com.carte.clouds5spring.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.carte.clouds5spring.dto.AuthResponse;
import com.carte.clouds5spring.dto.LoginRequest;
import com.carte.clouds5spring.dto.RegisterRequest;
import com.carte.clouds5spring.entity.User;
import com.carte.clouds5spring.entity.UserRole;
import com.carte.clouds5spring.entity.UserTentativeHistorique;
import com.carte.clouds5spring.exception.ApiException;
import com.carte.clouds5spring.repository.UserRepository;
import com.carte.clouds5spring.repository.UserRoleRepository;
import com.carte.clouds5spring.repository.UserTentativeHistoriqueRepository;
import com.carte.clouds5spring.security.constant.SecurityConstants;

@Service
@Component
public class AuthServiceImpl implements AuthService 
{
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserTentativeHistoriqueRepository historiqueRepository;
    private final PasswordEncoder passwordEncoder;

    // private static final int MAX_TENTATIVE = 3;

    public AuthServiceImpl(UserRepository u,
                           UserRoleRepository r,
                           UserTentativeHistoriqueRepository h,
                           PasswordEncoder p) {
        this.userRepository = u;
        this.userRoleRepository = r;
        this.historiqueRepository = h;
        this.passwordEncoder = p;
    }

    @Override
    public void register(RegisterRequest req) {

        if (userRepository.findByEmail(req.email).isPresent()) {
            throw new ApiException("Email already exists");
        }

        UserRole roleUser = userRoleRepository
                .findByLabel("utilisateur")
                .orElseThrow(() -> new ApiException("Role USER not found"));

        User user = new User();
        user.setEmail(req.email);
        user.setPassword(passwordEncoder.encode(req.password));
        user.setNbrTentative(0);
        user.setUserRole(roleUser);

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest req) 
    {
        User user = userRepository.findByEmail(req.email)
                .orElseThrow(() -> new ApiException("User not found"));

        if (user.getNbrTentative() >= SecurityConstants.MAX_TENTATIVE) {
            throw new ApiException("Account blocked");
        }

        if (user.getBlockedAt() != null &&
            user.getBlockedAt().plusHours(24).isAfter(LocalDateTime.now())) {
            throw new ApiException("Account temporarily blocked");
        }

        if (!passwordEncoder.matches(req.password, user.getPassword())) {

            user.setNbrTentative(user.getNbrTentative() + 1);
            userRepository.save(user);

            UserTentativeHistorique hist = new UserTentativeHistorique();
            hist.setDateHistorique(LocalDateTime.now());
            hist.setUser(user);
            historiqueRepository.save(hist);

            throw new ApiException("Invalid credentials");
        }

        user.setNbrTentative(0);
        userRepository.save(user);

        AuthResponse res = new AuthResponse();
        res.token = UUID.randomUUID(); // session token (sera persisté plus tard)
        res.expiresAt = LocalDateTime.now().plusMinutes(30);

        return res;
    }
}

