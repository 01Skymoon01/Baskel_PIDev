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
public class Ratings {
    private int id;
    private Produits p;
    private int idClient;
    private double rate;

    public Ratings(Produits p, int idClient, double rate) {
        this.p = p;
        this.idClient = idClient;
        this.rate = rate;
    }

    public Ratings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
    
    

    public int getId() {
        return id;
    }

  
    public Produits getP() {
        return p;
    }

    public void setP(Produits p) {
        this.p = p;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", p=" + p + ", idClient=" + idClient + '}';
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    
    
    
    
            
    
}
