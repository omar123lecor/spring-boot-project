package com.ensah.gestion_des_stock.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;

@Entity
public class Reception extends Produit {
    private Date dateReception;
    private String source;
    //plusieurs réceptions peuvent être associées à un seul entrepôt
    @ManyToOne
    @JoinColumn(name = "code")  // colonne FK
    private Entropot entropot;
    private String type;
    private String remarque;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_commande")
    private Achat achat ;
    public Reception(){}

    public Reception(Date dateReception, String source, String type, String remarque){
        this.dateReception = dateReception;
        this.source = source;
        this.type = type;
        this.remarque = remarque;
    }
    public Date getDateReception() {
        return dateReception;
    }
    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
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

    public Entropot getEntropot() {
        return entropot;
    }

    public void setEntropot(Entropot entropot) {
        this.entropot = entropot;
    }

    public Achat getAchat() {
        return achat;
    }

    public void setAchat(Achat achat) {
        this.achat = achat;
    }
}