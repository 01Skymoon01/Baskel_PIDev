/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFrites;

import Entite.RDV;
import Entite.Technicien;
import Services.RDVService;
import Services.TechnicienService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;
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
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class RDVBACKController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TableView<RDV> TabRDVBack;
    @FXML
    private TableColumn<RDV, Integer> idRDVBack;
    @FXML
    private TableColumn<RDV, Integer> useridBack;
    @FXML
    private TableColumn<RDV, Date> dateDepRDVBack;
    @FXML
    private TableColumn<RDV , Date> dateRDVBack;
    @FXML
    private TableColumn<RDV, String> objetRDVBack;
    @FXML
    private TableColumn<RDV, String> etatRDVBack;
    @FXML
    private TableColumn<RDV, String> detailsRDVBack;
    @FXML
    private TableColumn<RDV, String> technicienRDVBack = new TableColumn<>("Technicien");
    @FXML
    private Button sendPDF;
    @FXML
    private Button traiterRDV;
    @FXML
    private Button refuserRDV;
    @FXML
    private ComboBox<Technicien> ComboTechnicien;
    @FXML
    private Button affecterRDV;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<RDV, String> supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TechnicienService tt = new TechnicienService() ; 
       ObservableList<Technicien> TE9 ;
       TE9=tt.getAllTechniciensBACK();
      
        RDVService sp = new  RDVService();
        ObservableList<RDV> l ;
        l=sp.getAllRDVBACK();
      
         
        ComboTechnicien.getItems().setAll(TE9);
        
        useridBack.setCellValueFactory(new PropertyValueFactory<>("userid"));
         idRDVBack.setCellValueFactory(new PropertyValueFactory<>("idRDV"));
        dateDepRDVBack.setCellValueFactory(new PropertyValueFactory<>("dateRDV"));
         dateRDVBack.setCellValueFactory(new PropertyValueFactory<>("dateDeptRDV"));
       objetRDVBack.setCellValueFactory(new PropertyValueFactory<>("objetRDV"));
        etatRDVBack.setCellValueFactory(new PropertyValueFactory<>("etatRDV"));
       detailsRDVBack.setCellValueFactory(new PropertyValueFactory<>("detailsRDV"));
      // if (sp.){
     // technicienRDVBack.setCellValueFactory(new PropertyValueFactory<>("technicienid"));
     // technicienRDVBack.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().getNom()));
      // technicienRDVBack.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().getNom()));
      
  
       technicienRDVBack.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().toString()));
       supprimer();
       
       TabRDVBack.setItems(l);
       
       
       
       
         FilteredList<RDV> filteredData = new FilteredList<>(l, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(RDV -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(RDV.getIdRDV()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} else if (String.valueOf(RDV.getDateRDV()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                 else if (String.valueOf(RDV.getDateDeptRDV()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                  else if (RDV.getEtatRDV().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                   else if (RDV.getObjetRDV().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                   else if (RDV.getDetailsRDV().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                    else if (String.valueOf(RDV.getTechnicienid()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                 
				else if (String.valueOf(RDV.getUserid()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<RDV> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind( TabRDVBack.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 TabRDVBack.setItems(sortedData);
       
       
       
        
    }    

    @FXML
    private void ItemSelected(MouseEvent event) {
    }

    
    
    void supprimer(){
    
      //******************************Button Supprimer ***************************/
         Callback<TableColumn<RDV, String>, TableCell<RDV, String>> cellFactory2 = (param) -> {
           final TableCell<RDV, String> cell = new TableCell<RDV, String>() {
           
                @Override
                public void updateItem(String item, boolean empty) {
                
                    RDVService sp = new RDVService();
                    
                      super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
 
                        final Button editButton = new Button("X");
                       editButton.setStyle("-fx-background-color : none");
                       
                       
                       /***css**/
                        editButton.setOnMouseEntered( (event) ->

                        editButton.setStyle("-fx-text-fill : #800080; -fx-background-color : none;")
                       );
                      
                        editButton.setOnMouseExited( (event) ->

                        editButton.setStyle("-fx-text-fill : black;-fx-background-color : none;")
                       );
                        //end css ****/
                        
                        editButton.setOnAction(event -> {
                           
                            
                            RDV c = getTableView().getItems().get(getIndex());
                            
                            System.out.print(c);
                            
                            sp.SupprimerRDV(c.getIdRDV()); 
                            Afficher();
                            
                            
                      
                           
                        }
                        );
                              
                        
                           //w nHotoha fi screen ya habibi
                             setGraphic(editButton);
                             setText(null);
                    }
                }
                    
                };
               return cell;
           };
             
        //nhotou boutton fi cell jdida fi cell 9dima
        supprimer.setCellFactory(cellFactory2);
        
        //END BUTTON SUPPRIMER*****************************************************/
    }
     
     void Afficher(){
    
      TechnicienService tt = new TechnicienService() ; 
       ObservableList<Technicien> TE9 ;
       TE9=tt.getAllTechniciensBACK();
      
        RDVService sp = new  RDVService();
        ObservableList<RDV> l ;
        l=sp.getAllRDVBACK();
      
         
        ComboTechnicien.getItems().setAll(TE9);
        
        useridBack.setCellValueFactory(new PropertyValueFactory<>("userid"));
         idRDVBack.setCellValueFactory(new PropertyValueFactory<>("idRDV"));
        dateDepRDVBack.setCellValueFactory(new PropertyValueFactory<>("dateRDV"));
         dateRDVBack.setCellValueFactory(new PropertyValueFactory<>("dateDeptRDV"));
       objetRDVBack.setCellValueFactory(new PropertyValueFactory<>("objetRDV"));
        etatRDVBack.setCellValueFactory(new PropertyValueFactory<>("etatRDV"));
       detailsRDVBack.setCellValueFactory(new PropertyValueFactory<>("detailsRDV"));
      // if (sp.){
     // technicienRDVBack.setCellValueFactory(new PropertyValueFactory<>("technicienid"));
     // technicienRDVBack.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().getNom()));
      // technicienRDVBack.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().getNom()));
      
  
       technicienRDVBack.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().toString()));

        supprimer();
       
 
        TabRDVBack.setItems(sp.getAllRDVBACK());
    }
     /*****************************************************************************************************************************/
    
    
    
    
    
    
/*
    private void supprimerRDV(ActionEvent event) {
        
            RDVService sp = new RDVService();
        sp.SupprimerRDV(TabRDVBack.getSelectionModel().getSelectedItem().getIdRDV());
        
       TabRDVBack.setItems(sp.getAllRDVBACK());
        TabRDVBack.getItems().clear();
        TabRDVBack.setItems(sp.getAllRDVBACK());
    }
*/
    
    
    @FXML
    private void sendPDF(ActionEvent event) {
        
         RDVService sp = new RDVService();
        
         try {
            sp.sendPDF();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(ReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void traiterRDV(ActionEvent event) {
        
           int id =TabRDVBack.getSelectionModel().getSelectedItem().getIdRDV();
           RDVService sp = new RDVService();
           RDV p = new RDV ();
           
           p.setEtatRDV("Accepter");
            p.setIdRDV(id);
            
            sp.accepterRDV(p);
            System.out.print(id);
    
            TabRDVBack.setItems(sp.getAllRDVBACK());
            TabRDVBack.getItems().clear();
        TabRDVBack.setItems(sp.getAllRDVBACK());
        
           Notifications notificationBuilder = Notifications.create()
                .title ("Rendez-vous Accepte!")
                .text("Votre Rendez-vous a ete accepte.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
    }

    @FXML
    private void refuserRDV(ActionEvent event) {
        
         int id =TabRDVBack.getSelectionModel().getSelectedItem().getIdRDV();
           RDVService sp = new RDVService();
           RDV p = new RDV ();
           
           p.setEtatRDV("Refuser");
            p.setIdRDV(id);
            
            sp.accepterRDV(p);
            System.out.print(id);
         TabRDVBack.setItems(sp.getAllRDVBACK());    
        TabRDVBack.getItems().clear();
        TabRDVBack.setItems(sp.getAllRDVBACK());
        
           Notifications notificationBuilder = Notifications.create()
                .title ("Rendez-vous Refuse!")
                .text("Votre Rendez-vous a ete refuse.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showError();
        
    }

    @FXML
    private void affecterRDV(ActionEvent event) {
        
        Technicien objet = ComboTechnicien.getValue();

         
         int id =TabRDVBack.getSelectionModel().getSelectedItem().getIdRDV();

          RDVService sp = new RDVService();
           RDV p = new RDV ();
           
          
           p.setTechnicienid(objet);
           p.setIdRDV(id);
           
            sp.affecterRDV(p, id);
             
        TabRDVBack.setItems(sp.getAllRDVBACK());
         TabRDVBack.getItems().clear();
        TabRDVBack.setItems(sp.getAllRDVBACK());
        
        
          Notifications notificationBuilder = Notifications.create()
                .title ("Technicien Affecte!")
                .text("Un technicien a ete affecte au rendez-vous.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
        
        Mail();
    }

   
    
    
    
   /* 
    void pdf() throws DocumentException, SQLException, BadElementException, IOException {

        try {
            RDVService sp = new RDVService();

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
            PdfPCell c1 = new PdfPCell(new Phrase("idRDV"));
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
            Logger.getLogger(RDVBACKController.class.getName()).log(Level.SEVERE, null, ex);
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
    */
    
    
    /**************************EMAIL**************************/
    
    /**
     * Utility method to send email with attachment
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    
    public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("AdminBaskel@Baskel.tn", "Baskel"));

            msg.setReplyTo(InternetAddress.parse("AdminBaskel@Baskel.tn", false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(body);

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Second part is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "E:\\generate_pdf\\Frites.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            msg.setContent(multipart);

            // Send message
            Transport.send(msg);
            System.out.println("EMail Sent Successfully with attachment!!");
        } catch (MessagingException | UnsupportedEncodingException e) {
        }
    }

    public void Mail() {
        final String fromEmail = "baskelunseen@gmail.com"; //requires valid gmail id
        final String password = "unseenbaskel"; // correct password for gmail id
        final String toEmail = "zeineb.sfaxi@esprit.tn"; // can be any email id 

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        //props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        sendAttachmentEmail(session, toEmail, "Traitement de Rendez-vous", "Vous avez ete affecte a un rendez-vous. Verifiez vos activites. ");
    }
    
    
    
    
}
