/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionLivraison;

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
    private Button goToLivreur;
    @FXML
    private Button goToVehicule;
    @FXML
    private Button goToLivraison;
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
        goToLivreur.setOnMouseEntered((event)
                -> {
            goToLivreur.setStyle(" -fx-background-color : #365D84;");
            titre1.setVisible(true);
        }
        );

        goToLivreur.setOnMouseExited((event)
                -> {
            goToLivreur.setStyle("-fx-background-color :  #1B2A47;");
            titre1.setVisible(false);

        });

        goToVehicule.setOnMouseEntered((event)
                -> {
            goToVehicule.setStyle(" -fx-background-color : #365D84;");
            titre2.setVisible(true);
        }
        );

        goToVehicule.setOnMouseExited((event)
                -> {
            goToVehicule.setStyle("-fx-background-color :  #1B2A47;");
            titre2.setVisible(false);

        });

        goToLivraison.setOnMouseEntered((event)
                -> {
            goToLivraison.setStyle(" -fx-background-color : #365D84;");
            titre3.setVisible(true);
        }
        );

        goToLivraison.setOnMouseExited((event)
                -> {
            goToLivraison.setStyle("-fx-background-color :  #1B2A47;");
            titre3.setVisible(false);

        });

    }

    @FXML
    private void goToLivreur(ActionEvent event) throws IOException {

        Stage parentStage = (Stage) goToLivraison.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.livreur();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }

    @FXML
    private void goToVehicule(ActionEvent event) throws IOException {

        Stage parentStage = (Stage) goToLivraison.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.Vehicule();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }

    @FXML
    private void goToLivraison(ActionEvent event) throws IOException {

        Stage parentStage = (Stage) goToLivraison.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.livraison();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }

}
