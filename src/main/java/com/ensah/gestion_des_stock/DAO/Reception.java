package com.ensah.gestion_des_stock.DAO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Reception {
    @Id
    private String numReception;
    private Date dateReception;
    private String source;
    private String entrepot;
    private String type;
    private String remarque;

    public Reception(){}

    public Reception(String numReception, Date dateReception, String source, String type, String remarque){
        this.numReception = numReception;
        this.dateReception = dateReception;
        this.source = source;
        this.type = type;
        this.remarque = remarque;
    }
    public String getNumReception() {

        return numReception;
    }
    public void setNumReception(String numReception) {

        this.numReception = numReception;
    }
    public Date getDateReception() {
        return dateReception;
    }
    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }





}
