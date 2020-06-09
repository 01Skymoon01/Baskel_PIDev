/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionPanierEtWishlist;

import Entite.Produits;
import GestionShop.ItemProduitController;
import GuiFront.MainMenuFrontController;
import ServiceProduits.ServiceProduits;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ItemController implements Initializable {

    @FXML
    private Pane itemC;
    @FXML
    private Rectangle rectangleImg;
    @FXML
    private Label refP;
    @FXML
    private Label nom01;
    @FXML
    private Spinner<Integer> qte;
    @FXML
    private Label Prix01;

    int refpanier;
    /**
     * Initializes the controller class.
     */
    
    Produits porduit = new Produits();
    
    ServiceProduits sp = new ServiceProduits();
    CommandeService spN = new CommandeService();
    @FXML
    private Label Prix011;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      void setPrix(double p) {
        Prix01.setText(Double.toString(p));
        Double prixTotal = p*qte.getValue();
        Prix011.setText(Double.toString(prixTotal));

    }
    
    void setIdItem(int p) {
        try {
            porduit =  sp.showAllInfoOfProduct(p);
        } catch (SQLException ex) {
            Logger.getLogger(ItemProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refP.setText(Integer.toString(p));
    }
    void setNom(String p) {
        nom01.setText(p);
    }

    void setImage(Image image) {
        rectangleImg.setFill(new ImagePattern(image));
    }

    void setQte(int nbStock,int nbrPanier){
    
      final int initialValue = nbrPanier;
 
        SpinnerValueFactory<Integer> valueFactory = //
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, nbStock, initialValue);
 
        qte.setValueFactory(valueFactory);
    }
    
     Pane returnPane() {
        return itemC;
    }
     
     void setIdPanier(int nb){
     refpanier = nb;
     }

    @FXML
    private void remove(ActionEvent event) throws SQLException, IOException {
       
     Stage parentStage = (Stage) itemC.getScene().getWindow();
        spN.DeleteItemFromPanier(refpanier);
  
 
   
     
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
             Parent root = loader.load();

             MainMenuFrontController apc = loader.getController();
             apc.AfficherPanier();
     
              Scene scene=new Scene(root);
              parentStage.setScene(scene);
           
              parentStage.show();
   

    }



    @FXML
    private void modifierQte(MouseEvent event) throws SQLException {
        
          Double prixTotal = Double.parseDouble(Prix01.getText()) *qte.getValue();
        Prix011.setText(Double.toString(prixTotal));
          System.out.println("ref: "+ refpanier+" qte= "+ qte.getValue()+" prix total: "+ prixTotal);
          spN.ModifierQtePanier(refpanier, qte.getValue());
          spN.ModifierPrixPanier(refpanier,prixTotal);
    }
}
