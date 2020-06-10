/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Commande;
import Entite.Livraison;
import Entite.Livreur;
import adhma.Utils.Database;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Achraf Zaafrane
 */
public class LivraisonService {
      Connection cn = Database.getInstance().getConnection();
    
    
     public void ajouterLivraison(Livraison v){
        
        try {
            Statement st = cn.createStatement();
            String req = "insert into livraison (dateLivraison, idLivreur, idCommande) VALUES (?,7,2)";
            PreparedStatement ps=  cn.prepareStatement(req);
            ps.setDate(1, (Date) v.getDateLivraison());
              
            
            ps.executeUpdate();

        }  catch (SQLException ex){
            System.out.println("Error sql : " + ex.getMessage());

        }
        
    }
     
         public void SupprimerLivraison(int id) {
       
      try {
            Statement st = cn.createStatement();
            String req = "delete from livraison where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            System.out.println("suppression pas ok");
        }
           
      
}
     
        public void modifierLivraison(Livraison v) {
                  try {
             PreparedStatement ps = cn.prepareStatement("update livraison set dateLivraison=? where id=?");
   
            ps.setDate(3, (Date) v.getDateLivraison());

             
             
             ps.executeUpdate();
             }
                   catch (SQLException ex) {
             Logger.getLogger(VehiculeService   .class.getName()).log(Level.SEVERE, null, ex);
         }
    
             }
    
     public ObservableList<Livraison> getAllLivraison() {
           ObservableList<Livraison> livreurs= FXCollections.observableArrayList();

        try {
            Statement st = cn.createStatement();

            String req = "SELECT * FROM livraison";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Livraison c = new Livraison();
                c.setId(rs.getInt(1));
                c.setDateLivraison(rs.getDate(2));
                                c.setCodeConf(rs.getString(3));
           
int idLivreur= rs.getInt(4);
int idCommande= rs.getInt(5);
                Livreur l= new Livreur();
                l.setId(idLivreur);
                Commande cc= new Commande();
                cc.setId(idCommande);
                c.setIdLivreur(l);
               c.setIdCommande(cc);
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
     
      public ObservableList<Integer> getAllLivraisonId() {
           ObservableList<Integer> livreurs= FXCollections.observableArrayList();

        try {
            Statement st = cn.createStatement();

            String req = "SELECT id FROM livraison";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Livraison c = new Livraison();
                c.setId(rs.getInt(1));
            

               
                livreurs.add(c.getId());
            }

            return livreurs;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
      
         public String getNumberLivraison() throws SQLException {

     
            Statement st = cn.createStatement();

            String req = "SELECT count(*) FROM livraison";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

     rs.next();
     String sum = rs.getString(1);
                 return sum;

            }
         
         
         
         
                        
       public void sendPDF() throws DocumentException, FileNotFoundException {

        try {
            
            
       String file_name = "C:\\Users\\Achraf Zaafrane\\Documents\\sana saida\\livraison.pdf";
       Document document= new Document();
       PdfWriter.getInstance(document, new FileOutputStream(file_name));  
       PdfPTable table = new PdfPTable(5);
       PdfPCell c1 = new PdfPCell (new Phrase("Id"));
c1 = new PdfPCell(new Phrase("Cell 1"));
c1.setUseVariableBorders(true);
c1.setBorderColorTop(BaseColor.RED);
c1.setBorderColorBottom(BaseColor.BLUE);

       table.addCell(c1);
       document.open();
                Font f=new Font(FontFamily.TIMES_ROMAN,50.0f,Font.UNDERLINE,BaseColor.RED);

                 document.add(new Paragraph(" Votre livraison ",f));
                 document.add(new Paragraph(" "));
                  document.add(new Paragraph(" ")); 
            Statement st = cn.createStatement();

            String req = "SELECT * FROM `livraison` ";

            ResultSet rs = st.executeQuery(req); //retourne un r�sulat

            while (rs.next()) {
                Livraison c = new Livraison();
               
                
                Paragraph para = new Paragraph (
               rs.getInt(1) + " " +
                rs.getDate(2) + " " +
                rs.getString(3) + " " +
                rs.getInt(4) + " " + 
                rs.getInt(5) ) ;
                document.add(para);
                document.add(new Paragraph(" "));
               
            }

            document.close();
           
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }
       
       
       
       
       
       
}
