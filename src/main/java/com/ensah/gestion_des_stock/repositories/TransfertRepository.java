package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Transfere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransfertRepository extends JpaRepository<Transfere, Long> {


    // 🔍 Méthodes filterBy**()
       List<Transfere> findByDateTransfere(Date date);
       List<Transfere> findByNom(String nom);

       @Query("SELECT t FROM Transfere t " +
               "WHERE (:nom IS NULL OR t.nom = :nom) " +
            "AND (:date IS NULL OR t.dateTransfere = :date)")
       List<Transfere> searchByNomAndDate(@Param("nom") String nom,@Param("date") Date date);

}

