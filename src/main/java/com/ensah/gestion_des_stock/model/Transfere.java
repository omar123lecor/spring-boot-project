package com.ensah.gestion_des_stock.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Transfere extends Produit{
    private Date Date_Transfere;
    @ManyToOne
    @JoinColumn(name = "code")
    private Entropot entrepot_source;
    @ManyToOne
    @JoinColumn(name = "code")
    private Entropot entrepot_destination;
    private String Remarque;


    public Date getDate_Transfere() {
        return Date_Transfere;
    }

    public void setDate_Transfere(Date date_Transfere) {
        this.Date_Transfere = date_Transfere;
    }

    public Entropot getEntrepot_source() {
        return entrepot_source;
    }

    public void setEntrepot_source(Entropot entrepot_source) {
        this.entrepot_source = entrepot_source;
    }

    public Entropot getEntrepot_destination() {
        return entrepot_destination;
    }

    public void setEntrepot_destination(Entropot entrepot_destination) {
        this.entrepot_destination = entrepot_destination;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        this.Remarque = remarque;
    }
}
