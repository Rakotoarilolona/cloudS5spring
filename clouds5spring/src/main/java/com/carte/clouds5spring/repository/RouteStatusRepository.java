package com.carte.clouds5spring.repository;

import com.carte.clouds5spring.entity.RouteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteStatusRepository extends JpaRepository<RouteStatus, Integer> {
    RouteStatus findByLabel(String label);
}
