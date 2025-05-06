package com.ensah.gestion_des_stock.DAO;

import java.util.Date;

public class Livraison {

    private String Numlivraison;
    private Date DateLiv;
    private String source;
    private String Destination;
    private String Type;
    private String Remarque;


    public Livraison(){

    }
    public Livraison(String numlivraison, Date dateLiv, String source, String destination, String type, String remarque) {
        this.Numlivraison = numlivraison;
        this.DateLiv = dateLiv;
        this.source = source;
        this.Destination = destination;
        this.Type = type;
        this.Remarque = remarque;
    }

    public String getNumlivraison(){
        return Numlivraison;
    }

    public void setNumlivraison(String numlivraison){
        this.Numlivraison = numlivraison;

    }
    public Date getDateLiv(){
        return DateLiv;
    }

    public void setDateLiv(Date dateLiv) {
        this.DateLiv = dateLiv;
    }
    public String getSource(){
        return source;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getDestination(){
        return Destination;
    }

    public String getType(){
        return Type;
    }
    public void setType(String type){
        this.Type = type;
    }
    public String getRemarque(){
        return Remarque;
    }
    public void setRemarque(String remarque){
        this.Remarque = remarque;
    }
    public void setDestination(String Destination){
        this.Destination = Destination;
    }

}

