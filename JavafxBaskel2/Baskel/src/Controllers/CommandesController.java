/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commande;
import Entite.Personne;
import Gui.HomePageController;
import Services.CommandeService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CommandesController implements Initializable {

    CommandeService sp = new CommandeService();

    @FXML
    private Pane pnlOverview1;

    private TableView<Personne> personTable;

    private TableColumn<Personne, String> firstNameColumn;
    private TableColumn<Personne, String> lastNameColumn;

    private TableColumn<Personne, String> AgeColumn;
    @FXML
    private TableColumn<Commande, String> id;
    @FXML
    private TableColumn<Commande, String> idClient;
    @FXML
    private TableColumn<Commande, String> date;
    @FXML
    private TableColumn<Commande, String> totale;
    @FXML
    private TableColumn<Commande, String> nbr;
    @FXML
    private TableColumn<Commande, String> paye;
    @FXML
    private TableColumn<Commande, String> liv;
    @FXML
    private TableView<Commande> CommandeTable;
    @FXML
    private TableColumn<Commande, String> Details;
    @FXML
    private TableColumn<Commande, String> supprimer;
    @FXML
    private TextField Recherche;
    @FXML
    private Label NonPaye;
    @FXML
    private Label Revenue;
    @FXML
    private Label ProduitBestSaler;
    @FXML
    private Label ClientFidele;
    @FXML
    private TableColumn<Commande, String> Modifier;
    @FXML
    private FontAwesomeIconView Shop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Afficher();
        Recherche();
       
    }
    
    
    
    void Details(){
    
         //namlou cell feha button
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactory = (param) -> {
            //nhotou feha button
            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
 
                        final Button editButton = new Button("Details");
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
                           
                            
                            Commande c = getTableView().getItems().get(getIndex());
                            
                            System.out.print(c);
                            
                            
                            //**Afficher Details de la commande **//
                            
                             PauseTransition pt = new PauseTransition();
        
        pt.setDuration(Duration.seconds(3));
        
        pt.setOnFinished(
        ev -> {
        //editButton.getScene().getWindow().hide();

        Stage home = new Stage();
   
        
        System.out.print("Details succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/DetailsCommande.fxml"));
                   
         try {
              Parent root = loader.load();
              DetailsCommandeController apc = loader.getController();
               apc.setResId(c.getId());
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        );
        pt.play();
                            //*****************Fin Details************//
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
        Details.setCellFactory(cellFactory);
      
    }
    void supprimer(){
    
     
        //******************************Button Supprimer ***************************/
         Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactory2 = (param) -> {
           final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
           
                @Override
                public void updateItem(String item, boolean empty) {
                
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
                           
                            
                            Commande c = getTableView().getItems().get(getIndex());
                            
                            System.out.print(c);
                            try {
                                sp.SupprimerDetails(c.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            try {
                                sp.SupprimerCommande(c.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
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
    
    
    
        void Modifier(){
    
     
        //******************************Button Modifier ***************************/
         Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactory2 = (param) -> {
           final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
           
                @Override
                public void updateItem(String item, boolean empty) {
                
                      super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
 
                        final Button editButton = new Button("modifier");
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
                           
                              Mail();
                            Commande c = getTableView().getItems().get(getIndex());
                            
                            System.out.print(c);
                            try {
                                sp.ModifierEtatPayement(c.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            

                            
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
        Modifier.setCellFactory(cellFactory2);
        
        //END BUTTON Modifier*****************************************************/
       
    }
    
    /**************************Affichage  ****************************************/
    
    void Afficher(){
    
        try {
            lesParametres();
        } catch (SQLException ex) {
            Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        totale.setCellValueFactory(new PropertyValueFactory<>("TOtalPrix"));
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbrProduit"));
        paye.setCellValueFactory(new PropertyValueFactory<>("etat"));
        liv.setCellValueFactory(new PropertyValueFactory<>("etat_liv"));

        Details();
        supprimer();
        Modifier();
 
        try {
            CommandeTable.setItems(sp.readAllCommandes());
        } catch (SQLException ex) {
            Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*************************End Affichage***************************************/
    

    void Recherche (){
    
   
    
    Recherche.setOnKeyPressed((KeyEvent ke) -> {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            if (Recherche.getText() == null || Recherche.getText().isEmpty()) {
                  Afficher();
            }
            else{
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            totale.setCellValueFactory(new PropertyValueFactory<>("TOtalPrix"));
            nbr.setCellValueFactory(new PropertyValueFactory<>("nbrProduit"));
            paye.setCellValueFactory(new PropertyValueFactory<>("etat"));
            liv.setCellValueFactory(new PropertyValueFactory<>("etat_liv"));
            
            Details();
            supprimer();
            Modifier();
            try {
                CommandeTable.setItems(sp.readParIdCommandes(Recherche.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        }
    });
    }
    
    
   public void lesParametres() throws SQLException{
      
       ClientFidele.setText(sp.ClientPlusFidele());
       Revenue.setText(sp.Revenue());
       NonPaye.setText(sp.NbrCommandeNonPaye());
       ProduitBestSaler.setText(sp.BestSaler());
   } 

    @FXML
    private void GoToShop(MouseEvent event) {
         //**Afficher Shop **//
                            
        PauseTransition pt = new PauseTransition();
        
        pt.setDuration(Duration.seconds(1));
        
        pt.setOnFinished(
        ev -> {
        //editButton.getScene().getWindow().hide();

        Stage home = new Stage();
   
        
        System.out.print("Shop succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/Front/sample/Shop.fxml"));
                           System.out.print("Shop succesfully");

         try {
              Parent root = loader.load();
              
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        );
        pt.play();
                            //*****************Fin Shop************//
        
    }
    
    
    
    /*Email
     */
    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (UnsupportedEncodingException | MessagingException e) {
        }
    }

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
            String filename = "E:\\generate_pdf\\facture2.pdf";
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
        final String fromEmail = "nour.khedher@esprit.tn"; //requires valid gmail id
        final String password = "2Nounour"; // correct password for gmail id
        final String toEmail = "nour.khedher@esprit.tn"; // can be any email id 

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

        sendAttachmentEmail(session, toEmail, "Votre Commande est valide", "merci pour votre confiance :) ");
    }

    @FXML
    private void PlusDeStatistique(MouseEvent event) {
        
         //**Afficher Shop **//
                            
        PauseTransition pt = new PauseTransition();
        
        pt.setDuration(Duration.seconds(1));
        
        pt.setOnFinished(
        ev -> {
        //editButton.getScene().getWindow().hide();

        Stage home = new Stage();
   
        
        System.out.print("RevenueStatistics succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/RevenueStatistics.fxml"));
                           System.out.print("RevenueStatistics succesfully");

         try {
              Parent root = loader.load();
              
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(RevenueStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        );
        pt.play();
                            //*****************Fin Shop************//
    }

   
   
}
