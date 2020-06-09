/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class HomePageController implements Initializable {

    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private AnchorPane holderPane;

    
     AnchorPane homeCommandes;
    @FXML
    private Button btnProduits;
    @FXML
    private Button btnCommandes;
   
    @FXML
    private Button btnRDV;
    @FXML
    private Button btnLivreur; 
    @FXML
    private Button btnEvents;
    @FXML
    private Button btnUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        createPage();
                                     /***css**/
                        btnProduits.setOnMouseEntered( (event) ->

                        btnProduits.setStyle(" -fx-background-color : #365D84;")
                       );
                      
                        btnProduits.setOnMouseExited( (event) ->

                        btnProduits.setStyle("-fx-background-color : #1B2A47;")
                       );
                        
                                                btnCommandes.setOnMouseEntered( (event) ->

                        btnCommandes.setStyle(" -fx-background-color : #365D84;")
                       );
                      
                        btnCommandes.setOnMouseExited( (event) ->

                        btnCommandes.setStyle("-fx-background-color : #1B2A47;")
                       );
                        
                                        
                        btnRDV.setOnMouseEntered( (event) ->

                        btnRDV.setStyle(" -fx-background-color : #365D84;")
                       );
                      
                        btnRDV.setOnMouseExited( (event) ->

                        btnRDV.setStyle("-fx-background-color : #1B2A47;")
                       );
                        
                         btnLivreur.setOnMouseEntered( (event) ->

                        btnLivreur.setStyle(" -fx-background-color : #365D84;")
                       );
                      
                        btnLivreur.setOnMouseExited( (event) ->

                        btnLivreur.setStyle("-fx-background-color : #1B2A47;")
                       );
                        
                         btnEvents.setOnMouseEntered( (event) ->

                        btnEvents.setStyle(" -fx-background-color : #365D84;")
                       );
                      
                        btnEvents.setOnMouseExited( (event) ->

                        btnEvents.setStyle("-fx-background-color : #1B2A47;")
                       );
                        
                         btnUser.setOnMouseEntered( (event) ->

                        btnUser.setStyle(" -fx-background-color : #365D84;")
                       );
                      
                        btnUser.setOnMouseExited( (event) ->

                        btnUser.setStyle("-fx-background-color : #1B2A47;")
                       );
                        
                        //end css ****/
        
    }    

    
    
    private void setNode(Node node){
    
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
    
    private void createPage(){
    
         try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionProduits/showProduct.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

    
    /**************************MenuProduits**************************************************/
   
    @FXML
    private void btnProduits(ActionEvent event) {
        
        try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionProduits/menu.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    public void AfficherGestionProduits() {
        
        try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionProduits/showProduct.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void AfficherGestionCategories() {
        
        try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionCategories/ShowCategory.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void btnCommandes(ActionEvent event) {
        
        try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/Gui/Commandes.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnCategories(ActionEvent event) {
        
        try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionCategories/ShowCategory.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ProduitsHover(MouseEvent event) {
        
         //btnProduits.setStyle(" -fx-background-color : #171b26;");
    }

    
    /******Menu SVA***********************************/
   
   public void afficherRDV(){

             try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionFrites/RDVBack.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
    @FXML
    private void btnRDV(ActionEvent event) {
                try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionFrites/menu.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherRec(){
    try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionFrites/ReclamationsBack.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    
    public void afficherTec(){
             try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionFrites/TechniciensBack.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/********************MenuLivraision **********************************/
  public void livreur(){
  
      try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionLivraison/Livreur.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
    public void Vehicule(){
  
      try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionLivraison/Vehiculee.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
    
      public void livraison(){
  
      try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionLivraison/Livraison.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
    @FXML
    private void btnLivreur(ActionEvent event) {
        
                        try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionLivraison/menu.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
/*********************Event ***************************************/

 public void Event(){
  
      try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionEvents/AffichageEvents.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
  } 
 
 //AffichagePartenaires.fxml
    
  public void Partenaire(){
  
      try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionEvents/AffichagePartenaires_1.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
  } 
 
  
  //EventEmailContact
      
  public void Contact(){
  
      try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionEvents/EventEmailContact.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
  } 
 
  
    @FXML
    private void btnEvents(ActionEvent event) {
                      try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/GestionEvents/menu.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUser(ActionEvent event) {
        
              try {
            homeCommandes = FXMLLoader.load(getClass().getResource("/Gui/Client.fxml"));
            setNode(homeCommandes);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnLogOut(ActionEvent event) {
                        
           Stage stage = (Stage) btnCommandes.getScene().getWindow();
    // do what you have to do
           stage.close();
    }
    
    
    
    
}
