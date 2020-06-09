/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Panier;
import Services.CommandeService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ShopController implements Initializable {

    CommandeService sp = new CommandeService();
    int id_client = 5;

    @FXML
    private Label nom0;
    @FXML
    private Label Prix0;
    @FXML
    private Spinner<Integer> qte0;

    @FXML
    private Label NbrItems;
    @FXML
    private Label nom3;
    @FXML
    private Spinner<Integer> qte3;
    @FXML
    private Label Prix3;
    private Label nom4;
    private Spinner<Integer> qte4;
    private Label Prix4;
    @FXML
    private Label nom2;
    @FXML
    private Spinner<Integer> qte2;
    @FXML
    private Label Prix2;
    @FXML
    private Button AjouterProduit3;
    @FXML
    private Button AjouterProduit2;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblDetails;
    @FXML
    private Button AjouterProduit0;
    @FXML
    private FontAwesomeIconView GoPanier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Count Items
        try {
            NbrItems.setText(Integer.toString(sp.NbrProduitAuPanier(id_client)));
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // End Count Items

        // QTE PRODIUT
        final int initialValue = 3;

        SpinnerValueFactory<Integer> valueFactory
                = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);

        SpinnerValueFactory<Integer> valueFactory2
                = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);

        SpinnerValueFactory<Integer> valueFactory3
                = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);

     

        qte0.setValueFactory(valueFactory);
        qte2.setValueFactory(valueFactory2);
        qte3.setValueFactory(valueFactory3);
//        qte4.setValueFactory(valueFactory4);

        // EndQTE PRODIUT
    }

    @FXML
    private void AjouterProduit0(ActionEvent event) throws SQLException {

        String nom_prod = nom0.getText();
        String Prix = Prix0.getText();
        double prix_prod = Double.parseDouble(Prix);
        int qte_prod = qte0.getValue();
        int refP = 20054;
        Panier p = new Panier(nom_prod, prix_prod, qte_prod, id_client, refP);

     
    }

    @FXML
    private void GoPanier(MouseEvent event) {
        //**Afficher Panier **//

        PauseTransition pt = new PauseTransition();

        pt.setDuration(Duration.seconds(1));

        pt.setOnFinished(
                ev -> {
                    //editButton.getScene().getWindow().hide();

                    Stage home = new Stage();

                    System.out.print("Shop succesfully");
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("/Gui/Front/sample/Panier.fxml"));
                    System.out.print("Shop succesfully");

                    try {
                        Parent root = loader.load();

                        Scene scene = new Scene(root);

                        home.setScene(scene);
                        home.show();
                    } catch (IOException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        pt.play();
        //*****************Fin Panier************//

    }

    @FXML
    private void AjouterProduit2(ActionEvent event) throws SQLException {

        String nom_prod = nom2.getText();
        String Prix = Prix2.getText();
        double prix_prod = Double.parseDouble(Prix);
        int qte_prod = qte2.getValue();
        int refP = 20050;
        Panier p = new Panier(nom_prod, prix_prod, qte_prod, id_client, refP);

    }

    @FXML
    private void AjouterProduit3(ActionEvent event) throws SQLException {

        String nom_prod = nom3.getText();
        String Prix = Prix3.getText();
        double prix_prod = Double.parseDouble(Prix);
        int qte_prod = qte3.getValue();
        int refP = 200589;
        Panier p = new Panier(nom_prod, prix_prod, qte_prod, id_client, refP);

    }

    private void GoShop(MouseEvent event) {
        
        //**Afficher Panier **//

        PauseTransition pt = new PauseTransition();

        pt.setDuration(Duration.seconds(1));

        pt.setOnFinished(
                ev -> {
                    //editButton.getScene().getWindow().hide();

                    Stage home = new Stage();

                    System.out.print("Shop succesfully");
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("/Gui/Front/sample/Panier.fxml"));
                    System.out.print("Shop succesfully");

                    try {
                        Parent root = loader.load();

                        Scene scene = new Scene(root);

                        home.setScene(scene);
                        home.show();
                    } catch (IOException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        pt.play();
        //*****************Fin Panier************//

    }

   

}
