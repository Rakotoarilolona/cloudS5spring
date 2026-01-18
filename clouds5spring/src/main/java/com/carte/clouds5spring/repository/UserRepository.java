package com.carte.clouds5spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carte.clouds5spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
