/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

/**
 *
 * @author Ery
 */
public class Rating {
    
    private int id;
    private int rate;
    private int idProd;
    private int idClient;
    private int totalRate;

    public Rating(int idProd, int idClient, int rate) {
        
        this.idProd = idProd;
        this.idClient = idClient;
        this.rate = rate;
    }
    
    
    public Rating() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }
    
    
    

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", rate=" + rate + ", idProd=" + idProd + ", idClient=" + idClient + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
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
        final Rating other = (Rating) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
}
