package com.carte.clouds5spring.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.carte.clouds5spring.entity.PrixForfaitaire;

public interface PrixForfaitaireRepository extends JpaRepository<PrixForfaitaire, Integer> {

    @Modifying
    @Transactional
    @Query(
	    value = "insert into prixforfaitaire (id, montant) values (1, :montant) " +
		    "on conflict (id) do update set montant = excluded.montant",
	    nativeQuery = true
    )
    int upsertMontantId1(@Param("montant") BigDecimal montant);
}
