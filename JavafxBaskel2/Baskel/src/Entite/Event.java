/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

/**
 *
 * @author Nizar
 */
public class Event {
    
    private int id;
    private Date date;
    private String nom;
    private String description;
    private int nbParticipants;
    private String emailresponsable;
    private String whyattend;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event() {
    }

    public Event( Date date, String nom, String description, int nbParticipants, String emailresponsable, String whyattend) {
        
        this.date = date;
        this.nom = nom;
        this.description = description;
        this.nbParticipants = nbParticipants;
        this.emailresponsable = emailresponsable;
        this.whyattend = whyattend;
    }

  

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public String getEmailresponsable() {
        return emailresponsable;
    }

    public String getWhyattend() {
        return whyattend;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public void setEmailresponsable(String emailresponsable) {
        this.emailresponsable = emailresponsable;
    }

    public void setWhyattend(String whyattend) {
        this.whyattend = whyattend;
    }

    @Override
    public String toString() {
        return nom; 
    }
   
    public String toString2() {
        return ""+id;
    }
    
      public String toString3() {
        return emailresponsable;
    }
    
    
    
}
