package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Entropot;

import java.util.List;

public interface EntropotService {
    void supprimerEntropot(String code);

    Entropot modifierEntropot(String code, Entropot entropot);

    List<Entropot> lister();
    Entropot ajouterEntropot(Entropot entropot);
    Entropot getEntropotByCode(String code);

    Entropot getEntropotByNom(String nom);
}


