/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Event;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Nizar
 */
public class EventService {
    
          Connection En = Database.getInstance().getConnection();

          
    public void ajouterEvent(Event E) {
        try {
            Statement st = En.createStatement(); //l'element qui va éxécuter la requete sql

        
            String req = "insert into event ( Date, Nom, Description,nb_participants, responsable, whyattend,image) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ste = En.prepareStatement(req);
            ste.setDate(1, (Date) E.getDate());
            ste.setString(2, E.getNom());
            ste.setString(3, E.getDescription());
            ste.setInt(4, E. getNbParticipants());
            ste.setString(5, E.getEmailresponsable());
            ste.setString(6, E.getWhyattend());
            ste.setString(7,"on hold");
          
           
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    
    public ObservableList<Event> getAllEvent() {
        ObservableList<Event> l = FXCollections.observableArrayList();

        try {
            Statement st = En.createStatement();

            String req = "select id,Date,Nom,Description,nb_participants,responsable,whyattend from event";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
               Event E = new Event();
               E.setId(rs.getInt(1));
               E .setDate(rs.getDate(2));
               E .setNom(rs.getString(3));
               E .setDescription(rs.getString(4));
               E .setNbParticipants(rs.getInt(5));
               E.setEmailresponsable(rs.getString(6));
               E.setWhyattend(rs.getString(7));
               
               l.add(E );
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
    
    public Event  getEventByID(int idE) {
        Event E = new Event();

        try {
            Statement st = En.createStatement();

            String req = "SELECT * FROM event where id=" + idE;

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
               
                
                
               E.setId(rs.getInt(1));
               E .setDate(rs.getDate(2));
               E .setNom(rs.getString(3));
               E .setDescription(rs.getString(4));
               E .setNbParticipants(rs.getInt(5));
               E.setEmailresponsable(rs.getString(6));
               E.setWhyattend(rs.getString(7));
               
              
            }

            return E;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
    
    
   
    public void modifierE(Event E) {
        try {
            
            PreparedStatement ps = En.prepareStatement("update event set Date=?,Nom=?,Description=?,nb_participants=?,responsable=?,whyattend=? WHERE id=?");
       
            ps.setDate(1, (Date) E.getDate());
            ps.setString(2, E.getNom());
            ps.setString(3, E.getDescription());
            ps.setInt(4, E. getNbParticipants());
            ps.setString(5, E.getEmailresponsable());
            ps.setString(6, E.getWhyattend());
            ps.setInt(7, E.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error BOII : "+ex.getMessage());
        }

    }
    
    public void supprimerEvent(int id){
          try {
            Statement st = En.createStatement();
            String req = "delete from event where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression done");
        } catch (SQLException ex) {
          //  Logger.getLogger(AssureEntreprise.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void sendPDF() throws DocumentException, FileNotFoundException {

        try {
            
            
       String file_name = "E:\\generate_pdf\\ListeEvents.pdf";
       Document document= new Document();
       PdfWriter.getInstance(document, new FileOutputStream(file_name));  
       PdfPTable table = new PdfPTable(6);
       PdfPCell c1 = new PdfPCell (new Phrase("Id"));
       table.addCell(c1);
       document.open();
            
                 document.add(new Paragraph(" Votre Liste d'evenements "));
                 document.add(new Paragraph(" "));
                  document.add(new Paragraph(" ")); 
            Statement st = En.createStatement();

            String req = "select id,Date,Nom,Description,nb_participants,responsable,whyattend from event";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                
               
                
                Paragraph para = new Paragraph (
               rs.getInt(1) + " " +
               rs.getDate(2) + " " +
                rs.getString(3) + " " +
                rs.getString(4) + " " +
                rs.getInt(5) + " " + 
                rs.getString(6) + " " + 
                rs.getString(7) ) ;
                document.add(para);
                document.add(new Paragraph(" "));
               
            }

            document.close();
           
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }  
    
}


               
       
       