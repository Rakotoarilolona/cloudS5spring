package com.carte.clouds5spring.repository;

import com.carte.clouds5spring.dto.FirebaseRouteProblemeDTO;
import com.carte.clouds5spring.dto.RouteProblemeDto;
import com.carte.clouds5spring.entity.RouteProbleme;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RouteProblemeRepository extends JpaRepository<RouteProbleme, Integer> {

    Optional<RouteProbleme> findByFirebaseId(String firebaseId);

    @Query("""
        SELECT new com.carte.clouds5spring.dto.RouteProblemeDto(
            rp.id,
            rp.surface,
            rp.budget,
            re.id,
            re.label,
            rs.id,
            rs.label,
            rp.problemeDescription,
            rp.longitude,
            rp.latitude,
            rp.niveau
        )
        FROM RouteProbleme rp
        LEFT JOIN rp.routeEntreprise re
        JOIN rp.routeStatus rs
    """)
    List<RouteProblemeDto> findAllDto();
}
