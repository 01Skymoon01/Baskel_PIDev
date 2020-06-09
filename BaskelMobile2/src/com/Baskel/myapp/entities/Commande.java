/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author hp
 */
public class Commande {

    int id;
    int idClient;
    Date date;
    double TOtalPrix;
    int nbrProduit;
    int etat;
    int etat_liv;

            public Commande() {
    }
              public Commande(int id,int idClient, Date date, double TOtalPrix, int nbrProduit, int etat, int etat_liv) {
       this.id = id;
            this.idClient = idClient;
        this.date = date;
        this.TOtalPrix = TOtalPrix;
        this.nbrProduit = nbrProduit;
        this.etat = etat;
        this.etat_liv = etat_liv;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idClient=" + idClient + ", date=" + date + ", TOtalPrix=" + TOtalPrix + ", nbrProduit=" + nbrProduit + ", etat=" + etat + ", etat_liv=" + etat_liv + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTOtalPrix() {
        return TOtalPrix;
    }

    public void setTOtalPrix(double TOtalPrix) {
        this.TOtalPrix = TOtalPrix;
    }

    public int getNbrProduit() {
        return nbrProduit;
    }

    public void setNbrProduit(int nbrProduit) {
        this.nbrProduit = nbrProduit;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEtat_liv() {
        return etat_liv;
    }

    public void setEtat_liv(int etat_liv) {
        this.etat_liv = etat_liv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.idClient;
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.TOtalPrix) ^ (Double.doubleToLongBits(this.TOtalPrix) >>> 32));
        hash = 89 * hash + this.nbrProduit;
        hash = 89 * hash + this.etat;
        hash = 89 * hash + this.etat_liv;
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
        final Commande other = (Commande) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idClient != other.idClient) {
            return false;
        }
        if (Double.doubleToLongBits(this.TOtalPrix) != Double.doubleToLongBits(other.TOtalPrix)) {
            return false;
        }
        if (this.nbrProduit != other.nbrProduit) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (this.etat_liv != other.etat_liv) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    

}
