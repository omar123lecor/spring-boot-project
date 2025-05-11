package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import com.ensah.gestion_des_stock.repositorys.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    @Autowired
    private ReceptionRepository receptionRepository;

    @Override
    public Reception ajouter(Reception reception) {
        return receptionRepository.save(reception);
    }

    @Override
    public void supprimer(Long id) {
        receptionRepository.deleteById(id);
    }

    @Override
    public Reception modifier(Long id, Reception nouvelle) {
        Optional<Reception> ancienneOpt = receptionRepository.findById(id);
        if (ancienneOpt.isPresent()) {
            Reception ancienne = ancienneOpt.get();

            ancienne.setDateReception(nouvelle.getDateReception());
            ancienne.setSource(nouvelle.getSource());
            ancienne.setEntrepot(nouvelle.getEntrepot());
            ancienne.setType(nouvelle.getType());

            ancienne.setNom(nouvelle.getNom());
            ancienne.setQte(nouvelle.getQte());
            ancienne.setUnite(nouvelle.getUnite());

            return receptionRepository.save(ancienne);
        } else {
            return null;
        }
    }

    @Override
    public List<Reception> lister() {
        return receptionRepository.findAll();
    }

    @Override
    public List<Reception> filtrerParSource(String source) {
        return receptionRepository.findBySourceContaining(source);
    }

    @Override
    public List<Reception> filtrerParEntrepot(Entropot entrepot) {
        return receptionRepository.findByEntrepot(entrepot);
    }

    @Override
    public List<Reception> filtrerParType(String type) {
        return receptionRepository.findByType(type);
    }
    @Override
    public List<Reception> filtrerParNom(String nom) {
        return receptionRepository.findByNom(nom);
    }

    @Override
    public List<Reception> filtrerParDate(java.util.Date debut, java.util.Date fin) {
        return receptionRepository.findByDateReceptionBetween(
                convertToLocalDate(debut), convertToLocalDate(fin)
        );
    }

    // Convertisseur java.util.Date → java.time.LocalDate
    private static LocalDate convertToLocalDate(java.util.Date date) {
        return new Date(date.getTime()).toLocalDate();
    }
    public List<Reception> searchReceptions(Entropot e, LocalDate d1, LocalDate d2, String nom) {
        return receptionRepository.searchReceptions(e, d1, d2, nom);
    }

}
