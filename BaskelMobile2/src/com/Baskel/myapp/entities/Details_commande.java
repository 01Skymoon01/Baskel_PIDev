/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

import java.util.Objects;

/**
 *
 * @author hp
 */
public class Details_commande {


    String nomProduit;
    int idProduit;
    int qteProduit;
    double PrixProduit;
    Commande commande;

    public Details_commande() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQteProduit() {
        return qteProduit;
    }

    public void setQteProduit(int qteProduit) {
        this.qteProduit = qteProduit;
    }

    public double getPrixProduit() {
        return PrixProduit;
    }

    public void setPrixProduit(double PrixProduit) {
        this.PrixProduit = PrixProduit;
    }

    public Commande getCommande() {
        return commande;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nomProduit);
        hash = 59 * hash + this.idProduit;
        hash = 59 * hash + this.qteProduit;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.PrixProduit) ^ (Double.doubleToLongBits(this.PrixProduit) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.commande);
        return hash;
    }

    @Override
    public String toString() {
        return "Details_commande{" + "nomProduit=" + nomProduit + ", idProduit=" + idProduit + ", qteProduit=" + qteProduit + ", PrixProduit=" + PrixProduit + ", commande=" + commande + '}';
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
        final Details_commande other = (Details_commande) obj;
        if (this.idProduit != other.idProduit) {
            return false;
        }
        if (this.qteProduit != other.qteProduit) {
            return false;
        }
        if (Double.doubleToLongBits(this.PrixProduit) != Double.doubleToLongBits(other.PrixProduit)) {
            return false;
        }
        if (!Objects.equals(this.nomProduit, other.nomProduit)) {
            return false;
        }
        if (!Objects.equals(this.commande, other.commande)) {
            return false;
        }
        return true;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }



}
