package com.ensah.gestion_des_stock.model;

import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class Commande extends Produit {
    private Date DateComd;
    private String Client;
    private String entrepot;
    private String Remarque;


    public Date getDateComd() {
        return DateComd;
    }

    public void setDateComd(Date dateComd) {
        this.DateComd = dateComd;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        this.Client = client;
    }

    public String getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(String entrepot) {
        this.entrepot = entrepot;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        this.Remarque = remarque;
    }
}
