package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Livraison;
import com.ensah.gestion_des_stock.repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class LivraisonServiceImpl implements LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Override
    public Livraison ajouterLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    @Override
    public void supprimerLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }

    @Override
    public Livraison modifierLivraison(Long id, Livraison livraison) {
        Livraison existing = livraisonRepository.findById(id).orElse(null);
        if (existing != null) {
            livraison.setId(id); // assure l'update
            return livraisonRepository.save(livraison);
        }
        return null;
    }
    @Override
    public List<Livraison> lister() {
        return livraisonRepository.findAll();
    }

    @Override
    public Livraison getLivraisonById(Long id) {
        return livraisonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Livraison> findByDateLiv(Date dateLiv) {
        return livraisonRepository.findByDateLiv(dateLiv);
    }

    @Override
    public List<Livraison> findByNom(String nom) {
        return livraisonRepository.findByNom(nom);
    }

    @Override
    public List<Livraison> findByEntrepot(String entrepot) {
        return livraisonRepository.findByDestination(entrepot);
    }
    @Override
    public List<Livraison> rechercherLivraisons(String nom, String entrepot, Date dateLiv) {
        return livraisonRepository.chercherLivraisons(nom, entrepot, dateLiv);
    }
    @Override
    public Livraison findByCommande(Long id){
        return livraisonRepository.findByCommande(id).orElse(null);
    }
}
