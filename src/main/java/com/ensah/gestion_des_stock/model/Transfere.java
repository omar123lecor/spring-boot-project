package com.ensah.gestion_des_stock.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import java.util.Date;
@Entity
public class Transfere extends Produit{
    private Date dateTransfere;
    @ManyToOne
    @JoinColumn(name = "codeS")
    private Entropot entrepot_source;
    @ManyToOne
    @JoinColumn(name = "codeD")
    private Entropot entrepot_destination;

    private String Remarque;


    public Date getDate_Transfere() {
        return dateTransfere;
    }

    public void setDate_Transfere(Date date_Transfere) {
        this.dateTransfere = date_Transfere;
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
