package com.ensah.gestion_des_stock.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TransfertForm {
    private Long id;
    private String nom;
    private String unite;
    @NotNull(message = "La quantité ne peut pas être vide")
    private int qte;
    @NotNull(message = "La date est requise")
    private Date dateTransfere;
    @NotBlank(message = "L'entrepôt source est requis")
    private String entrepotSourceId;
    @NotBlank(message = "L'entrepôt destination est requis")
    private String entrepotDestinationId;
    private String remarque;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Date getDateTransfere() {
        return dateTransfere;
    }

    public void setDateTransfere(Date dateTransfere) {
        this.dateTransfere = dateTransfere;
    }

    public String getEntrepotSourceId() {
        return entrepotSourceId;
    }

    public void setEntrepotSourceId(String entrepotSourceId) {
        this.entrepotSourceId = entrepotSourceId;
    }

    public String getEntrepotDestinationId() {
        return entrepotDestinationId;
    }

    public void setEntrepotDestinationId(String entrepotDestinationId) {
        this.entrepotDestinationId = entrepotDestinationId;
    }


    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

}
