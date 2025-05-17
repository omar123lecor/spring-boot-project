package com.ensah.gestion_des_stock.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

/**
 * @author $ {USER}
 **/
public class AchatForm {

    @NotBlank(message = "this field is required")
    private Date DateOfReception ;
    @NotBlank(message = "this field is required")
    private Date DateOfBuy;
    @NotBlank(message = "this field is required")
    private Long AchatNumber;
    @NotBlank(message = "this field is required")
    private String productName;
    @NotBlank(message = "this field is required")
    private String unite;
    @NotBlank(message = "this field is required")
    private int qte;
    @NotBlank(message = "this field is required")
    private String source;
    @NotBlank(message = "this field is required")
    private String magCode;

    public Date getDateOfReception() {
        return DateOfReception;
    }

    public void setDateOfReception(Date dateOfReception) {
        DateOfReception = dateOfReception;
    }

    public Date getDateOfBuy() {
        return DateOfBuy;
    }

    public void setDateOfBuy(Date dateOfBuy) {
        DateOfBuy = dateOfBuy;
    }

    public Long getAchatNumber() {
        return AchatNumber;
    }

    public void setAchatNumber(Long achatNumber) {
        AchatNumber = achatNumber;
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

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
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
