package com.ensah.gestion_des_stock.DTO;

import java.util.Date;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


public class LivraisonForm {

    @NotNull(message = "this field is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfLivraison;


    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfCmd;

    @NotNull(message = "this field is required")
    private Long commandeNumber;


    @NotBlank(message = "this field is required")
    private String productName;

    @NotBlank(message = "this field is required")
    private String unite;

    @Min(value = 1, message = "quantity must be at least 1")
    private Long qte;

    @NotBlank(message = "this field is required")
    private String source;

    @NotBlank(message = "this field is required")
    private String destination;

    @NotBlank(message = "this field is required")
    private String remarque;

    // Getters and setters

    public Date getDateOfLivraison() {
        return dateOfLivraison;
    }

    public void setDateOfLivraison(Date dateOfLivraison) {
        this.dateOfLivraison = dateOfLivraison;
    }

    public Date getDateOfCmd() {
        return dateOfCmd;
    }

    public void setDateOfCmd(Date dateOfCmd) {
        this.dateOfCmd = dateOfCmd;
    }

    public Long getCommandeNumber() {
        return commandeNumber;
    }

    public void setCommandeNumber(Long commandeNumber) {
        this.commandeNumber = commandeNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Long getQte() {
        return qte;
    }

    public void setQte(Long qte) {
        this.qte = qte;
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

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


}
