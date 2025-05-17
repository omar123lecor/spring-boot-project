package com.ensah.gestion_des_stock.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author $ {USER}
 **/
@MappedSuperclass
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Product name is required")
    @Column(nullable = false)
    private String nom;
    @NotEmpty(message = "Product unite is required")
    private String unite;
    @NotEmpty(message = "Product qte is required")
    private int qte;

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
}
