package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import com.ensah.gestion_des_stock.repositories.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    @Autowired
    private ReceptionRepository receptionRepository;



    @Override
    public Reception findByAchatt(Long id){
        return receptionRepository.findByAchat(id).orElse(null);
    }

    @Override
    public Reception ajouter(Reception reception) {
        return receptionRepository.save(reception);
    }

    @Override
    public List<Reception> allReceptions() {
        return receptionRepository.findAll();
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
            ancienne.setEntropot(nouvelle.getEntropot());
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
        return receptionRepository.findByEntropot(entrepot);
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
    public List<Reception> filtrerParDate(Date debut) {
        return receptionRepository.findByDateReception(debut);
    }

    // Convertisseur java.util.Date → java.time.LocalDate
    public List<Reception> searchReceptions(Entropot e,Date d2, String nom) {
        return receptionRepository.searchReceptions(e,d2, nom);
    }
    @Override
    public List<Reception> dynamiqueR(Date date,Date date1,String nom,String magcode){
        return receptionRepository.searchFlexible(nom,magcode,date,date1);
    }
    @Override
    public Reception filterParId(Long id){
        return receptionRepository.findById(id).orElse(null);
    }

    @Override
    public void saveReception(Reception reception){
        receptionRepository.save(reception);
    }


}