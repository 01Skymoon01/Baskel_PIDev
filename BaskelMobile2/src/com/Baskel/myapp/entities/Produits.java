/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

/**
 *
 * @author Ery
 */
public class Produits {
    private int ref_p;
    private String nom_p;
    private String genre_p;
    private int quantite;
    private double prix_p;
    private String image;

    public Produits(int ref_p, String nom_p, String genre_p, int quantite, double prix_p, String image) {
        this.ref_p = ref_p;
        this.nom_p = nom_p;
        this.genre_p = genre_p;
        this.quantite = quantite;
        this.prix_p = prix_p;
        this.image = image;
    }
    
     public Produits(String nom_p, double prix_p, int quantite, String image,int ref_p ) {
        this.nom_p = nom_p;      
        this.prix_p = prix_p;
        this.quantite = quantite;
        this.image = image;
        this.ref_p = ref_p;
    }

    public Produits() {
    }

    public Produits(int ref_p, String image) {
        this.ref_p = ref_p;
        this.image = image;
    }

    public int getRef_p() {
        return ref_p;
    }

    public void setRef_p(int ref_p) {
        this.ref_p = ref_p;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public String getGenre_p() {
        return genre_p;
    }

    public void setGenre_p(String genre_p) {
        this.genre_p = genre_p;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix_p() {
        return prix_p;
    }

    public void setPrix_p(double prix_p) {
        this.prix_p = prix_p;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produits{" + "ref_p=" + ref_p + ", nom_p=" + nom_p + ", genre_p=" + genre_p + ", quantite=" + quantite + ", prix_p=" + prix_p + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.ref_p;
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
        final Produits other = (Produits) obj;
        return this.ref_p == other.ref_p;
    }
    
    
    
}
