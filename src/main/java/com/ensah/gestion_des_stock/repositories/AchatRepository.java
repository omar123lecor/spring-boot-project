package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Queue;

/**
 * @author $ {USER}
 **/
public interface AchatRepository extends JpaRepository<Achat,Long> {
    @Query("""
    SELECT a FROM Achat a 
    WHERE (:nom IS NULL OR a.nom = :nom) 
    AND (:id IS NULL OR a.id=:id)""")
    List<Achat> findByIdOrNom(Long id,String nom);

}
