package com.carte.clouds5spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carte.clouds5spring.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
