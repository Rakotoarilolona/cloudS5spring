package com.carte.clouds5spring.repository;

import com.carte.clouds5spring.entity.HistoriqueStatusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HistoriqueStatusRouteRepository 
        extends JpaRepository<HistoriqueStatusRoute, Integer> {

    List<HistoriqueStatusRoute> findByStatusId(int statusId);
}
