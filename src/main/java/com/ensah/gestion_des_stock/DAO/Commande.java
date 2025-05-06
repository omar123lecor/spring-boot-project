package com.ensah.gestion_des_stock.DAO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Commande {
    @Id
    private String NumComd;
    private Date DateComd;
    private String Client;
    private String entrepot;
    private String Remarque;

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
