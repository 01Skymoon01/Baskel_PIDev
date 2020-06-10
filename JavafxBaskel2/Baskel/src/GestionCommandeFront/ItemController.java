/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCommandeFront;

import Controllers.DetailsCommandeController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ItemController implements Initializable {

    @FXML
    private Pane itemC;
    @FXML
    private Label idCommande;
    @FXML
    private Label PrixCommande;
    @FXML
    private Label qteC;
    @FXML
    private Label dateC;
    @FXML
    private Button btnDetailsC;

    int c;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    Pane returnPane() {
        return itemC;
    }
    
    void setIdCommande(int idc){
        idCommande.setText(Integer.toString(idc));
        c=idc;
    }
     
    
    void setPrixCommande(Double prixC){
        PrixCommande.setText(Double.toString(prixC));
    }
    
    void setQteCommande(int QteC){
        qteC.setText(Integer.toString(QteC));
    }
    void setDateCommande(String d){
     dateC.setText(d);
    }
    @FXML
    private void remove(ActionEvent event) {
        
    }

    @FXML
    private void BlackButton(MouseEvent event) {
    }

    @FXML
    private void RedButton(MouseEvent event) {
    }

    @FXML
    private void Details(MouseEvent event) {
    }

    @FXML
    private void btnDetailsC(ActionEvent event) {
        
               Stage home = new Stage();
   
        
        System.out.print("Details succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/DetailsCommande.fxml"));
                   
         try {
              Parent root = loader.load();
              DetailsCommandeController apc = loader.getController();
               apc.setResId(c);
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(DetailsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    
}
