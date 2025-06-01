package com.ensah.gestion_des_stock.services;
import com.ensah.gestion_des_stock.model.Commande;
import com.ensah.gestion_des_stock.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository CommandeRepository;

    @Override
    public List<Commande> findByNom(String nom) {
        return CommandeRepository.findByNom(nom);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return CommandeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commande> rechercherCommandes(String nom, Long id) {
        return CommandeRepository.findByNomAndId(nom, id);
    }
    @Override
    public List<Commande> findAll() {
        return CommandeRepository.findAll();
    }


}
