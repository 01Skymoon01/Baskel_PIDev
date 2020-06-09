/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionProduits;

import Gui.HomePageController;
import GuiFront.MainMenuFrontController;
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
    private Button GoProduits;
    @FXML
    private Button GoCategories;
    @FXML
    private Label titre1;
    @FXML
    private Label titre2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GoProduits.setOnMouseEntered((event)
                -> {
            GoProduits.setStyle(" -fx-background-color : #365D84;");
            titre1.setVisible(true);
        }
        );

        GoProduits.setOnMouseExited((event)
                -> {
            GoProduits.setStyle("-fx-background-color :  #1B2A47;");
            titre1.setVisible(false);

        });

        GoCategories.setOnMouseEntered((event)
                -> {
            GoCategories.setStyle(" -fx-background-color : #365D84;");
            titre2.setVisible(true);
        }
        );

        GoCategories.setOnMouseExited((event)
                -> {
            GoCategories.setStyle("-fx-background-color :  #1B2A47;");
            titre2.setVisible(false);

        });
    }

    @FXML
    private void GoProduits(ActionEvent event) throws IOException {

        Stage parentStage = (Stage) GoProduits.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.AfficherGestionProduits();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
    }

    @FXML
    private void GoCategories(ActionEvent event) throws IOException {

        Stage parentStage = (Stage) GoProduits.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.AfficherGestionCategories();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();

    }

}
