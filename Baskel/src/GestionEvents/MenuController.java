/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import Gui.HomePageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MenuController implements Initializable {


    @FXML
    private Button goToEvent;
    @FXML
    private Button goToContact;
    @FXML
    private Button goToPartner;
    @FXML
    private Label titre1;
    @FXML
    private Label titre2;
    @FXML
    private Label titre3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           goToEvent.setOnMouseEntered((event)
                -> {
            goToEvent.setStyle(" -fx-background-color : #365D84;");
            titre1.setVisible(true);
        }
        );

        goToEvent.setOnMouseExited((event)
                -> {
            goToEvent.setStyle("-fx-background-color :  #1B2A47;");
            titre1.setVisible(false);

        });

        goToContact.setOnMouseEntered((event)
                -> {
            goToContact.setStyle(" -fx-background-color : #365D84;");
            titre2.setVisible(true);
        }
        );

        goToContact.setOnMouseExited((event)
                -> {
            goToContact.setStyle("-fx-background-color :  #1B2A47;");
            titre2.setVisible(false);

        });

        goToPartner.setOnMouseEntered((event)
                -> {
            goToPartner.setStyle(" -fx-background-color : #365D84;");
            titre3.setVisible(true);
        }
        );

        goToPartner.setOnMouseExited((event)
                -> {
            goToPartner.setStyle("-fx-background-color :  #1B2A47;");
            titre3.setVisible(false);

        });

    }    

    @FXML
    private void goToEvent(ActionEvent event) throws IOException {
        
        Stage parentStage = (Stage) goToEvent.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.Event();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }

    @FXML
    private void goToContact(ActionEvent event) throws IOException {
        Stage parentStage = (Stage) goToEvent.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.Contact();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }

    @FXML
    private void goToPartner(ActionEvent event) throws IOException {
        
        Stage parentStage = (Stage) goToEvent.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.Partenaire(); 

        Scene scene = new Scene(root);
        parentStage.setScene(scene);
         
        parentStage.show();
    }
    
}
