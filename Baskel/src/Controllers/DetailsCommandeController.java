/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import Entite.Details_commande;
import Services.CommandeService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DetailsCommandeController implements Initializable {

    CommandeService sp = new CommandeService();
    List<Details_commande> listInfo = new ArrayList<>();
    int val = 0;

    @FXML
    private Label idClient;
    @FXML
    private Label Totale;

    @FXML
    private Label ref;
    @FXML
    private TableView<Details_commande> ProduitsTable;
    @FXML
    private TableColumn<Details_commande, String> RefProduit;
    @FXML
    private TableColumn<Details_commande, String> NomProduit;
    @FXML
    private TableColumn<Details_commande, String> QteProduit;
    @FXML
    private TableColumn<Details_commande, String> PrixProduits;
    @FXML
    private Label etat;
    @FXML
    private Label lEtativraison;
    @FXML
    private Label Day;
    @FXML
    private Label Month;
    @FXML
    private Label year;
    @FXML
    private Button GeneratePdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       


    }

    public void setResId(int id) {

        // Affichage des donnes Dans sceen
        try {
            listInfo = sp.readAllDetailsCommandes(id);
        } catch (SQLException ex) {
            Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Ref
        this.ref.setText(Integer.toString(listInfo.get(0).getIdCommande()));

        val = listInfo.get(0).getIdCommande();

        //ID Cilent
        try {
            this.idClient.setText(Integer.toString(sp.readCommande(val).get(0).getIdClient()));
        } catch (SQLException ex) {
            Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end ID Cilent

        //Totale
        try {
            this.Totale.setText(Double.toString(sp.readCommande(val).get(0).getTOtalPrix()));
        } catch (SQLException ex) {
            Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end Totale

        //etat Payement
        try {
            if (sp.readCommande(val).get(0).getEtat() == 1) {
                this.etat.setText("Paye");
            } else if (sp.readCommande(val).get(0).getEtat() == 0) {
                this.etat.setText("Non Paye");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end etat Payement 

        //etat Livraison
        try {
            if (sp.readCommande(val).get(0).getEtat_liv() == 1) {
                this.lEtativraison.setText("Livree");
            } else if (sp.readCommande(val).get(0).getEtat_liv() == 0) {
                this.lEtativraison.setText("Non Livree");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end etat Livraison

        //Date
        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat DayE = new SimpleDateFormat("dd");
            DateFormat MonthE = new SimpleDateFormat("MM");
            DateFormat YearE = new SimpleDateFormat("yyyy");

            String strDate = dateFormat.format(sp.readCommande(val).get(0).getDate());
            System.out.println("Converted String: " + strDate);

            this.Day.setText(DayE.format(sp.readCommande(val).get(0).getDate()));
            this.Month.setText(MonthE.format(sp.readCommande(val).get(0).getDate()));
            this.year.setText(YearE.format(sp.readCommande(val).get(0).getDate()));

        } catch (SQLException ex) {
            Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end Date

        for (int i = 0; i < listInfo.size(); i++) {
            System.out.println(listInfo.get(i));
            // System.out.println(listInfo.get(i).getIdCommande());
        }
        Afficher();
        //End Dans Screen       
    }

    void Afficher() {

        RefProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        NomProduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        QteProduit.setCellValueFactory(new PropertyValueFactory<>("qteProduit"));
        PrixProduits.setCellValueFactory(new PropertyValueFactory<>("PrixProduit"));

        try {
            ProduitsTable.setItems(sp.readAllDetailsCommandesForTableView(val));
        } catch (SQLException ex) {
            Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * *css*
         */
        GeneratePdf.setOnMouseEntered((event)
                -> GeneratePdf.setStyle("-fx-background-color : #800080; -fx-background-color : none;")
        );

        GeneratePdf.setOnMouseExited((event)
                -> GeneratePdf.setStyle("-fx-text-fill : black;-fx-background-color : none;")
        );
    }

    /**
     * ***********************End
     * Affichage**************************************
     */
    /**
     * Generate Pdf
     */
    void pdf() throws DocumentException, SQLException, BadElementException, IOException {

        try {

            String file_name = "E:\\generate_pdf\\facture2.pdf";
            String IMG = "C:\\wamp64\\www\\javaDesktop\\Baskel\\src\\images\\logo.png";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();

            //Font 
            Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD);

            //Img
            Image image = Image.getInstance(IMG);
            image.scaleAbsolute(50, 50);

            //Paragraph  
            Chunk c = new Chunk(Image.getInstance(image), 0, 0);
            Paragraph para = new Paragraph("    ");
            para.setLeading(0, 1.2f);
            para.setAlignment(Element.ALIGN_JUSTIFIED);
            para.setSpacingBefore(10);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.addCell(getCell("       ", PdfPCell.ALIGN_LEFT));
            table.addCell(getCell("       ", PdfPCell.ALIGN_CENTER));
            table.addCell(getCell("Commande n:" + Integer.toString(val), PdfPCell.ALIGN_RIGHT));
            table.addCell(getCell("       ", PdfPCell.ALIGN_LEFT));
            table.addCell(getCell("       ", PdfPCell.ALIGN_CENTER));
            table.addCell(getCell(sp.readCommande(val).get(0).getDate().toString(), PdfPCell.ALIGN_RIGHT));

            document.add(table);

            document.add(para);
            document.add(c);
            //Table
            PdfPTable ListeProduit = new PdfPTable(4);
            ListeProduit.setSpacingBefore(5);
            PdfPCell c1 = new PdfPCell(new Phrase("Ref Produit"));
            c1.setPadding(5);
            c1.setUseAscender(true);
            c1.setUseDescender(true);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c2 = new PdfPCell(new Phrase("Nom Produit"));
            c2.setPadding(5);
            c2.setUseAscender(true);
            c2.setUseDescender(true);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c3 = new PdfPCell(new Phrase("Qte Produit"));
            c3.setPadding(5);
            c3.setUseAscender(true);
            c3.setUseDescender(true);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c4 = new PdfPCell(new Phrase("Prix Produit"));
            c4.setPadding(5);
            c4.setUseAscender(true);
            c4.setUseDescender(true);
            c4.setHorizontalAlignment(Element.ALIGN_CENTER);

            ListeProduit.addCell(c1);
            ListeProduit.addCell(c2);
            ListeProduit.addCell(c3);
            ListeProduit.addCell(c4);

            ListeProduit.setHeaderRows(1);

            for (int i = 0; i < sp.readAllDetailsCommandesForPdf(val).size(); i++) {
                System.out.println(sp.readAllDetailsCommandesForPdf(val).get(i));
                ListeProduit.addCell(Integer.toString(sp.readAllDetailsCommandesForPdf(val).get(i).getIdProduit()));
                ListeProduit.addCell(sp.readAllDetailsCommandesForPdf(val).get(i).getNomProduit());
                ListeProduit.addCell(Integer.toString(sp.readAllDetailsCommandesForPdf(val).get(i).getQteProduit()));
                ListeProduit.addCell(Double.toString(sp.readAllDetailsCommandesForPdf(val).get(i).getPrixProduit()));
            }

            ListeProduit.addCell(" ");
            ListeProduit.addCell(" ");
            ListeProduit.addCell(" ");
            ListeProduit.addCell(Double.toString(sp.readCommande(val).get(0).getTOtalPrix()));

            document.add(ListeProduit);

            document.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    @FXML
    private void GeneratePdf(ActionEvent event) throws DocumentException, SQLException, BadElementException, IOException {

        pdf();
    }

}
