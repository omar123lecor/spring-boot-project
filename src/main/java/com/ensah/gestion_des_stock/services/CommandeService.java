package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Commande;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommandeService {
    Commande getCommandeById(Long id);
    List<Commande> findByNom(String nom);
    List<Commande> rechercherCommandes(String nom, Long id);
    List<Commande> findAll();

}
