package com.ensah.gestion_des_stock.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InventaireDto {

    private Long id;

    @NotNull(message = "L'entrepôt est requis")
    private String entrepotId; // le code de l'entrepôt (clé étrangère)

    @NotNull(message = "La date de l'inventaire est requise")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateInventaire;

    @NotNull(message = "Le champ 'effectué par' est requis")
    private String effectuePar;

    @NotNull(message = "Le champ 'validé par' est requis")
    private String validePar;

    @NotNull(message = "Le champ 'écart' est requis")
    private Integer ecart;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEntrepotId() { return entrepotId; }
    public void setEntrepotId(String entrepotId) { this.entrepotId = entrepotId; }

    public Date getDateInventaire() { return dateInventaire; }
    public void setDateInventaire(Date dateInventaire) { this.dateInventaire = dateInventaire; }

    public String getEffectuePar() { return effectuePar; }
    public void setEffectuePar(String effectuePar) { this.effectuePar = effectuePar; }

    public String getValidePar() { return validePar; }
    public void setValidePar(String validePar) { this.validePar = validePar; }

    public Integer getEcart() { return ecart; }
    public void setEcart(Integer ecart) { this.ecart = ecart; }
}
