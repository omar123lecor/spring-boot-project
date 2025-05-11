package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Transfere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransfertRepository extends JpaRepository<Transfere, Long> {

    // 🔍 Méthodes filterBy**()
       List<Transfere> findByDateTransfereBetween(LocalDate dateDebut, LocalDate dateFin);
       List<Transfere> findByNom(String nom);

       @Query("SELECT t FROM Transfere t " +
               "WHERE (:nom IS NULL OR t.nom = :nom) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR t.Date_Transfere BETWEEN :startDate AND :endDate)")
       List<Transfere> searchByNomAndDate(String nom, LocalDate startDate, LocalDate endDate);

}

