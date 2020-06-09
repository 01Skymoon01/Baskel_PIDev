/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Event;
import Entite.Reservation;
import Entite.SessionUser;
import adhma.Utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nizar
 */
public class ReservationService {
    
        Connection En = Database.getInstance().getConnection();
        
     public void Reserver(Reservation E,int IdE) {
        try {
            Statement st = En.createStatement(); //l'element qui va éxécuter la requete sql

        
            String req = "insert into reservation ( id_event,id_user,etat) VALUES ("+IdE+",?,?)";
            PreparedStatement ste = En.prepareStatement(req);
  
            ste.setInt(1, SessionUser.getInstance().getId());
            ste.setString(2, E.getEtat());
           
          
           
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
     
      public ObservableList<Reservation> getAllRes() {
        ObservableList<Reservation> l = FXCollections.observableArrayList();

        try {
            Statement st = En.createStatement();

            String req = "select  id_event,id,etat from reservation";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
               Reservation E = new Reservation();
               
                int idEvent= rs.getInt(1);
                Event e= new Event();
                
                EventService Es = new EventService();
                Event E1 = Es.getEventByID(idEvent);
                
               E.setId_event(E1);
               E.setId(rs.getInt(2));
               E.setEtat(rs.getString(3));
               
         
               // hedhom zeydin raw ahh
              
               
               l.add(E );
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
      
      
      
      public void Confirmer(Reservation r) {
          
          
        try {
            PreparedStatement ps = En.prepareStatement("update reservation set etat=? where id=?");
                 
                ps.setString(1, r.getEtat());
                 ps.setInt(2, r.getId());
                 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
      
         public void Annuler(Reservation r) {
          
          
        try {
            PreparedStatement ps = En.prepareStatement("update reservation set etat=? where id=?");
                 
                ps.setString(1, r.getEtat());
                 ps.setInt(2, r.getId());
                 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
    
}
