/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

/**
 *
 * @author Achraf Zaafrane
 */
public class Livreur {
    private int id;
    private int id_username;
    private String nom;
    private String prenom;
    private Date dateNaiss;
    private int solde;
    private String etat;

    @Override
    public String toString() {
        return " "+ id ;
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
        final Livreur other = (Livreur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public Livreur(int id) {
        this.id = id;
    }

    public Livreur() {
        etat="en attente";
        solde=0;
    }

    public Livreur(int id, int id_username, String nom, String prenom, Date dateNaiss, int solde, String etat) {
        this.id = id;
        this.id_username = id_username;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.solde = solde;
        this.etat = etat;
    }

    public Livreur( String nom, String prenom, Date dateNaiss, int solde, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.solde = solde;
        this.etat = etat;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_username() {
        return id_username;
    }

    public void setId_username(int id_username) {
        this.id_username = id_username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
}
