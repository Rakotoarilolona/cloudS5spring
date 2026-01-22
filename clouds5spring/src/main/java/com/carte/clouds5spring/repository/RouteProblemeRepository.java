package com.carte.clouds5spring.repository;

import com.carte.clouds5spring.entity.RouteProbleme;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteProblemeRepository extends JpaRepository<RouteProbleme, Integer> {

    Optional<RouteProbleme> findByFirebaseId(String firebaseId);


}
