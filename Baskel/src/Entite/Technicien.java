/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author 21692
 */
public class Technicien {
    
private int idT;
    private int cin;
    private String nom;
    private String prenom;
    private String email;
    private int numtel;

    public int getIdT() {
        return idT;
    }

    @Override
    public String toString() {
        if(idT==0){ return " Non affecté "; } else {
        return " "+ idT + " " + nom + " " +prenom ; }
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public Technicien() {
    }

    public Technicien(int idT, int cin, String nom, String prenom, String email, int numtel) {
        this.idT = idT;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
    }

    public Technicien(int cin, String nom, String prenom, String email, int numtel) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
    }
    

}
