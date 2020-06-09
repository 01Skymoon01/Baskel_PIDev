/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Ery's Desktop
 */
public class Wishlist {
    
    private int id;
    private String nom_prod;
     private int refP;
   /* private double prix_prod;
    private int qte_prod;
   
    private String private_prod;*/
    private Produits p;
    private int id_client;
    
    

  /*
    public Wishlist(int id, String nom_prod, double prix_prod, int qte_prod, int id_client, int refP, String private_prod) {
        this.id = id;
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.id_client = id_client;
        this.refP = refP;
        this.private_prod = private_prod;
    }
    */
    
    

    public Wishlist(int id_client,Produits p) {
   
        this.p = p;
        this.id_client = id_client;
    }

    public Wishlist(String image, String nom) {
        this.p.setImage(image);
        this.p.setNom_p(nom);
    }

    public Wishlist(String nom,int ref) {
        this.nom_prod =nom;
        this.refP=ref;
    }

    public Produits getP() {
        return p;
    }

    public void setP(Produits p) {
        this.p = p;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }
/*
    public double getPrix_prod() {
        return prix_prod;
    }

    public void setPrix_prod(double prix_prod) {
        this.prix_prod = prix_prod;
    }

    public int getQte_prod() {
        return qte_prod;
    }

    public void setQte_prod(int qte_prod) {
        this.qte_prod = qte_prod;
    }
*/
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getRefP() {
        return refP;
    }

    public void setRefP(int refP) {
        this.refP = refP;
    }
/*
    public String getPrivate_prod() {
        return private_prod;
    }

    public void setPrivate_prod(String private_prod) {
        this.private_prod = private_prod;
    }
*/
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
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
        final Wishlist other = (Wishlist) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "id=" + id + ", nom_prod=" + p.getNom_p() + ", prix_prod=" + p.getPrix_p() + ", qte_prod=" + p.getQuantite_p() + ", id_client=" + id_client + ", refP=" + p.getRef_p() + ", private_prod=" + p.getImage() + '}';
    }
    
    
    
    
}
