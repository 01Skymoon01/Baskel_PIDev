/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

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
    String Revenue="";

        public Commande() {
    }
    public String getRevenue() {
        return Revenue;
    }

    public void setRevenue(String Revenue) {
        this.Revenue = Revenue;
    }
        public Commande(int id,int idClient, java.sql.Date date, double TOtalPrix, int nbrProduit, int etat, int etat_liv) {
       this.id = id;
            this.idClient = idClient;
        this.date = date;
        this.TOtalPrix = TOtalPrix;
        this.nbrProduit = nbrProduit;
        this.etat = etat;
        this.etat_liv = etat_liv;
    }
      public Commande(int etat, String Revenue) {
     
        this.etat = etat;
        this.Revenue = Revenue;
 
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idClient=" + idClient + ", date=" + date + ", TOtalPrix=" + TOtalPrix + ", nbrProduit=" + nbrProduit + ", etat=" + etat + ", etat_liv=" + etat_liv + ", Revenue=" + Revenue + '}';
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

}
