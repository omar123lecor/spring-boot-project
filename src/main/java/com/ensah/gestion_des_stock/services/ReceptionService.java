package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReceptionService {

    Reception ajouter(Reception reception);

    void supprimer(Long id);

    Reception modifier(Long id, Reception reception);

    List<Reception> lister();

    List<Reception> filtrerParSource(String source);

    List<Reception> filtrerParEntrepot(Entropot entrepot);

    List<Reception> filtrerParType(String type);

    List<Reception> filtrerParNom(String nom);

    List<Reception> filtrerParDate(Date debut);
    List<Reception> searchReceptions(Entropot entrepot,Date date, String nom);

}

