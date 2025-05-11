package com.ensah.gestion_des_stock.repositorys;


import com.ensah.gestion_des_stock.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByNom(String nom);
    @Query("""
    SELECT c FROM Commande c 
    WHERE (:nom IS NULL OR c.nom = :nom) 
    AND (:id IS NULL OR c.id = :id) 
    """)
    List<Commande> findByNomAndId(@Param("nom") String nom, @Param("id") Long id);

}
