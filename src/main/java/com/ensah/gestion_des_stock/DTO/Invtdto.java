package com.ensah.gestion_des_stock.DTO;
public class Invtdto {

    private String produit;
    private String unite;
    private Long quantiteAvant;
    private Long quantiteApres;
    private Long ecart;

    // Constructeur vide
    public Invtdto() {
    }

    // Constructeur avec tous les champs
    public Invtdto(String produit, String unite, Long quantiteAvant, Long quantiteApres) {
        this.produit = produit;
        this.unite = unite;
        this.quantiteAvant = quantiteAvant;
        this.quantiteApres = quantiteApres;
        this.ecart = Math.abs(quantiteApres - quantiteAvant);
    }

    // Getters et setters
    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public double getQuantiteAvant() {
        return quantiteAvant;
    }

    public void setQuantiteAvant(Long quantiteAvant) {
        this.quantiteAvant = quantiteAvant;
    }

    public double getQuantiteApres() {
        return quantiteApres;
    }

    public void setQuantiteApres(Long quantiteApres) {
        this.quantiteApres = quantiteApres;
    }

    public double getEcart() {
        return ecart;
    }

    public void setEcart(Long ecart) {
        this.ecart = ecart;
    }
}
