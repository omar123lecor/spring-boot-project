package com.ensah.gestion_des_stock.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author $ {USER}
 **/
public class AchatForm {

    @NotNull(message = "this field is required",groups = {HorsAchatGroup.class,AchatGroup.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfReception ;
    @NotNull(message = "this field is required",groups = {AchatGroup.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBuy;
    @NotNull(message = "this field is required",groups = {AchatGroup.class})
    private Long achatNumber;
    @NotBlank(message = "this field is required",groups = {HorsAchatGroup.class,AchatGroup.class})
    private String productName;
    @NotBlank(message = "this field is required",groups = {HorsAchatGroup.class,AchatGroup.class})
    private String unite;
    @NotNull(message = "this field is required",groups = {HorsAchatGroup.class,AchatGroup.class})
    private Long qte;
    @NotBlank(message = "this field is required",groups = {HorsAchatGroup.class,AchatGroup.class})
    private String source;
    @NotBlank(message = "this field is required",groups = {HorsAchatGroup.class,AchatGroup.class})
    private String magCode;

    @NotEmpty(message = "this field is required",groups = {AchatGroup.class})
    private String remarque;
    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    public Date getDateOfReception() {
        return dateOfReception;
    }

    public void setDateOfReception(Date dateOfReception) {
        this.dateOfReception = dateOfReception;
    }

    public Date getDateOfBuy() {
        return dateOfBuy;
    }

    public void setDateOfBuy(Date dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
    }

    public Long getAchatNumber() {
        return achatNumber;
    }

    public void setAchatNumber(Long achatNumber) {
        this.achatNumber = achatNumber;
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

    public String getMagCode() {
        return magCode;
    }

    public void setMagCode(String magCode) {
        this.magCode = magCode;
    }
}