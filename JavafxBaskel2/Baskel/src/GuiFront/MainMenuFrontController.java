/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFront;

import Gui.HomePageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MainMenuFrontController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    AnchorPane homePage;
    @FXML
    private Button shop;
    @FXML
    private Button favoris;
    @FXML
    private Button event;
    @FXML
    private Button SAV;
    @FXML
    private Button logOut;
    @FXML
    private Button rendezVous;
    @FXML
    private Button btnprofil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //AfficherMesReservations();
        createPage();
    }

    private void setNode(Node node) {

        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));

        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

    }

    private void createPage() {

        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionShop/Shop.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void shop(ActionEvent event) {
        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionShop/Shop.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void favoris(ActionEvent event) {
                try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionPanierEtWishlist/WishlistItems.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
    public void AfficherWishlist() {
                try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionPanierEtWishlist/WishlistItems.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void event(ActionEvent event) {///GestionEvents/AffichageEventsFront.fxml
        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionEvents/AffichageEventsFront.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SAV(ActionEvent event) { ///GestionFrites/ReclamationFFront.fxml
        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionFrites/ReclamationFFront.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
                
           Stage stage = (Stage) logOut.getScene().getWindow();
    // do what you have to do
           stage.close();
    }

    @FXML
    private void rendezVous(ActionEvent event) {

        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionFrites/RDVFFront.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AfficherMesReservations() {//MesReservations

        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionEvents/MesReservations.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AfficherPanier() {

        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionPanierEtWishlist/LIstaItems.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void AfficherValiderCommande() {

        try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionPanierEtWishlist/ValiderCommande.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnprofil(ActionEvent event) {
        
        try {
            homePage = FXMLLoader.load(getClass().getResource("/Gui/com/xemacscode/Profile.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*************Commande ************************************/
    
     public void AfficherCommande(){
  
      try {
            homePage = FXMLLoader.load(getClass().getResource("/GestionCommandeFront/ListeOfItems.fxml"));
            setNode(homePage);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
  } 
}
