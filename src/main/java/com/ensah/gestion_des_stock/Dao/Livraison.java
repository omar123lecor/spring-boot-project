package com.ensah.gestion_des_stock.Dao;

import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class Livraison extends Produit{
    private Date DateLiv;
    private String source;
    private String Destination;
    private String Type;
    private String Remarque;



    public Date getDateLiv() {
        return DateLiv;
    }

    public void setDateLiv(Date dateLiv) {
        this.DateLiv = dateLiv;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        this.Destination = destination;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        this.Remarque = remarque;
    }
}
