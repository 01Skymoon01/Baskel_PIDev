/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;

/**
 *
 * @author Nizar
 */
public class Reservation {
    
    private int id;
    private String etat="réservé";
    private Event id_event;
    private int id_user=10;

    public int getId() {
        return id;
    }

    public String getEtat() {
        return etat;
    }

    

    public int getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getId_event() {
        return id_event;
    }

    public void setId_event(Event id_event) {
        this.id_event = id_event;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", etat=" + etat + ", id_event=" + id_event + ", id_user=" + id_user + '}';
    }

    public Reservation(Event id_event) {
        this.id_event = id_event;
    }

    public Reservation() {
    }

 
    
    
    
}
