package com.ensah.gestion_des_stock.DTO;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO pour l'entité Entropot
 */
public class EntrepotForm {

    @NotBlank(message = "Ce champ est requis")
    private String codef;

    @NotBlank(message = "Ce champ est requis")
    private String nomf;

    @NotBlank(message = "Ce champ est requis")
    private String addressf;

    // Getters & Setters
    public String getCodef() {
        return codef;
    }

    public void setCodef(String code) {
        this.codef = code;
    }

    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nom) {
        this.nomf = nom;
    }

    public String getAddressf() {
        return addressf;
    }

    public void setAddressf(String address) {
        this.addressf = address;
    }
}
