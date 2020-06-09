/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Technicien;
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
 * @author 21692
 */
public class TechnicienService_1 {
    
    
            Connection Cn = Database.getInstance().getConnection();

    public void ajouterTechnicien (Technicien C) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

            //  String req = "insert into assurance values('" + C.getNom()+ "','" + C.getDescription()+ "','" + C.getId_client()+ "','" + C.getType()+ "','" + C.getDate_debut() + "','" + C.getDate_Echeance()+"')";
         
            String req = "INSERT INTO technicien ( `cin`,  `nom`, `prenom`,  `email`,  `numtel`) VALUES (?, ?, ?,?, ?);";
            PreparedStatement ste = Cn.prepareStatement(req);
            
            ste.setInt(1, C.getCin());
           // ste.setDate(2, (Date) C.getDateR());
   
            ste.setString(2, C.getNom());
            ste.setString(3, C.getPrenom());
            ste.setString(4, C.getEmail());
            ste.setInt(5, C.getNumtel());
           
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    
    
       public void SupprimerTechnicien (int idT) {
        try {
            Statement st = Cn.createStatement();
            String req = "delete from technicien where idT=" + idT;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            System.out.println("suppression pas ok");
        }

    }

       
       
         public ObservableList<Technicien> getAllTechniciensBACK() {
        ObservableList<Technicien> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT * FROM `technicien` WHERE 1";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Technicien c = new Technicien();
                
                c.setIdT(rs.getInt(1));
                c.setCin(rs.getInt(2));
                c.setNom(rs.getString(3));
                c.setPrenom(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setNumtel(rs.getInt(6));
               
                l.add(c);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }  
       
         
         
         
              public Technicien getTechnicienByID(int idT) {
        Technicien c = new Technicien();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT * FROM technicien where idT=" + idT;

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
               
                
                
                c.setIdT(rs.getInt(1));
                c.setCin(rs.getInt(2));
                c.setNom(rs.getString(3));
                c.setPrenom(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setNumtel(rs.getInt(6));
               
              
            }

            return c;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }  
          
         
         
         
         
         
        
               public void modifierTechnicien(Technicien c) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update technicien set cin=?,nom=?, prenom=?, email=?, numtel=? where idT=?");
            
            
                 ps.setInt(1, c.getCin());
                 ps.setString(2, c.getNom());
                 ps.setString(3, c.getPrenom());
                 ps.setString(4, c.getEmail());
                 ps.setInt(5, c.getNumtel());
                 ps.setInt(6, c.getIdT());
                 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
       
    
}
