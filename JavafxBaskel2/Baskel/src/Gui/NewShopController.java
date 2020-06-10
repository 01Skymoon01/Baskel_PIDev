/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class NewShopController implements Initializable {

    @FXML
    private Label nom0;
    @FXML
    private Label Prix0;
    @FXML
    private Spinner<?> qte0;
    @FXML
    private Button AjouterProduit0;
    @FXML
    private Label nom3;
    @FXML
    private Spinner<?> qte3;
    @FXML
    private Label Prix3;
    @FXML
    private Label nom2;
    @FXML
    private Spinner<?> qte2;
    @FXML
    private Label Prix2;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblDetails;
    @FXML
    private FontAwesomeIconView GoPanier;
    @FXML
    private Label NbrItems;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterProduit0(ActionEvent event) {
    }

    @FXML
    private void AjouterProduit3(ActionEvent event) {
    }

    @FXML
    private void AjouterProduit2(ActionEvent event) {
    }

    @FXML
    private void GoPanier(MouseEvent event) {
    }
    
}
