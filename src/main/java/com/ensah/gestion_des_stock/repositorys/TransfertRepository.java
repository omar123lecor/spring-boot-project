package com.ensah.gestion_des_stock.repositorys;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Transfere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransfertRepository extends JpaRepository<Transfere, Long> {

    // 🔍 Méthodes filterBy**()
    List<Transfere> findByEntrepotSource(Entropot source);
    List<Transfere> findByEntrepotDestination(Entropot destination);
    List<Transfere> findByDateTransfereBetween(LocalDate dateDebut, LocalDate dateFin);
    List<Transfere> findByRemarqContaining(String motCle);
}
