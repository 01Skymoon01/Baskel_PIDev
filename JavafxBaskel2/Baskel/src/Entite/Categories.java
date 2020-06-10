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
public class Categories {
    private int ref_c;
    private String libelle;

    public Categories(int ref_c, String libelle) {
        this.ref_c = ref_c;
        this.libelle = libelle;
    }

    public Categories() {
    }
    
    
    

    public int getRef_c() {
        return ref_c;
    }

    public void setRef_c(int ref_c) {
        this.ref_c = ref_c;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Categories{" + "ref_c=" + ref_c + ", libelle=" + libelle + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.ref_c;
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
        final Categories other = (Categories) obj;
        if (this.ref_c != other.ref_c) {
            return false;
        }
        return true;
    }
    
    
}
