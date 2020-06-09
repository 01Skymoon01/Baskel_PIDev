/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Objects;

/**
 *
 * @author hp
 */
public class Panier {
     int id;
     String nom_prod;
     double prix_prod;
     int qte_prod;
     int id_client;
     int refP;
     String private_prod = "";
    
    
        public Panier(int id,String nom_prod, double prix_prod, int qte_prod, int id_client, int refP, String private_prod) {
       this.id = id;
         this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.id_client = id_client;
        this.refP = refP;
        this.private_prod = private_prod;
    }
        
            
        public Panier(String nom_prod, double prix_prod, int qte_prod, int id_client, int refP) {
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.id_client = id_client;
        this.refP = refP;
    }

    public Panier(int id, String nom_prod, double prix_prod, int qte_prod, int id_client, int refP) {
        this.id = id;
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
        this.id_client = id_client;
        this.refP = refP;
    }
    public Panier(String nom_prod, double prix_prod, int qte_prod) {
        this.nom_prod = nom_prod;
        this.prix_prod = prix_prod;
        this.qte_prod = qte_prod;
      
    }
    
            public Panier( ) {
       
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nom_prod);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.prix_prod) ^ (Double.doubleToLongBits(this.prix_prod) >>> 32));
        hash = 67 * hash + this.qte_prod;
        hash = 67 * hash + this.id_client;
        hash = 67 * hash + this.refP;
        hash = 67 * hash + Objects.hashCode(this.private_prod);
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
        final Panier other = (Panier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix_prod) != Double.doubleToLongBits(other.prix_prod)) {
            return false;
        }
        if (this.qte_prod != other.qte_prod) {
            return false;
        }
        if (this.id_client != other.id_client) {
            return false;
        }
        if (this.refP != other.refP) {
            return false;
        }
        if (!Objects.equals(this.nom_prod, other.nom_prod)) {
            return false;
        }
        if (!Objects.equals(this.private_prod, other.private_prod)) {
            return false;
        }
        return true;
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

    public String getPrivate_prod() {
        return private_prod;
    }

    public void setPrivate_prod(String private_prod) {
        this.private_prod = private_prod;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", nom_prod=" + nom_prod + ", prix_prod=" + prix_prod + ", qte_prod=" + qte_prod + ", id_client=" + id_client + ", refP=" + refP + ", private_prod=" + private_prod + '}';
    }
        
}
