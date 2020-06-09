/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCategories;

import Entite.Categories;
import Entite.Personne;
import ServiceProduits.ServiceProduits;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ery's Desktop
 */
public class AddCategoryController implements Initializable {
    
    
    
    
    
    
    
    @FXML
    private TextField textNomCat;
    
    @FXML
    private TextField textRefC;
    @FXML
    private Label error;
    
    
 
    @FXML
    private Button addBtn;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    private Boolean ControleRef(){
    
        if(textRefC.getText().length()!=6){
            return false;
        }
        return true;
    }
    
    
    private Boolean ControleLibelle(String s){
    
      
      if (s == null) {
         return false;
      }
      
      int len = s.length();
      for (int i = 0; i < len; i++) {

         if ((Character.isLetterOrDigit(s.charAt(i)) == false)) {
            return false;
         }
      }
      return true;
    }
    
    
    @FXML
    private void AjouterCat(ActionEvent event) {
        try {
            
            String refC1 = textRefC.getText();
            int refC = Integer.parseInt(refC1);
            String nomCat = textNomCat.getText();
            
            Categories categorie = new Categories(refC,nomCat);
            ServiceProduits sp = new ServiceProduits();
            
            if ( ControleRef()==true && ControleLibelle(textNomCat.getText())==true ){
            sp.AjouterCat(categorie);
            
                FXMLLoader loader = new FXMLLoader
                            (getClass()
                             .getResource("/Gui/HomePage.fxml"));
                try {
                    Parent root = loader.load();
                    //ShowCategoryController apc = loader.getController();

                    textNomCat.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    }
            
            }  
            else {
                
                
               BoxBlur blur = new BoxBlur(3, 3, 3);
                        
                JFXDialogLayout dialogLayout = new JFXDialogLayout();
                JFXButton button=new JFXButton("OK! Je vérifie.");
                button.getStyleClass().add("dialog-button");
               
                button.setLayoutY(50);
                
               Image image=new Image(getClass().getResource("/Gui/Front/resources/whoups.png").toExternalForm());
               ImageView iv1 = new ImageView();
                iv1.setImage(image);
              //iv1.setTranslateY(50);
            
                JFXDialog dialog= new JFXDialog(rootPane,dialogLayout,JFXDialog.DialogTransition.TOP);
                dialogLayout.getStyleClass().add("dialog-box");
                
                
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{
                    dialog.close();
                });
                Label label=new Label("Vérfiez vos données!");
                label.getStyleClass().add("labelError");
                dialogLayout.setHeading(label);
                dialogLayout.setActions(button);
                
                dialogLayout.getChildren().add(iv1);
                
              
               iv1.setTranslateX(-105);
               iv1.setTranslateY(-30);
               label.setTranslateX(150);
               label.setTranslateY(40);
               button.setTranslateX(-10);
               button.setTranslateY(-10);
               dialogLayout.setPrefHeight(200);
               //button.setTranslateY(90);
               
               
                
                dialog.show();
                
                dialog.setOnDialogClosed((JFXDialogEvent event1)->{
                    rootAnchorPane.setEffect(null);
                });
                rootAnchorPane.setEffect(blur);
                
            }
            
            
            
            
          
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    @FXML
    public void showCat() {
    
    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/HomePage.fxml"));
            try {
                Parent root = loader.load();
                //ShowCategoryController apc = loader.getController();
                
                textNomCat.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }

    }
    
}
