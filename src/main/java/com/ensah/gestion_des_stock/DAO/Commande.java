package com.ensah.gestion_des_stock.DAO;

import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Commande {
    @Id
    private String NumComd;
=======

import java.util.Date;
@Entity
public class Commande extends Produit {
>>>>>>> f697ea77c806a291560bade1966de074488235c4
    private Date DateComd;
    private String Client;
    private String entrepot;
    private String Remarque;

<<<<<<< HEAD
    public Commande(){

    }
    public Commande( String NumComd, Date DateComd, String Client, String entrepot, String Remarque){
        this.NumComd = NumComd;
        this.DateComd = DateComd;
        this.Client = Client;
        this.entrepot = entrepot;
        this.Remarque = Remarque;
    }

    // Getters et Setters
    public String getNumComd() {
        return NumComd;
    }

    public void setNumComd(String numComd) {
        this.NumComd = numComd;
    }
=======
>>>>>>> f697ea77c806a291560bade1966de074488235c4

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
