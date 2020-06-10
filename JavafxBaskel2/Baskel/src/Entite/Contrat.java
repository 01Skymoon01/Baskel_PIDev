/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Nizar
 */
public class Contrat {
    private int id;
    private int pack;
    private String description;
    private Event id_event;
    private Partenaire  id_partenaire;

    public Contrat() {
      
    }

    public int getId() {
        return id;
    }

    

    public String getDescription() {
        return description;
    }

    public Contrat(int pack, String description, Event id_event, Partenaire id_partenaire) {
        
        this.pack = pack;
        this.description = description;
        this.id_event = id_event;
        this.id_partenaire = id_partenaire;
    }

    

    

   

  

    public void setId(int id) {
        this.id = id;
    }

    public int getPack() {
        return pack;
    }

    public void setPack(int pack) {
        this.pack = pack;
    }

   

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getId_event() {
        return id_event;
    }

    public void setId_event(Event id_event) {
        this.id_event = id_event;
    }

    public Partenaire getId_partenaire() {
        return id_partenaire;
    }

    public void setId_partenaire(Partenaire id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

    

    

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", pack=" + pack + ", description=" + description + ", id_event=" + id_event + ", id_partenaire=" + id_partenaire + '}';
    }
    
    
    
}
