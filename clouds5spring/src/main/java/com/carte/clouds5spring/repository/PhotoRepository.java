package com.carte.clouds5spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carte.clouds5spring.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByRouteProblemeId(Integer routeProblemeId);
}
