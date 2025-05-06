package com.ensah.gestion_des_stock.Dao;

import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class Commande extends Produit {
    private Date DateComd;
    private String Client;
    private String entrepot;
    private String Remarque;

    // Constructeur par défaut
    public Commande() {
    }

    // Constructeur avec tous les champs
    public Commande(String NumComd, Date DateComd, String Client, String entrepot, String Remarque) {
        this.DateComd = DateComd;
        this.Client = Client;
        this.entrepot = entrepot;
        this.Remarque = Remarque;
    }


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
