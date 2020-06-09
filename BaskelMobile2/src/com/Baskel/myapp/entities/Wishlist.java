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
public class Wishlist {
    private int id;
    private int id_client;
    private Produits p;
    private String nomProd;
    private String imgProd;
    private double prixProd;
    private int refProd;

    public Wishlist() {
    }

    public Wishlist(int id_client, Produits p) {
       
        this.id_client = id_client;
        this.p = p;
    }

    
    public Wishlist( Produits p) {
   
        this.p = p;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Produits getP() {
        return p;
    }

    public void setP(Produits p) {
        this.p = p;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getImgProd() {
        return imgProd;
    }

    public void setImgProd(String imgProd) {
        this.imgProd = imgProd;
    }

    public double getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(double prixProd) {
        this.prixProd = prixProd;
    }

    public int getRefProd() {
        return refProd;
    }

    public void setRefProd(int refProd) {
        this.refProd = refProd;
    }

    
    
    
    @Override
    public String toString() {
        return "Wishlist{" + "id=" + id + ", id_client=" + id_client + ", nom_prod=" + p.getNom_p() + ", prix_prod"+p.getPrix_p()+", quantite_prod"+p.getQuantite()+", ref_prod"+p.getRef_p()+ '}';
    }
    
    
    
}
