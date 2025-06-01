package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.repositories.EntropotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntropotServiceImpl implements EntropotService {


    @Autowired
    private EntropotRepository entrepotRepository;

    @Override
    public Entropot ajouterEntropot(Entropot entropot) {
        return entrepotRepository.save(entropot);
    }

    @Override
    public void supprimerEntropot(String code) {
        entrepotRepository.deleteById(code);
    }

    @Override
  /*  public Entropot modifierEntropot(String code, Entropot entropot) {
        Entropot existing = entrepotRepository.findById(code).orElse(null);
        if (existing != null) {
            entropot.setCode(code); // assure l'update
            return entrepotRepository.save(entropot);
        }
        return null;
    }*/
    public Entropot modifierEntropot(String code, Entropot updated) {
        Entropot existing = entrepotRepository.findById(code).orElse(null);
        if (existing != null) {
            existing.setNom(updated.getNom());
            existing.setAddress(updated.getAddress());
            // Pas besoin de modifier le code (ID)
            return entrepotRepository.save(existing);
        }
        return null;
    }

    @Override
    public List<Entropot> lister() {
        return entrepotRepository.findAll();
    }
    public Entropot getEntropotByCode(String code) {
        return entrepotRepository.findById(code).orElse(null);
    }

}
