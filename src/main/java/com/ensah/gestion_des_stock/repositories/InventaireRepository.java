package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.model.Entropot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InventaireRepository extends JpaRepository<Inventaire, Long> {

    List<Inventaire> findByEntropot(Entropot entrepot);

    List<Inventaire> findByDateInventaireBetween(Date date1, Date date2);

    List<Inventaire> findByEffectueParContaining(String nom);

    List<Inventaire> findByEcartIsNotNull();

    @Query("SELECT i FROM Inventaire i " +
            "WHERE (:entropot IS NULL OR i.entropot = :entropot) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR i.dateInventaire BETWEEN :startDate AND :endDate)")
    List<Inventaire> searchInventaires(Entropot entropot, Date startDate, Date endDate);

}