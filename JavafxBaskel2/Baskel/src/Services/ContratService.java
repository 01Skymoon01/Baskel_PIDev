/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import adhma.Utils.Database;
import Entite.Contrat;
import Entite.Event;
import Entite.Partenaire;
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
public class ContratService {
    
     Connection En = Database.getInstance().getConnection();
     
       public void Signercontrat(Contrat C, int fooE,int fooP) {
        try {
            Statement st = En.createStatement(); //l'element qui va éxécuter la requete sql

        
            String req = "insert into contrat (id_event,id_partenaire,Pack,description) VALUES ("+fooE+","+fooP+",?,?)";
            PreparedStatement ste = En.prepareStatement(req);
            

            ste.setInt(1, C.getPack());
            ste.setString(2,C.getDescription());

            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
       
       public ObservableList<Contrat> getAllContrat() {
        ObservableList<Contrat> l = FXCollections.observableArrayList();

        try {
            Statement st = En.createStatement();

            String req = "select * from contrat";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                
                
                
                
               Contrat C = new Contrat();
               C.setId(rs.getInt(1));
               int idEvent= rs.getInt(2);
                Event E= new Event();
                
                EventService Es = new EventService();
                Event E1 = Es.getEventByID(idEvent);
                
                E.setId(idEvent); 
               C.setId_event(E1);
               int idpart= rs.getInt(3);
                Partenaire P= new Partenaire();
                PartenaireService Ps = new PartenaireService();
                Partenaire P1 = Ps.getPartenaireByID(idpart);
                P.setId(idpart);
                
               C.setId_partenaire(P1);
               C.setPack(rs.getInt(4));
               C.setDescription(rs.getString(5));
               
               
             
              
               
               l.add(C);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
}
