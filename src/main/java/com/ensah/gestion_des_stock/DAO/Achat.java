package com.ensah.gestion_des_stock.DAO;

import java.util.Date;

public class Achat {
    private String NumAchat;
    private Date DateAchat;
    private String Source;

    public Achat() {
    }


    public Achat(String numAchat, Date dateAchat, String source) {
        this.NumAchat = numAchat;
        this.DateAchat = dateAchat;
        this.Source = source;
    }


    public String getNumAchat() {
        return NumAchat;
    }

    public void setNumAchat(String numAchat) {
        this.NumAchat = numAchat;
    }

    public Date getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.DateAchat = dateAchat;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        this.Source = source;
    }
}

