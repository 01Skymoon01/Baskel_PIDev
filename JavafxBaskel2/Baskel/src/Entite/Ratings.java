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
    private double totalRate;

    public Ratings(Produits p, int idClient, double rate,double totalRate) {
        this.p = p;
        this.idClient = idClient;
        this.rate = rate;
        this.totalRate = totalRate;
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
        return "Ratings{" + "id=" + id + ", p=" + p + ", idClient=" + idClient + ", rate=" + rate + ", totalRate=" + totalRate + '}';
    }

 

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }
    
    
    
    
    
            
    
}
