package com.ensah.gestion_des_stock.Dao;

import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class Achat extends Produit {
    private Date DateAchat;
    private String Source;


    public Date getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.DateAchat = dateAchat;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        this.Source = source;
    }
}
