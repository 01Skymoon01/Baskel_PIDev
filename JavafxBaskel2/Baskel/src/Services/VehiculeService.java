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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Achraf Zaafrane
 */
public class VehiculeService {
    Connection cn = Database.getInstance().getConnection();
    
    public void ajouterVehicule(Vehicule v,int id){
        
        try {
            Statement st = cn.createStatement();
            String req = "insert into vehicule (matricule, marque, type, livreur_id) VALUES (?,?,?,"+id+")";
            PreparedStatement ps=  cn.prepareStatement(req);
            ps.setString(1, v.getMarque());
            ps.setString(2, v.getMatricule());
            ps.setString(3, v.getType());
            //   ps.setObject(4, new Livreur());
            ps.executeUpdate();

        }  catch (SQLException ex){
            System.out.println("Error sql : " + ex.getMessage());

        }
        
    }
    
    
     
        
        
        public ObservableList<Vehicule> getAllVehicule() {
           ObservableList<Vehicule> vehicules= FXCollections.observableArrayList();

        try {
            Statement st = cn.createStatement();

            String req = "SELECT * FROM vehicule";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Vehicule c = new Vehicule();
                c.setId(rs.getInt(4));
                c.setMatricule(rs.getString(1));
                c.setMarque(rs.getString(2));
                c.setType(rs.getString(3));
                int idLivreur= rs.getInt(5);
                Livreur l= new Livreur();
                l.setId(idLivreur);
                c.setUser(l);
               
                vehicules.add(c);
            }

            return vehicules;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
        
           public void SupprimerVehicule(int id) {
       
      try {
            Statement st = cn.createStatement();
            String req = "delete from vehicule where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            System.out.println("suppression pas ok");
        }
           
      
}
           
             public void modifierVehicule(Vehicule v) {
                  try {
             PreparedStatement ps = cn.prepareStatement("update vehicule set matricule=?,marque=?,type=? where id=?");
             ps.setString(1,v.getMatricule());
              ps.setString(2, v.getMarque());
            ps.setString(3, v.getType());
                ps.setInt(4,v.getId());

             
             
             ps.executeUpdate();
             }
                   catch (SQLException ex) {
             Logger.getLogger(VehiculeService   .class.getName()).log(Level.SEVERE, null, ex);
         }
    
             }
             
             
             
        public String getNumberVehicule() throws SQLException {

     
            Statement st = cn.createStatement();

            String req = "SELECT count(*) FROM vehicule";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

     rs.next();
     String sum = rs.getString(1);
                 return sum;

            }

           public List<String> searchVehicule(String x) {
           List<String> vehicules= new ArrayList<String>();

        try {
            Statement st = cn.createStatement();

            String req = "SELECT * FROM vehicule WHERE marque LIKE ?";
  PreparedStatement ps = cn.prepareStatement(req);
  ps.setString(1, "%" + x + "%");

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            

          while (rs.next()) {
            vehicules.add(rs.getString("matircule"));
        }

            return vehicules;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
       public ObservableList<Vehicule> findVehiculeById(String id) throws SQLException {
              Statement ste = cn.createStatement();
        ObservableList<Vehicule> arr = FXCollections.observableArrayList();
        ste = cn.createStatement();
        ResultSet rs = ste.executeQuery("select * from vehicule WHERE id = '" + id + "'");
        while (rs.next()) {
            /*int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);
              arr.add(new Commande(rs.getString(2), rs.getString(3), rs.getInt(4)));

         Commande(int idClient, Date date, double TOtalPrix, int nbrProduit, int etat, int etat_liv)
             */
             int idLivreur= rs.getInt(5);
                Livreur l= new Livreur();
            Vehicule c = new Vehicule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),l);
            arr.add(c);

        }
        return arr;
    } 
}
