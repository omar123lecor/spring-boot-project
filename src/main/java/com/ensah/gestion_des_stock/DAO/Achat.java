package com.ensah.gestion_des_stock.DAO;

import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Achat{
    @Id
    private String NumAchat;
    private Date DateAchat;
    private String Source;

    public Achat() {
    }


    public Achat(String numAchat, Date dateAchat, String source) {
        this.NumAchat = numAchat;
        this.DateAchat = dateAchat;
        this.Source = source;
    }


    public String getNumAchat() {
        return NumAchat;
    }

    public void setNumAchat(String numAchat) {
        this.NumAchat = numAchat;
    }
=======

import java.util.Date;
@Entity
public class Achat extends Produit {
    private Date DateAchat;
    private String Source;

>>>>>>> f697ea77c806a291560bade1966de074488235c4

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
<<<<<<< HEAD

=======
>>>>>>> f697ea77c806a291560bade1966de074488235c4
