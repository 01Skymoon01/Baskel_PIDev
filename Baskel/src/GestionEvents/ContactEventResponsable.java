/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import Entite.Event;
import Services.EventService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class ContactEventResponsable implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private Button BtnEnvoi;
    @FXML
    private ImageView baskelLogo;
    @FXML
    private TextField idObjet;
    @FXML
    private TextField idMail;
    @FXML
    private ComboBox<Event> ComboRespE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
         EventService lsS = new EventService();
        ObservableList<Event> EE;
        EE = lsS.getAllEvent();
        ComboRespE.setItems(EE);

        ComboRespE.setConverter(new StringConverter<Event>() {
            @Override
            public String toString(Event object) {
                return object.getEmailresponsable();
            }

            @Override
            public Event fromString(String string) {
                return null;
            }

        }
        );
    }    

    @FXML
    private void EnvoyerMail(ActionEvent event) {
        
         String body = idMail.getText();
        String objet= idObjet.getText();
          String Email = ComboRespE.getSelectionModel().getSelectedItem().toString3();
        
        try {
            Services.Mail2.sendMail2(Email,objet,body);
        } catch (Exception ex) {
            Logger.getLogger(AffichageEventsBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
          Image img = new Image("/images/baskel.png");
        Notifications notificationBuilder = Notifications.create()
                .title ("Un Email a ete envoyé!!")
                .text("votre email a été envoyé felicitations!")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER);
           //  notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
    
}
