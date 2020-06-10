/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entite.Commande;
import Entite.Details_commande;
import Entite.Personne;
import Entite.User;
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
 * @author pinxh
 */
public class UserService {
    
    
    private Connection con;
    private Statement ste;

    public UserService() {
        con = Database.getInstance().getConnection();

    }

  

//************Details *********************************************************/ 
    public ObservableList<User> readAll() throws SQLException {
        ObservableList<User> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user");
        while (rs.next()) {

            arr.add(new User(rs.getInt(1),rs.getInt(14),rs.getString(2),rs.getString(4),rs.getString(8)));
        }
        return arr;
    }
    
     public void ajouter(User t) throws SQLException {
        ste = con.createStatement();
        String var = "a:0:{}";
        /*char[] var2 = var.toCharArray();
        System.out.println(var2);*/
        String requeteInsert = "INSERT INTO user (`username`, `username_canonical`, `email`, `email_canonical`, `password`, `roles`, `enabled`, `cin`) VALUES ('" + t.getUsername() + "', '" + t.getUsername() + "', '" + t.getEmail() + "', '" + t.getEmail() + "', '" + t.getPassword() + "', '"  +var+  "' , 1, '" + t.getCin() + "');";
                                
        ste.executeUpdate(requeteInsert);
    }
    
    public void SupprimerUser(int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `user` WHERE id = '" + id + "'";
        ste.executeUpdate(requeteInsert);
    }
    
   /*public ObservableList<User> RechercherUser(String id) throws SQLException {
        ObservableList<User> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from user WHERE id = '" + id + "'");
        while (rs.next()) {
            /*int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);
              arr.add(new Commande(rs.getString(2), rs.getString(3), rs.getInt(4)));

         Commande(int idClient, Date date, double TOtalPrix, int nbrProduit, int etat, int etat_liv)
             */
           /* User c = new User(rs.getInt(1),rs.getString(2),rs.getString(4)) ;
            arr.add(c);

        }
        return arr;
    }*/
   public ObservableList<User> RechercherUser(String id) throws SQLException {
        ObservableList<User> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        //ResultSet rs = ste.executeQuery("select * from user WHERE ( id LIKE '"+id+"' OR username LIKE '"+id+"' OR email LIKE '"+id+"')");
        PreparedStatement pstmt = con.prepareStatement( "SELECT * FROM user WHERE username like ?");
        pstmt.setString(1, id + "%");
        //ResultSet rs = ste.executeQuery("select * from user WHERE username LIKE '"+id+"'");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            /*int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);
              arr.add(new Commande(rs.getString(2), rs.getString(3), rs.getInt(4)));

         Commande(int idClient, Date date, double TOtalPrix, int nbrProduit, int etat, int etat_liv)
             */
            User c = new User(rs.getInt(1),rs.getString(2),rs.getString(4)) ;
            //System.out.println(c);
            arr.add(c);

        }
        return arr;
    }
   
     public void ModifierUser(int id,String username,String email) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "update user set username='" + username + "',email='" + email + "' where cin ='" + id + "'";
        ste.executeUpdate(requeteInsert);
    }
     
    public void DesactiverUser(int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "update user set enabled=0 where id ='" + id + "'";
        ste.executeUpdate(requeteInsert);
    }
    
      public void activerUser(int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "update user set enabled=1 where id ='" + id + "'";
        ste.executeUpdate(requeteInsert);
    }
      
         public void changePasswrd(String pass,int cin) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "update user set password='"+ pass +"' where cin ='" + cin + "'";
        ste.executeUpdate(requeteInsert);
    }
     
     public ResultSet GetUserProfile(String username){
        ResultSet r = null;
         try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        String requeteInsert = "select * from user where username ='" + username + "'";
        try {
            r = ste.executeQuery(requeteInsert);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
     }
    
     public ResultSet getUserPass(String username){
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        String requeteInsert = "select username,password,roles,enabled from user where username ='" + username + "'";
        
        ResultSet resultSet = null;
        try {
            resultSet = ste.executeQuery(requeteInsert);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
        
     
     }
     
     
     public void AddImage(int id,String imgpath) throws SQLException{
         ste = con.createStatement();
        String requeteInsert = "update user set picturePath='" + imgpath + "' where cin ='" + id + "'";
        ste.executeUpdate(requeteInsert);
     
     
     }
    
     public String ShowImage(int id) throws SQLException{
         
         
         //System.out.println("id fel show image : " + id);
         String picturePath = "";
         ste = con.createStatement();
       
        try {
         String requeteInsert = "select picturePath from user where cin='" + id + "'";
         ResultSet rs = ste.executeQuery(requeteInsert);
    
        while (rs.next()) {
         System.out.println(" resultat rs : " + rs.getString(1));
         picturePath = rs.getString("picturePath");
         System.out.println("picture path fi show image : " + picturePath);
        }
        }
        catch(SQLException x  ){
        x.getMessage();
        }
        return picturePath;
     
     }
     
     
     public int getCin(String username) throws SQLException{
       
         int cin = 0;
         ste = con.createStatement();
         String requeteInsert = "select * from user where username ='" + username + "'";
         ResultSet rs = ste.executeQuery(requeteInsert);
         while (rs.next()) {

         cin = rs.getInt("cin");
        
         }
            return cin;
     }
    
   

  
    
    
    
    
    
    
    
    
}
