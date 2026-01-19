package com.carte.clouds5spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carte.clouds5spring.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByNbrTentativeGreaterThanEqual(Integer limit); // liste users bloqués
    boolean existsByFirebaseUid(String firebaseUid);
    Optional<User> findByFirebaseUid(String firebaseUid);
}
