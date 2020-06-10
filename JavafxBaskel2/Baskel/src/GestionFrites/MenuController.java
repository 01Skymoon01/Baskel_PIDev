/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFrites;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MenuController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private Button goToReclamations;
    @FXML
    private Button goToRdv;
        @FXML
    private Label titre1;
    @FXML
    private Label titre2;
    @FXML
    private Label titre3;
@FXML
    private Button goToTech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           goToReclamations.setOnMouseEntered((event)
                -> {
            goToReclamations.setStyle(" -fx-background-color : #365D84;");
            titre1.setVisible(true);
        }
        );

        goToReclamations.setOnMouseExited((event)
                -> {
            goToReclamations.setStyle("-fx-background-color :  #1B2A47;");
            titre1.setVisible(false);

        });
        
           goToRdv.setOnMouseEntered((event)
                -> {
            goToRdv.setStyle(" -fx-background-color : #365D84;");
            titre2.setVisible(true);
        }
        );

        goToRdv.setOnMouseExited((event)
                -> {
            goToRdv.setStyle("-fx-background-color :  #1B2A47;");
            titre2.setVisible(false);

        });
        
           goToTech.setOnMouseEntered((event)
                -> {
            goToTech.setStyle(" -fx-background-color : #365D84;");
            titre3.setVisible(true);
        }
        );

        goToTech.setOnMouseExited((event)
                -> {
            goToTech.setStyle("-fx-background-color :  #1B2A47;");
            titre3.setVisible(false);

        });
    }    

    @FXML
    private void goToReclamations(ActionEvent event) throws IOException {
        
        
        Stage parentStage = (Stage) goToTech.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.afficherRec();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }

    @FXML
    private void goToRdv(ActionEvent event) throws IOException {
           
        
        Stage parentStage = (Stage) goToTech.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.afficherRDV();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show(); 
        
    }

    @FXML
    private void goToTech(ActionEvent event) throws IOException {
        
            
        
        Stage parentStage = (Stage) goToTech.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.afficherTec();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }
    
}
