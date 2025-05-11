package com.ensah.gestion_des_stock.repositorys;

import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.model.Entropot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface InventaireRepository extends JpaRepository<Inventaire, Long> {

    List<Inventaire> findByEntrepot(Entropot entrepot);

    List<Inventaire> findByDateInventaireBetween(Date date1, Date date2);

    List<Inventaire> findByEffectuerParContaining(String nom);

    List<Inventaire> findByEcartIsNotNull();

    @Query("SELECT i FROM Inventaire i " +
            "WHERE (:entrepot IS NULL OR i.entropot = :entrepot) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR i.dateInventaire BETWEEN :startDate AND :endDate)")
    List<Inventaire> searchInventaires(Entropot entrepot, Date startDate, Date endDate);

}