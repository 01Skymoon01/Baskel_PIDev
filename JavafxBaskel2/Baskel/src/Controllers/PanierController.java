/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commande;
import Entite.Panier;
import Entite.SessionUser;
import Gui.HomePageController;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PanierController implements Initializable {

    CommandeService sp = new CommandeService();

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TableView<Panier> Panier;
    @FXML
    private TableColumn<Panier, String> Supprimer;
    @FXML
    private TableColumn<Panier, String> NOMPRODUIT;
    @FXML
    private TableColumn<Panier, String> PRIXPRODUIT;
    @FXML
    private TableColumn<Panier, String> QTEPRODUIT;
    @FXML
    private TableColumn<Panier, String> modifier;
    @FXML
    private Label IdClient;
    private AnchorPane ModifierItem;
 
         AnchorPane HomeModifier;
    @FXML
    private TableColumn<Panier, String> Ref;
    @FXML
    private Button Valider;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();
        
    }    
    
   
      void supprimer(){
    
     
        //******************************Button Supprimer ***************************/
         Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactory2 = (param) -> {
           final TableCell<Panier, String> cell = new TableCell<Panier, String>() {
           
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
                           
                            
                            Panier p = getTableView().getItems().get(getIndex());
                            
                            //System.out.print(p);
                            try {
                                sp.SupprimerItemFromPanier(5,p.getNom_prod());
                            } catch (SQLException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
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
        Supprimer.setCellFactory(cellFactory2);
        
        //END BUTTON SUPPRIMER*****************************************************/
    
    }
      
      
      
      
    void Modifier(){
    
         //namlou cell feha button
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactory = (param) -> {
            //nhotou feha button
            final TableCell<Panier, String> cell = new TableCell<Panier, String>() {

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
                           
                            
                            Panier p = getTableView().getItems().get(getIndex());
                            
                           // System.out.print(c);
                            
                                             //          createPageModifier(p,5);

                            //**Afficher Details de la commande **//
                            
                             PauseTransition pt = new PauseTransition();
        
        pt.setDuration(Duration.seconds(1));
        
        pt.setOnFinished(
        ev -> {
            
            
            
            
            
      
            pnlOverview1.getScene().getWindow().hide();

        Stage home = new Stage();
   
        
        System.out.print("Details succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/Front/sample/ModifierQuantite.fxml"));
                   
         try {
              Parent root = loader.load();
              ModifierQuantiteController apc = loader.getController();
               apc.lesParametres(p,SessionUser.getInstance().getId());
               
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(ModifierQuantiteController.class.getName()).log(Level.SEVERE, null, ex);
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
        modifier.setCellFactory(cellFactory);
    }
      
      
      
      
      
       /**************************Affichage  ****************************************/
    
    void Afficher(){
    
       /* try {
            lesParametres();
        } catch (SQLException ex) {
            Logger.getLogger(CommandesController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        NOMPRODUIT.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        PRIXPRODUIT.setCellValueFactory(new PropertyValueFactory<>("prix_prod"));
        QTEPRODUIT.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
        Ref.setCellValueFactory(new PropertyValueFactory<>("refP"));


      //  Details();
        supprimer();
        Modifier();
 
        try {
            Panier.setItems(sp.AfficherPanier(SessionUser.getInstance().getId()));
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*************************End Affichage***************************************/
    
    //****Node*****//
    
        
    private void setNode(Node node){
    
        ModifierItem.getChildren().clear();
        ModifierItem.getChildren().add((Node) node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
    }
    
       private void createPageModifier(Panier p , int idClient){
    
        try {
            HomeModifier = FXMLLoader.load(getClass().getResource("/Gui/Front/sample/ModifierQuantite.fxml"));
            
        
            setNode(HomeModifier);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

    @FXML
    private void Valider(ActionEvent event) {
        try {
            sp.ValiderPanier(SessionUser.getInstance().getId());
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        pnlOverview1.getScene().getWindow().hide();

        Stage home = new Stage();
   
        
        System.out.print("Details succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/HomePage.fxml"));
                   
         try {
              Parent root = loader.load();
  
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(ModifierQuantiteController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
}
