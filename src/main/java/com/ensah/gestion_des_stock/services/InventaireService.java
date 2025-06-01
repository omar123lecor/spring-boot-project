package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.model.Entropot;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface InventaireService {

    Inventaire ajouter(Inventaire inventaire);

    List<Inventaire> lister();

    List<Inventaire> filtrerParEntrepot(Entropot entrepot);

    List<Inventaire> filtrerParDate(Date debut, Date fin);

    List<Inventaire> filtrerParNomEffectuer(String motCle);

    List<Inventaire> afficherEcart();

    List<Inventaire> searchInventaires(Entropot entropot, Date startDate, Date endDate);


    Inventaire getById(Long id);
    void supprimer(Long id);

    public void telecharger(HttpServletResponse response)  throws IOException;
    List<Inventaire> filtrerParEntrepotEtDates(Entropot entrepot, Date debut, Date fin);

}
