/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

/**
 *
 * @author Achraf Zaafrane
 */
public class Vehicule {
    private int id;
    private String matricule ;
    private String marque ;
    private String type;
    private Livreur user;

    public Vehicule() {
    }

    public Vehicule(int id, String matricule, String marque, String type, Livreur user) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.type = type;
        this.user = user;
    }

    public Vehicule(String matricule, String marque, String type, Livreur user) {
        this.matricule = matricule;
        this.marque = marque;
        this.type = type;
        this.user = user;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Livreur getUser() {
        return user;
    }

    public void setUser(Livreur user) {
        this.user = user;
    }

    public Vehicule(String matricule, String marque, String type) {
        this.matricule = matricule;
        this.marque = marque;
        this.type = type;
    }

    
    
    
    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", type=" + type + ", user=" + user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicule other = (Vehicule) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
