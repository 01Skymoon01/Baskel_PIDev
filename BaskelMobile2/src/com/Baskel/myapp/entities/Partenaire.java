/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

/**
 *
 * @author Nizar
 */
public class Partenaire {
    
    private int id;
    private String nom;
    private String description;
    private String type;
    private String representant;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getRepresentant() {
        return representant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRepresentant(String representant) {
        this.representant = representant;
    }

    @Override
    public String toString() {
        return nom;
    }
    
    
    public String toString2() {
        return ""+id;
    }

    public Partenaire(String nom, String description, String type, String representant) {
        
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.representant = representant;
    }

    public Partenaire() {
    }
    
    
}
