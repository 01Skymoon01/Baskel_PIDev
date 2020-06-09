/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionPanierEtWishlist;

import Entite.Produits;
import GuiFront.MainMenuFrontController;
import ServiceProduits.ServiceProduits;
import Services.CommandeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ItemWhishlistController implements Initializable {

    @FXML
    private Pane itemC;
    @FXML
    private Rectangle rectangleImg;
    @FXML
    private Label nom01;
    @FXML
    private Label refP;
    @FXML
    private Label Prix01;
    @FXML
    private Label qte;
    Produits porduit = new Produits();
    
    ServiceProduits sp = new ServiceProduits();
    CommandeService spN = new CommandeService();
    int idClient=5;
   
    /**
     * Initializes the controller class.
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void remove(ActionEvent event) {
        /*
             Stage parentStage = (Stage) itemC.getScene().getWindow();
        spN.DeleteItemFromPanier(refpanier);
  
 
   
     
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
             Parent root = loader.load();

             MainMenuFrontController apc = loader.getController();
             apc.AfficherPanier();
     
              Scene scene=new Scene(root);
              parentStage.setScene(scene);
           
              parentStage.show();*/
    }

    Pane returnPane() {
        return itemC;
    }
    
    void showDetails(Produits porduit) throws SQLException{
     // porduit =  sp.showAllInfoOfProduct(id);
      Prix01.setText(Double.toString(porduit.getPrix_p()));
      
      
      nom01.setText(porduit.getNom_p() );
      
      int i = porduit.getQuantite_p();
      if(i > 0)
      qte.setText("En stock !");
      else  qte.setText("Hors stock !");

      System.out.println("image produit: "+porduit.getImage());
      Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" +porduit.getImage() );

       rectangleImg.setFill(new ImagePattern(image));
    }

}
