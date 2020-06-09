/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entite.Partenaire;
import adhma.Utils.Database;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nizar
 */
public class PartenaireService {
    
          Connection En = Database.getInstance().getConnection();

          
    public void ajouterPartenaire(Partenaire E) {
        try {
            Statement st = En.createStatement(); //l'element qui va éxécuter la requete sql

        
            String req = "insert into partenaire  (nom, description, type, representant) VALUES (?,?,?,?)";
            PreparedStatement ste = En.prepareStatement(req);
            
            ste.setString(1, E.getNom());
            ste.setString(2, E.getDescription());
            ste.setString(3, E. getType());
            ste.setString(4, E.getRepresentant());
            
          
           
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    
     public Partenaire  getPartenaireByID(int idE) {
        Partenaire E = new Partenaire();

        try {
            Statement st = En.createStatement();

            String req = "SELECT * FROM partenaire where id=" + idE;

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
               
                
         
               E.setId(rs.getInt(1));
               E .setNom(rs.getString(2));
               E .setDescription(rs.getString(3));
               E .setType(rs.getString(4));
               E.setRepresentant(rs.getString(5));
               
              
            }

            return E;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
     
     
    public ObservableList<Partenaire> getAllPartenaire() {
        ObservableList<Partenaire> l = FXCollections.observableArrayList();

        try {
            Statement st = En.createStatement();

            String req = "select  * from partenaire";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
               Partenaire E = new Partenaire();
               E.setId(rs.getInt(1));
               E .setNom(rs.getString(2));
               E .setDescription(rs.getString(3));
               E .setType(rs.getString(4));
               E.setRepresentant(rs.getString(5));
              
               
               l.add(E );
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
    
   
    public void modifierE(Partenaire E) {
        try {
            
            PreparedStatement ps = En.prepareStatement("update partenaire set nom=?,description=?,type=?,representant=? WHERE id=?");
       
           
            ps.setString(1, E.getNom());
            ps.setString(2, E.getDescription());
            ps.setString(3, E. getType());
            ps.setString(4, E.getRepresentant());
            
            ps.setInt(5,E.getId());
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error BOII : "+ex.getMessage());
        }

    }
    
    public void supprimerPartenaire(int id){
          try {
            Statement st = En.createStatement();
            String req = "delete from partenaire where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression done");
        } catch (SQLException ex) {
           // Logger.getLogger(AssureEntreprise.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    
}
