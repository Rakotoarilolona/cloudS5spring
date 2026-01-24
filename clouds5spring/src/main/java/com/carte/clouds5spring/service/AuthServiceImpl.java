package com.carte.clouds5spring.service;

import java.time.LocalDateTime;

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
import com.carte.clouds5spring.security.util.JwtUtil;

@Service
@Component
public class AuthServiceImpl implements AuthService 
{
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserTentativeHistoriqueRepository historiqueRepository;
    private final JwtUtil jwtUtil;


    // private static final int MAX_TENTATIVE = 3;

    public AuthServiceImpl(UserRepository u,
                           UserRoleRepository r,
                           UserTentativeHistoriqueRepository h,
                        JwtUtil j) {
        this.userRepository = u;
        this.userRoleRepository = r;
        this.historiqueRepository = h;
        this.jwtUtil = j;
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
        user.setPassword(req.password);
        user.setNbrTentative(0);
        user.setUserRole(roleUser);
        user.setPseudo(req.pseudo);

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

        if (!req.password.equals(user.getPassword())) 
        {
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

        String jwt = jwtUtil.generateToken(user.getEmail());

        AuthResponse res = new AuthResponse();
        res.token = jwt;
        res.expiresAt = LocalDateTime.now().plusMinutes(30);
        res.role = user.getUserRole().getLabel();

        return res;

    }
}

