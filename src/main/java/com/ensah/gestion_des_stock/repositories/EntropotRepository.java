package com.ensah.gestion_des_stock.repositories;

import com.ensah.gestion_des_stock.model.Entropot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntropotRepository extends JpaRepository<Entropot, Long> {
}
