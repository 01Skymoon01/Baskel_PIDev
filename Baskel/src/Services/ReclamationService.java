/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Reclamation;
import Entite.SessionUser;
import adhma.Utils.Database;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 21692
 */
public class ReclamationService {
    
        Connection Cn = Database.getInstance().getConnection();
       

    public void ajouterReclamation (Reclamation C) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

            //  String req = "insert into assurance values('" + C.getNom()+ "','" + C.getDescription()+ "','" + C.getId_client()+ "','" + C.getType()+ "','" + C.getDate_debut() + "','" + C.getDate_Echeance()+"')";
           String cutie = "non traitee";
            String req = "INSERT INTO reclamation ( `userid`,  `dateR`, `objetR`,  `etatR`,  `detailsR`) VALUES (?, ?, ?,?, ?) ";
            PreparedStatement ste = Cn.prepareStatement(req);
            
            ste.setInt(1, SessionUser.getInstance().getId());
           // ste.setDate(2, (Date) C.getDateR());
            ste.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ste.setString(3, C.getObjetR());
            ste.setString(4, C.getEtatR());
            ste.setString(5, C.getDetailsR());
           
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    
    
       public void SupprimerReclamation (int idR) {
        try {
            Statement st = Cn.createStatement();
            String req = "delete from reclamation where idR=" + idR;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            System.out.println("suppression pas ok");
        }

    }

       
       
         public ObservableList<Reclamation> getAllReclamationsBACK() {
        ObservableList<Reclamation> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT * FROM `reclamation` ";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Reclamation c = new Reclamation();
                c.setUserid(rs.getInt(1));
                c.setIdR(rs.getInt(2));
               
                c.setDateR(rs.getDate(3));
                c.setObjetR(rs.getString(4));
                c.setEtatR(rs.getString(5));
                c.setDetailsR(rs.getString(6));
               
                l.add(c);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }  
       
         
           public ObservableList<Reclamation> getAllReclamationsFRONT() {
        ObservableList<Reclamation> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT idR , dateR, objetR, etatR, detailsR FROM reclamation where userid='"+SessionUser.getInstance().getId()+"'";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Reclamation c = new Reclamation();
                c.setIdR(rs.getInt(1));
                c.setDateR(rs.getDate(2));
                c.setObjetR(rs.getString(3));
                c.setEtatR(rs.getString(4));
                c.setDetailsR(rs.getString(5));
               
                l.add(c);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
           
           
           
         
           
        
               public void modifierReclamation(Reclamation c) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update reclamation set objetR=?,detailsR=? where idR=?");
            
            
                 ps.setString(1, c.getObjetR());
           
                 ps.setString(2, c.getDetailsR());
                 ps.setInt(3, c.getIdR());
                 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
          
        
               public void traiterReclamation(Reclamation c) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update reclamation set etatR= ? where idR=?");
                       
                 ps.setString(1, c.getEtatR());

                 ps.setInt(2, c.getIdR());
                 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
                        
               
               
       public void sendPDF() throws DocumentException, FileNotFoundException {

        try {
            
            
String file_name = "E:\\generate_pdf\\Frites.pdf";
Document document= new Document();
       PdfWriter.getInstance(document, new FileOutputStream(file_name));  
       PdfPTable table = new PdfPTable(6);
       PdfPCell c1 = new PdfPCell (new Phrase("Id"));
       table.addCell(c1);
       document.open();
            
                 document.add(new Paragraph(" Vos Reclamations "));
                 document.add(new Paragraph(" "));
                  document.add(new Paragraph(" ")); 
            Statement st = Cn.createStatement();

            String req = "SELECT * FROM `reclamation` ";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Reclamation c = new Reclamation();
               
                
                Paragraph para = new Paragraph (
               rs.getInt(1) + " " +
               rs.getInt(2) + " " +
                rs.getDate(3) + " " +
                rs.getString(4) + " " +
                rs.getString(5) + " " + 
                rs.getString(6) ) ;
                document.add(para);
                document.add(new Paragraph(" "));
               
            }

            document.close();
           
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }  
      
     
       
       /****************Recherche
     * @param idR
     * @return 
     * @throws java.sql.SQLException *********************************************************/
    
    public ObservableList<Reclamation> readParIdReclamations(String idR) throws SQLException {
        ObservableList<Reclamation> arr = FXCollections.observableArrayList();
       Statement ste = Cn.createStatement();
        ResultSet rs = ste.executeQuery("select * from reclamations WHERE idR LIKE '" + idR + "' OR WHERE objetR LIKE '" + idR+"'");
        while (rs.next()) {
            
             Reclamation c = new Reclamation();
                c.setUserid(rs.getInt(1));
                c.setIdR(rs.getInt(2));
               
                c.setDateR(rs.getDate(3));
                c.setObjetR(rs.getString(4));
                c.setEtatR(rs.getString(5));
                c.setDetailsR(rs.getString(6));
               
            arr.add(c);

        }
        return arr;
    }
               
       
    
}



/*


public ObservableList<Reclamation> getAllReclamationsFRONT(int id) {
        ObservableList<Reclamation> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT idR , dateR, objetR, etatR, detailsR FROM reclamation  where id_client = '" + id + "' ";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Reclamation c = new Reclamation();
                c.setIdR(rs.getInt(1));
                c.setDateR(rs.getDate(2));
                c.setObjetR(rs.getString(3));
                c.setEtatR(rs.getString(4));
                c.setDetailsR(rs.getString(5));
               
                l.add(c);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    //Cottroler : rs.getAllReclamationsFRONT(user.getId(id)) //ki hatem yaamel entite
              
                 rs.getAllReclamationsFRONT(3) //hedhi hotha taw


*/