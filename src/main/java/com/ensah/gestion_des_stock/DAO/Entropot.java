package com.ensah.gestion_des_stock.DAO;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class Entropot {
    @Entity
    public class Entrepot {
        @Id
        private String code;
        private String nom;
        private String address;


        // Getters, setters, constructeurs

        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }

    }

}
