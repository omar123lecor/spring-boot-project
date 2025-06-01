package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import com.ensah.gestion_des_stock.model.Transfere;
import java.util.Date;

import java.util.List;

public interface TransfertService {

    Transfere ajouter(Transfere transfert);

    void supprimer(Long id);

    Transfere modifier(Long id, Transfere nouveau);

    List<Transfere> lister();


    List<Transfere> filtrerParDate(Date debut);
    List<Transfere> filtrerParNom(String nom);
    List<Transfere> searchByNomAndDate(String nom, Date startDate);
    Transfere getById(Long id);
}
