/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

import java.util.ArrayList;

public class Panier {

    public ArrayList<Produits> panier = new ArrayList<>();
    ;
      public static Panier instance = null;

    public Panier(Produits p) {
        panier.add(p);
    }

    public Panier() {

    }

    @Override
    public String toString() {
        return "Panier{" + "panier=" + panier + '}';
    }

    public static Panier getInstance() {
        if (instance == null) {
            instance = new Panier();
        }
        return instance;
    }

    public boolean AddProduitInPanier(Produits p) {
        
     
        if (panier.contains(p)) {
            
                    return false;

        } else {
                  panier.add(p);
                   return true;

        }
    
    }

    
     public double totalPrix() {
        
        double x=0;
        ArrayList<Produits> p = getInstance().panier;
        for(int i=0; i< p.size();i++ ){
        
            x+=p.get(i).getPrix_p();
        
        }
        return x;
    
    }
     
     

}
