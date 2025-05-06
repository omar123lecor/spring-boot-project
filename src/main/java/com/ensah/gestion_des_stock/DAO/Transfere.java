package com.ensah.gestion_des_stock.DAO;

import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class Transfere extends Produit{
    private Date Date_Transfere;
    private String entrepot_source;
    private String entrepot_destination;
    private String Remarque;


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
