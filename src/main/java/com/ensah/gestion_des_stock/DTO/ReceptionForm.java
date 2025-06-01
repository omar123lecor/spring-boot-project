package com.ensah.gestion_des_stock.DTO;

import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.model.Entropot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author $ {USER}
 **/
public class ReceptionForm {
    @NotNull(message = "this field is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateR;
    @NotNull(message = "This field is required")
    private Long id;
    @NotEmpty(message = "This field is required")
    private String nom;
    @NotEmpty(message = "This field is required")
    private String unite;
    @NotNull(message = "This field is required")
    private Long qte;
    @NotEmpty(message = "This field is required")
    private String source;
    @NotNull(message = "This field is required")
    private String magcode;
    @NotEmpty(message = "This field is required")
    private String remarque;
    public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

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

    public Long getQte() {
        return qte;
    }

    public void setQte(Long qte) {
        this.qte = qte;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String sourve) {
        this.source = sourve;
    }
   public String getMagcode() {
        return magcode;
    }

    public void setMagcode(String magcode) {
        this.magcode = magcode;
    }
    public String getRemarque() {
        return remarque;
    }
    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
