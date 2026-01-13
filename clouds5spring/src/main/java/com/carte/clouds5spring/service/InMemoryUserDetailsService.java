package com.carte.clouds5spring.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryUserDetailsService implements UserDetailsService 
{
    
    // Liste des utilisateurs en mémoire pour les tests
    private final List<UserDetails> users = List.of(
        // Utilisateur 1 : admin
        User.withUsername("admin")
            .password("{noop}admin")  // {noop} = pas de cryptage pour les tests
            .roles("USER", "ADMIN")      // Rôles de l'utilisateur
            .build(),
        
        // Utilisateur 2 : user normal
        User.withUsername("user")
            .password("{noop}user123")
            .roles("USER")
            .build()
    );

    /**
     * Implémentation de la méthode obligatoire de l'interface UserDetailsService
     * Spring Security appelle cette méthode pour vérifier les utilisateurs
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        // Recherche l'utilisateur dans la liste
        return users.stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + username));
    }
}