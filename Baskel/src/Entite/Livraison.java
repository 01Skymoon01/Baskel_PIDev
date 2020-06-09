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
public class Livraison extends Commande {
    private int id;
    private Livreur idLivreur;
    private Date dateLivraison;
    private String codeConf;
    private Commande idCommande;
//nour

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livreur getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Livreur idLivreur) {
        this.idLivreur = idLivreur;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getCodeConf() {
        return codeConf;
    }

    public void setCodeConf(String codeConf) {
        this.codeConf = codeConf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Livraison other = (Livraison) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "Livraison{" + "id=" + id + ", idLivreur=" + idLivreur + ", dateLivraison=" + dateLivraison + ", codeConf=" + codeConf + '}';
    }

    public Livraison() {
        super();
        codeConf= randomAlphaNumeric(5);
    }

    public Livraison(int id, Livreur idLivreur, Date dateLivraison, String codeConf ,   int idc,  int idClient,java.sql.Date  date, double TOtalPrix, int nbrProduit, int etat,int etat_liv)
    {
        super(idc,idClient,date,TOtalPrix,nbrProduit,etat,etat_liv);
        this.id = id;
        this.idLivreur = idLivreur;
        this.dateLivraison = dateLivraison;
        this.codeConf = codeConf;
    }
    
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
public static String randomAlphaNumeric(int count) {
    count =5;
StringBuilder builder = new StringBuilder();
while (count-- != 0) {
int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
builder.append(ALPHA_NUMERIC_STRING.charAt(character));
}
return builder.toString();
}
    
}
