package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Entropot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntropotRepository extends JpaRepository<Entropot, String> {
    Optional<Entropot> findByNom(String nom);

}

