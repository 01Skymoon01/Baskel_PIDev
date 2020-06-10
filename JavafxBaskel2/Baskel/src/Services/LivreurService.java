/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Livreur;
import Entite.Vehicule;
import adhma.Utils.Database;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Achraf Zaafrane
 */
public class LivreurService {
    Connection cn = Database.getInstance().getConnection();
    
    
     public void ajouterLivreur(Livreur v){
        
        try {
            Statement st = cn.createStatement();
            String req = "insert into livreur (nom, prenom, dateNaiss,solde, etat,id_username) VALUES (?,?,?,?,?,2)";
            PreparedStatement ps=  cn.prepareStatement(req);
            ps.setString(1, v.getNom());
            ps.setString(2, v.getPrenom());
            ps.setDate(3, (Date) v.getDateNaiss());
                   ps.setInt(4, v.getSolde());
                          ps.setString(5, v.getEtat());
            
            ps.executeUpdate();

        }  catch (SQLException ex){
            System.out.println("Error sql : " + ex.getMessage());

        }
        
    }
     
         public void SupprimerLivreur(int id) {
       
      try {
            Statement st = cn.createStatement();
            String req = "delete from livreur where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            System.out.println("suppression pas ok");
        }
           
      
}
     
        public void modifierLivreur(Livreur v) {
                  try {
             PreparedStatement ps = cn.prepareStatement("update livreur set nom=?,prenom=?,dateNaiss=? where id=?");
              ps.setString(1, v.getNom());
            ps.setString(2, v.getPrenom());
            ps.setDate(3, (Date) v.getDateNaiss());
                   ps.setInt(4,v.getId());

             
             
             ps.executeUpdate();
             }
                   catch (SQLException ex) {
             Logger.getLogger(VehiculeService   .class.getName()).log(Level.SEVERE, null, ex);
         }
    
             }
    
     public ObservableList<Livreur> getAllLivreur() {
           ObservableList<Livreur> livreurs= FXCollections.observableArrayList();

        try {
            Statement st = cn.createStatement();

            String req = "SELECT * FROM livreur";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Livreur c = new Livreur();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                                c.setPrenom(rs.getString(3));
                c.setDateNaiss(rs.getDate(4));
                c.setSolde(rs.getInt(5));
                        c.setEtat(rs.getString(6));
                                        c.setId_username(rs.getInt(7));

               
                livreurs.add(c);
            }

            return livreurs;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
     
     
     
     
     
     
     
     
     /*****************************************************************************************
      * 
      */
     
      public ObservableList<Integer> getAllLivreurId() {
           ObservableList<Integer> livreurs= FXCollections.observableArrayList();

        try {
            Statement st = cn.createStatement();

            String req = "SELECT id FROM livreur";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Livreur c = new Livreur();
                c.setId(rs.getInt(1));
            

               
                livreurs.add(c.getId());
            }

            return livreurs;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
      
         public String getNumberLivreur() throws SQLException {

     
            Statement st = cn.createStatement();

            String req = "SELECT count(*) FROM livreur";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

     rs.next();
     String sum = rs.getString(1);
                 return sum;

            }
}
