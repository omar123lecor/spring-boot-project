package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.model.Entropot;

import java.util.Date;
import java.util.List;

public interface InventaireService {

    Inventaire ajouter(Inventaire inventaire);

    List<Inventaire> lister();

    List<Inventaire> filtrerParEntrepot(Entropot entrepot);

    List<Inventaire> filtrerParDate(Date debut, Date fin);

    List<Inventaire> filtrerParNomEffectuer(String motCle);

    List<Inventaire> afficherEcart();

    List<Inventaire> searchInventaires(Entropot entrepot, Date startDate, Date endDate);


    // méthode fictive pour simuler un export
    String telecharger();

}
