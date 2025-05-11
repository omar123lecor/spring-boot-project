package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Transfere;

import java.time.LocalDate;
import java.util.List;

public interface TransfertService {

    Transfere ajouter(Transfere transfert);

    void supprimer(Long id);

    Transfere modifier(Long id, Transfere nouveau);

    List<Transfere> lister();

    List<Transfere> filtrerParSource(Entropot source);

    List<Transfere> filtrerParDestination(Entropot destination);

    List<Transfere> filtrerParDate(LocalDate debut, LocalDate fin);

    List<Transfere> filtrerParRemarque(String motCle);
}
