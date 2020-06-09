/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services ;

import Entite.RDV;
import Entite.SessionUser;
import Entite.Technicien;
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
public class RDVService {
    
       Connection Cn = Database.getInstance().getConnection();

    public void ajouterRDV (RDV C) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

            //  String req = "insert into assurance values('" + C.getNom()+ "','" + C.getDescription()+ "','" + C.getId_client()+ "','" + C.getType()+ "','" + C.getDate_debut() + "','" + C.getDate_Echeance()+"')";
          
            String req = "INSERT INTO r_d_v ( `userid`,  `dateDeptRDV`, `dateRDV`,  `objetRDV`,  `etatRDV`,  `detailsRDV` ) VALUES ( ?, ?, ?, ?, ?, ?) ";
            PreparedStatement ste = Cn.prepareStatement(req);
            
            ste.setInt(1, SessionUser.getInstance().getId());
         
            ste.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ste.setDate(3, (Date) C.getDateRDV());
            ste.setString(4, C.getObjetRDV());
            ste.setString(5, C.getEtatRDV());
            ste.setString(6, C.getDetailsRDV());
            
           
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    
    
       public void SupprimerRDV (int idRDV) {
        try {
            Statement st = Cn.createStatement();
            String req = "delete from r_d_v where idRDV=" + idRDV;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            System.out.println("suppression pas ok");
        }

    }

       
       
         public ObservableList<RDV> getAllRDVBACK() {
        ObservableList<RDV> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT * FROM `r_d_v`  ";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                RDV c = new RDV();
               
                 int idT= rs.getInt(1);
                 TechnicienService tt = new TechnicienService();
                  Technicien t = new Technicien();
                 Technicien t1 = tt.getTechnicienByID(idT);
                
                 t.setIdT(idT);
                c.setTechnicienid(t1);
                c. setUserid(rs.getInt(2));
                c.setIdRDV(rs.getInt(3));
                c.setDateDeptRDV(rs.getDate(4));
                c.setDateRDV(rs.getDate(5));
                c.setObjetRDV(rs.getString(6));
                c.setEtatRDV(rs.getString(7));
                c.setDetailsRDV(rs.getString(8));
               
                l.add(c);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }  
               
       
         
          
          public ObservableList<RDV> getAllRDVFRONT() {
        ObservableList<RDV> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT * FROM r_d_v where userid='" +SessionUser.getInstance().getId()+ "'";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                RDV c = new RDV();
                   int idT= rs.getInt(1);
                 TechnicienService tt = new TechnicienService();
                  Technicien t = new Technicien();
                 Technicien t1 = tt.getTechnicienByID(idT);
                
                 t.setIdT(idT);
                c.setTechnicienid(t1);
                c.setUserid(rs.getInt(2));
                c.setIdRDV(rs.getInt(3));
                c.setDateDeptRDV(rs.getDate(4));                
                c.setDateRDV(rs.getDate(5));
                c.setObjetRDV(rs.getString(6));
                c.setEtatRDV(rs.getString(7));
                c.setDetailsRDV(rs.getString(8));
               
                l.add(c);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
      
           
         
           
        
               public void modifierRDV (RDV c) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update r_d_v set dateRDV=?,objetRDV=?, detailsRDV=? where idRDV=?");
            
            
                 ps.setDate(1, (Date) c.getDateRDV());          
                 ps.setString(2, c.getObjetRDV());
                 ps.setString(3, c.getDetailsRDV());           
                 ps.setInt(4, c.getIdRDV());
                 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
               
               
               
               public void affecterRDV (RDV c, int idRDV) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update r_d_v set technicienid= ? where idRDV=?");
            
            
                 ps.setObject(1, c.getTechnicienid().getIdT()) ;       
                 ps.setInt(2, c.getIdRDV());
                 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
                              
               
               
               public void accepterRDV (RDV c) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update r_d_v set etatRDV= ? where idRDV=?");
                       
                 ps.setString(1, c.getEtatRDV());

                 ps.setInt(2, c.getIdRDV());
                 
            ps.executeUpdate();
            
          //  System.out.println("SUCCEEEEEEEESS");
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
               
                  public void refuserRDV (RDV c) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update r_d_v set etatRDV= ? where idRDV=?");
                       
                 ps.setString(1, c.getEtatRDV());

                 ps.setInt(2, c.getIdRDV());
                 
            ps.executeUpdate();
            
          //  System.out.println("SUCCEEEEEEEESS");
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
               
                              
       public void sendPDF() throws DocumentException, FileNotFoundException {

        try {
            
            
       String file_name = "E:\\generate_pdf\\Frites.pdf";
       Document document= new Document();
       PdfWriter.getInstance(document, new FileOutputStream(file_name));  
       PdfPTable table = new PdfPTable(8);
       PdfPCell c1 = new PdfPCell (new Phrase("Id"));
       table.addCell(c1);
       document.open();
            
                 document.add(new Paragraph(" Vos Rendez-Vous "));
                 document.add(new Paragraph(" "));
                  document.add(new Paragraph(" ")); 
            Statement st = Cn.createStatement();

            String req = "SELECT * FROM `r_d_v` ";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                RDV c = new RDV();
               
                
                Paragraph para = new Paragraph (
              rs.getInt(1) + " " +
               rs.getInt(2) + " " +
               rs.getInt(3)+ " " +
                rs.getDate(4) + " " +
                 rs.getDate(5) + " " +
                rs.getString(6) + " " +
                rs.getString(7) + " " + 
                rs.getString(8) ) ;
                document.add(para);
                document.add(new Paragraph(" "));
               
            }

            document.close();
           
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }  
       
       
    
}
