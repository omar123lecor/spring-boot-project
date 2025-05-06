package com.ensah.gestion_des_stock.DAO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Transfere {
    @Id
    private String id_transfere;
    private Date Date_Transfere;
    private String entrepot_source;
    private String entrepot_destination;
    private String Remarque;


    public Transfere() {
    }

    public Transfere(String id_transfere, Date date_Transfere, String entrepot_source, String entrepot_destination, String remarque) {
        this.id_transfere = id_transfere;
        this.Date_Transfere = date_Transfere;
        this.entrepot_source = entrepot_source;
        this.entrepot_destination = entrepot_destination;
        this.Remarque = remarque;
    }

    // Getters et Setters
    public String getId_transfere() {
        return id_transfere;
    }

    public void setId_transfere(String id_transfere) {
        this.id_transfere = id_transfere;
    }

    public Date getDate_Transfere() {
        return Date_Transfere;
    }

    public void setDate_Transfere(Date date_Transfere) {
        this.Date_Transfere = date_Transfere;
    }

    public String getEntrepot_source() {
        return entrepot_source;
    }

    public void setEntrepot_source(String entrepot_source) {
        this.entrepot_source = entrepot_source;
    }

    public String getEntrepot_destination() {
        return entrepot_destination;
    }

    public void setEntrepot_destination(String entrepot_destination) {
        this.entrepot_destination = entrepot_destination;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        this.Remarque = remarque;
    }

}
