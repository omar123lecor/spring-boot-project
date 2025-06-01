package com.ensah.gestion_des_stock.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
public class Livraison extends Produit{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateLiv;
    private String source;
    private String destination;
    private String type;
    private String remarque;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_commande")
    private Commande commande;

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Date getDateLiv() {
        return dateLiv;
    }

    public void setDateLiv(Date dateLiv) {
        this.dateLiv = dateLiv;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
