package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Livraison;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LivraisonService {
    Livraison ajouterLivraison(Livraison livraison);
    void supprimerLivraison(Long id);
    Livraison modifierLivraison(Long id, Livraison livraison);
    Livraison getLivraisonById(Long id);
    List<Livraison> findByDateLiv(Date dateLiv);
    List<Livraison> findByNom(String nom);
    List<Livraison> findByEntrepot(String entrepot);
    List<Livraison> lister();
    List<Livraison> rechercherLivraisons(String nom, String entrepot, Date dateLiv);
    Livraison findByCommande(Long id);
}
