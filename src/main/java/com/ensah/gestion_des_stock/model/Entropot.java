package com.ensah.gestion_des_stock.model;

import jakarta.persistence.OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;



import java.util.Set;

@Entity
public class Entropot {
        @Id
        private String code;
        private String nom;
        private String address;
        @OneToMany(mappedBy = "entropot")
        private Set<Reception> receptions;
        @OneToMany(mappedBy = "entropot")
        private Set<Inventaire> inventaires;

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

        public Set<Reception> getReceptions() {
            return receptions;
        }

        public void setReceptions(Set<Reception> receptions) {
            this.receptions = receptions;
        }

        public Set<Inventaire> getInventaires() {
            return inventaires;
        }

        public void setInventaires(Set<Inventaire> inventaires) {
            this.inventaires = inventaires;
        }
}

