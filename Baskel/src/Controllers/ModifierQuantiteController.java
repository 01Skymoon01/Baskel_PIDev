/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Panier;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierQuantiteController implements Initializable {

        CommandeService sp = new CommandeService();
        Panier p0 = new Panier();
    @FXML
    private Pane pnlOverview1;
    @FXML
    private Button AjouterProduit0;
    @FXML
    private Label nom0;
    @FXML
    private Label Prix0;
    @FXML
    private Spinner<Integer> qte0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterProduit0(ActionEvent event) {
        
        p0.setQte_prod(qte0.getValue());
            try {
                sp.ModifierItemFromPanier(p0);
            } catch (SQLException ex) {
                Logger.getLogger(ModifierQuantiteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
     
         
/*********************************************************/
AjouterProduit0.getScene().getWindow().hide();
        Stage home = new Stage();
   
        
        System.out.print("Details succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/Front/sample/Panier.fxml"));
                   
         try {
              Parent root = loader.load();
              PanierController apc = loader.getController();
               apc.Afficher();
               
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(ModifierQuantiteController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        /************************************/
    }
    
    public void lesParametres(Panier p,int idClient){
        
        // QTE PRODIUT
        p0.setNom_prod(p.getNom_prod());
        p0.setQte_prod(p.getQte_prod());
        p0.setId_client(idClient);
        final int initialValue = p.getQte_prod();
 
        SpinnerValueFactory<Integer> valueFactory = //
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
 
                    qte0.setValueFactory(valueFactory);

         // EndQTE PRODIUT
     nom0.setText(p.getNom_prod());
     Prix0.setText(Double.toString(p.getPrix_prod()));
     
        
             
        
    
    }
    
}
