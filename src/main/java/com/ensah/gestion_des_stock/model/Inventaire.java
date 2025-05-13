package com.ensah.gestion_des_stock.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Inventaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateInventaire;
    private String effectuePar;
    private String validePar;
    private int ecart;
    @ManyToOne
    @JoinColumn(name = "code")
    private Entropot entropot;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDateInventaire() {
        return dateInventaire;
    }
    public void setDateInventaire(Date dateInventaire) {
        this.dateInventaire = dateInventaire;
    }
    public String getEffectuePar() {
        return effectuePar;
    }
    public void setEffectuePar(String effectuePar) {
        this.effectuePar = effectuePar;
    }
    public String getValidePar() {
        return validePar;
    }
    public void setValidePar(String validePar) {
        this.validePar = validePar;
    }

    public int getEcart() {
        return ecart;
    }
    public void setEcart(int ecart) {
        this.ecart = ecart;
    }
    public Entropot getEntropot() {
        return entropot;
    }
    public void setEntropot(Entropot entropot) {
        this.entropot = entropot;
    }

}
