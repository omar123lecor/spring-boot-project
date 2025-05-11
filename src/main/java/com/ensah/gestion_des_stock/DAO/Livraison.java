package com.ensah.gestion_des_stock.DAO;

import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Livraison {
    @Id
    private String Numlivraison;
=======

import java.util.Date;
@Entity
public class Livraison extends Produit{
>>>>>>> f697ea77c806a291560bade1966de074488235c4
    private Date DateLiv;
    private String source;
    private String Destination;
    private String Type;
    private String Remarque;


<<<<<<< HEAD
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
=======

    public Date getDateLiv() {
>>>>>>> f697ea77c806a291560bade1966de074488235c4
        return DateLiv;
    }

    public void setDateLiv(Date dateLiv) {
        this.DateLiv = dateLiv;
    }
<<<<<<< HEAD
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

=======

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        this.Destination = destination;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        this.Remarque = remarque;
    }
}
>>>>>>> f697ea77c806a291560bade1966de074488235c4
