/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionShop;

import Entite.Produits;
import ServiceProduits.ServiceProduits;
import Services.CommandeService;
import com.jfoenix.controls.JFXDialogLayout;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
public class ItemProduitController implements Initializable {

    @FXML
    private Label nom01;
    @FXML
    private Label Prix01;
    @FXML
    private Pane item;
    @FXML
    private Rectangle rectangleImg;
    @FXML
    private Button Details;
    @FXML
    private Pane ItemForBlur;
    @FXML
    private Button AddToCart;
    @FXML
    private Label refP;

    Produits porduit = new Produits();
   int idclient =5;
    
        ServiceProduits sp = new ServiceProduits();
    CommandeService spN = new CommandeService();
   

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    Pane returnPane() {
        return item;
    }

    void setPrix(double p) {
        Prix01.setText(Double.toString(p));
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

    @FXML
    private void Details(MouseEvent event) throws SQLException {
        System.out.println("NZELET YA DEGLA");
        
       
          FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/GestionShop/DetailsItem.fxml"));

        try {
            Stage stage = new Stage();
            Parent root = loader.load();
            DetailsItemController apc = loader.getController();
            
            //apc.setImage(rectangleImg.getFill());
            apc.setDetails(Integer.parseInt(refP.getText()));
             apc.ShowColors(Integer.parseInt(refP.getText()));
            Scene scene = new Scene(root);

            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);    
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ItemProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    @FXML
    private void ShowButton(MouseEvent event) {

        BoxBlur blur = new BoxBlur(3, 3, 3);
        ItemForBlur.setEffect(blur);
        Details.setVisible(true);
        AddToCart.setVisible(true);

    }


    @FXML
    private void UnShowButton(MouseEvent event) {

        ItemForBlur.setEffect(null);
        Details.setVisible(false);
        AddToCart.setVisible(false);

    }

    @FXML
    private void RedButton(MouseEvent event) {
        Details.setStyle("-fx-background-color : red");

    }

    @FXML
    private void AddToCart(ActionEvent event) throws IOException, SQLException {
        
      Stage parentStage = (Stage) Details.getScene().getWindow();
        if (spN.RecherchePanier(porduit,idclient) == 0) {
        try {
            spN.AjouterProduitAuPanier(porduit,idclient);
        } catch (SQLException ex) {
            Logger.getLogger(ItemProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     Parent root = FXMLLoader.load(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
     Scene scene=new Scene(root);
     parentStage.setScene(scene);
     parentStage.show();
       } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Baskel");
            alert.setHeaderText("vous avez deja ajoute ce produit");
            alert.setContentText(porduit.getNom_p());

            alert.showAndWait();

        }
     
     
        
    }

    @FXML
    private void BlackButton(MouseEvent event) {
        Details.setStyle("-fx-background-color : black");
        
    }

    @FXML
    private void BlackButton2(MouseEvent event) {
        AddToCart.setStyle("-fx-background-color : black");
    }

    @FXML
    private void RedButton2(MouseEvent event) {
        AddToCart.setStyle("-fx-background-color : red");

    }

}
