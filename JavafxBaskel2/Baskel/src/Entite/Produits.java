/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import static java.sql.JDBCType.BLOB;
import static java.sql.Types.BLOB;

/**
 *
 * @author Ery's Desktop
 */
public class Produits {
    
    private int ref_p;
    private String nom_p;
    private String genre_p;
    private String couleur_p;
    private int quantite_p;
    private double prix_p;
    private String marque_p;
    private int ref_c;
    private Categories c;
    private String description;
    private String image;
    private double solde;
    //private BLOB image2;

    public Produits(int ref_p, String nom_p, String genre_p, String couleur_p, int quantite_p, double prix_p, String marque_p, Categories c, String description, String image, double solde) {
        this.ref_p = ref_p;
        this.nom_p = nom_p;
        this.genre_p = genre_p;
        this.couleur_p = couleur_p;
        this.quantite_p = quantite_p;
        this.prix_p = prix_p;
        this.marque_p = marque_p;
        this.c = c;
        this.description = description;
        this.image = image;
        this.solde = solde;
    }

    public Produits(int ref_p, String nom_p) {
        this.ref_p = ref_p;
        this.nom_p = nom_p;
    }

    public Produits(int ref2,Categories c, String nom, String marque, double prix2, int qte2, String gre,String desc,String couleur,String image) {
        
        this.ref_p = ref2;
        //this.ref_c=refCat;
        this.c=c;
        this.nom_p = nom;
        this.marque_p = marque;
        this.prix_p = prix2;
        this.quantite_p = qte2;
        this.genre_p = gre;
        this.description=desc;
        this.couleur_p = couleur;
        this.image=image;
    }
    
     public Produits(int ref2,Categories c, String nom, String marque, double prix2, int qte2, String gre,String desc,String image) {
        
        this.ref_p = ref2;
        //this.ref_c=refCat;
        this.c=c;
        this.nom_p = nom;
        this.marque_p = marque;
        this.prix_p = prix2;
        this.quantite_p = qte2;
        this.genre_p = gre;
        this.description=desc;
     //  this.couleur_p = couleur;
        this.image=image;
    }

    public Produits() {
    }

    public Produits(int ref_p) {
        this.ref_p=ref_p;
    }

    public Produits(int ref_p,String nom_p, String genre_p, String marque_p, int quantite_p, double prix_p) {
        
        this.ref_p = ref_p;
        this.nom_p = nom_p;
        this.genre_p = genre_p;
   
        this.quantite_p = quantite_p;
        this.prix_p = prix_p;
        this.marque_p = marque_p;

    }

    public Produits(String nom, double prix, int qte, String imageFile,int id) {
        this.ref_p = id;
        this.nom_p = nom;
   
        this.quantite_p = qte;
        this.prix_p = prix;
        this.image = imageFile;
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

    public String getCouleur_p() {
        return couleur_p;
    }

    public void setCouleur_p(String couleur_p) {
        this.couleur_p = couleur_p;
    }

    public int getQuantite_p() {
        return quantite_p;
    }

    public void setQuantite_p(int quantite_p) {
        this.quantite_p = quantite_p;
    }

    public double getPrix_p() {
        return prix_p;
    }

    public void setPrix_p(double prix_p) {
        this.prix_p = prix_p;
    }

    public String getMarque_p() {
        return marque_p;
    }

    public void setMarque_p(String marque_p) {
        this.marque_p = marque_p;
    }

    public int getRef_c() {
        return c.getRef_c();
    }

    /*public void setRef_c(int ref_c) {
        this.c.getRef_c() = ref_c;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Categories getC() {
        return c;
    }

    public void setC(Categories c) {
        this.c = c;
    }
    
    
    
    
    
    

    @Override
    public String toString() {
        return "Produits{" + "ref_p=" + ref_p + ", nom_p=" + nom_p + ", genre_p=" + genre_p + ", couleur_p=" + couleur_p + ", quantite_p=" + quantite_p + ", prix_p=" + prix_p + ", marque_p=" + marque_p + ", ref_c=" + c.getRef_c() + ", description=" + description + ", image=" + image + ", solde=" + solde + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.ref_p;
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
        if (this.ref_p != other.ref_p) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
