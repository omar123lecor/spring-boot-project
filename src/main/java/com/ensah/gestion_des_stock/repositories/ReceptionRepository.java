package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, Long> {

    // Méthodes filterBy**()
    List<Reception> findBySourceContaining(String source);
    @Query("select r from Reception  r where r.achat.id = :id ")
    Optional<Reception> findByAchat(@Param("id") Long id);

    List<Reception> findByEntropot(Entropot entrepot);
    List<Reception> findByDateReception(Date date);
    List<Reception> findByNom(String nom);
    List<Reception> findByType(String type);
    @Query("SELECT r FROM Reception r " +
            "WHERE (:entropot IS NULL OR r.entropot = :entropot) " +
            "AND (:date IS NULL OR r.dateReception = :date) " +
            "AND (:nom IS NULL OR r.nom = :nom)")
    List<Reception> searchReceptions(@Param("entropot") Entropot entropot, @Param("date") Date date, @Param("nom") String nom);


    @Query("SELECT r FROM Reception r WHERE " +
            "(:produit IS NULL OR r.nom LIKE CONCAT('%',:produit,'%')) AND " +
            "(:entrepot IS NULL OR r.entropot.code LIKE CONCAT('%',:entrepot,'%')) AND " +
            "(:startDate IS NULL OR r.dateReception >= :startDate) AND " +
            "(:endDate IS NULL OR r.dateReception <= :endDate)")
    List<Reception> searchFlexible(
            @Param("produit") String produit,
            @Param("entrepot") String entrepot,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}