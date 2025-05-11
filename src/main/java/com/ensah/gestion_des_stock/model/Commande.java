package com.ensah.gestion_des_stock.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Commande extends Produit {
    private Date dateComd;
    private String entrepot;
    private String client;
    private String remarque;


    public Date getDateComd() {
        return dateComd;
    }

    public void setDateComd(Date dateComd) {
        this.dateComd = dateComd;
    }

    public String
    getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(String entrepot) {
        this.entrepot = entrepot;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
