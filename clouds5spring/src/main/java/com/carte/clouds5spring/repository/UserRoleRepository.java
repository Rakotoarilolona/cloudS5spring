package com.carte.clouds5spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carte.clouds5spring.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByLabel(String label);
}
