package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.repositorys.InventaireRepository;
import com.ensah.gestion_des_stock.services.InventaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventaireServiceImpl implements InventaireService {

    @Autowired
    private InventaireRepository inventaireRepository;

    @Override
    public Inventaire ajouter(Inventaire inventaire) {
        return inventaireRepository.save(inventaire);
    }

    @Override
    public List<Inventaire> lister() {
        return inventaireRepository.findAll();
    }

    @Override
    public List<Inventaire> filtrerParEntrepot(Entropot entrepot) {
        return inventaireRepository.findByEntrepot(entrepot);
    }

    @Override
    public List<Inventaire> filtrerParDate(Date debut, Date fin) {
        return inventaireRepository.findByDateInventaireBetween(debut, fin);
    }

    @Override
    public List<Inventaire> filtrerParNomEffectuer(String motCle) {
        return inventaireRepository.findByEffectuerParContaining(motCle);
    }

    @Override
    public List<Inventaire> afficherEcart() {
        return inventaireRepository.findByEcartIsNotNull();
    }

    public List<Inventaire> searchInventaires(Entropot entrepot, Date d1, Date d2) {
        return inventaireRepository.searchInventaires(entrepot, d1, d2);
    }


    @Override
    public String telecharger() {
        // Logique d’export fictive
        return "Fichier téléchargé avec succès (simulation)";
    }
}
