package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReceptionService {

    Reception ajouter(Reception reception);

    public List<Reception> allReceptions();
    public void saveReception(Reception reception);
    public void supprimer(Long id);
    public List<Reception> dynamiqueR(Date date,Date date1,String nom,String magcode);

    public Reception modifier(Long id, Reception reception);

    public List<Reception> lister();

    public List<Reception> filtrerParSource(String source);

    public List<Reception> filtrerParEntrepot(Entropot entrepot);

    public List<Reception> filtrerParType(String type);

    public List<Reception> filtrerParNom(String nom);

    public List<Reception> filtrerParDate(Date debut);
    public  Reception filterParId(Long id);
    public Reception findByAchatt(Long id);
    public List<Reception> searchReceptions(Entropot entrepot,Date date, String nom);

}

