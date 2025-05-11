package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReceptionRepository extends JpaRepository<Reception, Long> {

    // Méthodes filterBy**()
    List<Reception> findBySourceContaining(String source);
    List<Reception> findByEntrepot(Entropot entrepot);
    List<Reception> findByDateReceptionBetween(LocalDate date1, LocalDate date2);
    List<Reception> findByNom(String nom);
    List<Reception> findByType(String type);
    @Query("SELECT r FROM Reception r " +
            "WHERE (:entrepot IS NULL OR r.entrepot = :entrepot) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR r.dateReception BETWEEN :startDate AND :endDate) " +
            "AND (:nom IS NULL OR r.nom = :nom)")
    List<Reception> searchReceptions(Entropot entrepot, LocalDate startDate, LocalDate endDate, String nom);


}