package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import com.ensah.gestion_des_stock.model.Transfere;
import com.ensah.gestion_des_stock.repositories.TransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransfertServiceImpl implements TransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    @Override
    public Transfere ajouter(Transfere transfert) {
        return transfertRepository.save(transfert);
    }

    @Override
    public void supprimer(Long id) {
        transfertRepository.deleteById(id);
    }

    @Override
    public Transfere modifier(Long id, Transfere nouveau) {
        Optional<Transfere> ancienOpt = transfertRepository.findById(id);
        if (ancienOpt.isPresent()) {
            Transfere ancien = ancienOpt.get();

            ancien.setDate_Transfere(nouveau.getDate_Transfere());
            ancien.setEntrepot_source(nouveau.getEntrepot_source());
            ancien.setEntrepot_destination(nouveau.getEntrepot_destination());
            ancien.setRemarque(nouveau.getRemarque());

            ancien.setNom(nouveau.getNom());
            ancien.setQte(nouveau.getQte());
            ancien.setUnite(nouveau.getUnite());

            return transfertRepository.save(ancien);
        } else {
            return null;
        }
    }

    @Override
    public List<Transfere> lister() {
        return transfertRepository.findAll();
    }

    @Override
    public List<Transfere> filtrerParDate(LocalDate debut, LocalDate fin) {
        return transfertRepository.findByDateTransfereBetween(debut, fin);
    }

    @Override
    public List<Transfere> filtrerParNom(String nom) {
        return transfertRepository.findByNom(nom);
    }
    public List<Transfere> searchByNomAndDate(String nom, LocalDate debut, LocalDate fin) {
        return transfertRepository.searchByNomAndDate(nom, debut, fin);
    }

}

