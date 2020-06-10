/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionProduits;

import Entite.Categories;
import Entite.Produits;
import GestionCategories.ShowCategoryController;
import ServiceProduits.ServiceProduits;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.stage.Window;
import ServiceProduits.UploadServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.util.stream.Collectors;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;



/**
 * FXML Controller class
 *
 * @author Ery's Desktop
 */
public class AddProductController implements Initializable {
    
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private TextArea areaDesc;
    @FXML
    private ComboBox<String> catBox;
    @FXML
    private ComboBox<String> genreBox;
    @FXML
    private TextField textRefp,textProdNom,textQte,textPrix,textMarque;
    @FXML
    public CheckBox blue,black,green,yellow,pink,darkred,purple,whitesmoke;
    @FXML
    private Button browse;
    @FXML
    private Label error;
    
    private FileChooser fileChooser;
    
    private File file;
    private Desktop desktop = Desktop.getDesktop();
    private FileInputStream fis;
    String  imageFile="";
    
    
    ObservableList<String> checkBoxList=FXCollections.observableArrayList();
    
    ServiceProduits sp = new ServiceProduits();
    @FXML
    private Button btnAddProduct;
    
  
    
   
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
       
            ObservableList<String> options2 =
                    FXCollections.observableArrayList(
                            "Femme",
                            "Homme",
                            "Enfant"
                    );
            
            
            genreBox.setItems(options2);
            
            
            
            
            ObservableList<String> options = sp.ShowCategoriesLibelle();
        
            catBox.setItems(options);
  
        } 
        
        
        
        catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }   
    
    
    private Boolean ControleNull(){
        if(textQte.getText().length()==0
                || textMarque.getText().length()==0 || areaDesc.getText().length()==0
               ){
            return false;
        }
        
                    return true;
    }
    
     private Boolean ControleRef(){
          if(textRefp.getText().length()!=6){
            return false;
        }
        return true;
    }
     
    
     
    private Boolean ControleStrings(String s){
    
      
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
    
    
     private Boolean ControlePrix(){
        double prix=Double.parseDouble(textPrix.getText());
      if(prix < 0){
            return false;
        }
        return true;
    }
     
    
    
     private Boolean ControleQte(){
        int quantite=Integer.parseInt(textQte.getText());
      if(quantite < 0){
            return false;
        }
        return true;
    }
    
     
     private Boolean ControleCouleur(){
         if (!blue.isSelected() && !green.isSelected() && !black.isSelected() && !green.isSelected() &&
                !pink.isSelected() && !darkred.isSelected() && !purple.isSelected() &&
                !whitesmoke.isSelected() ){
            return false;
            
        }
         return true;
    }
     
     
    
    
 /*@FXML
    private void modifierProd(ActionEvent event){
     
      UploadServices up =new UploadServices();
     
                Produits p= tableProd2.getSelectionModel().getSelectedItem();
                int id=p.getRef_p();
              if(id != 0){
             System.out.println(id);
                
               String path = up.upload(imageFile);
               System.out.println(path);
               
               sp.modifierProduit(path, id);
               
               
            }
         
    }*/
    
    
    
        
                
               
    
    
    @FXML
    private void fileChoosing(ActionEvent event){
       fileChooser=new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    
                new ExtensionFilter("All Files", "*.*"),
                
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),

                new ExtensionFilter("Audio Files", "*wav", "*.mp3", "*.aac"),

                new ExtensionFilter("Text Files", "*txt")
            
            );
            
          
           
            Window ownerWindow = null;
            file = fileChooser.showOpenDialog(ownerWindow);
                
                if(file != null){
                     imageFile = file.getAbsolutePath();
                }
    }
    
    
    private Boolean ControleImage(){
        //String img=imageFile;
      if(imageFile.length()==0){
            return false;
        }
        return true;
    }
    
    @FXML
    private void AjouterProd(ActionEvent event) {
       
        
       
        try {
            
            System.out.print(imageFile);
            String ref = textRefp.getText();
            int ref2 = Integer.parseInt(ref);
            String nom=textProdNom.getText();
            String marque=textMarque.getText();
            String qte=textQte.getText();
            int qte2=Integer.parseInt(qte);
            String prix=textQte.getText();
            double prix2=Double.parseDouble(prix);
            String desc=areaDesc.getText();
            String gre=genreBox.getValue();
            String catRef=catBox.getValue();
            String catRefSub=catRef.substring(0, 6);
            
            int refCat= Integer.parseInt(catRefSub);
            Categories c=sp.getCategorie(refCat);
            
            if (ControleRef()==true && ControleImage()==true &&
                    ControleStrings(nom) && ControleStrings(marque)
                    && ControlePrix()==true && ControleQte()==true
                    && ControleImage()==true && ControleNull()==true
                    && ControleCouleur()==true){
            
            if(blue.isSelected()){
                checkBoxList.add("\"blue\"");
               
                
            }
            if(black.isSelected()){
                checkBoxList.add("\"black\"");
                
                
            }
            if(green.isSelected()){
                checkBoxList.add("\"green\"");
                
                
            }
            if(yellow.isSelected()){
                checkBoxList.add("\"yellow\"");
                
            }
            if(pink.isSelected()){
                checkBoxList.add("\"pink\"");
                
            }
            if(darkred.isSelected()){
                checkBoxList.add("\"darkred\"");
                
            }
            if(purple.isSelected()){
                checkBoxList.add("\"purple\"");
                
            }
            if(whitesmoke.isSelected()){
                checkBoxList.add("\"whitesmoke\"");
               
            }
            
            
            UploadServices up =new UploadServices();
            
            String path=up.upload(imageFile);
            System.out.print(prix2);
            
            
            Produits produit = new Produits(ref2,c,nom,marque,prix2,qte2,gre,desc,path);
            
            //sp.AjouterProduct(produit,checkBoxList,fis,file);
            sp.AjouterProduct(produit,checkBoxList); 
          //  System.out.println(checkBoxList.);
          
           FXMLLoader loader = new FXMLLoader
                            (getClass()
                             .getResource("/Gui/HomePage.fxml"));
                try {
                    Parent root = loader.load();
                    //ShowCategoryController apc = loader.getController();

                    textQte.getScene().setRoot(root);
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
            
            /*
            else if(ControleRef()== false){
                error.setVisible(true);
                error.setText("Référence doit etre 6 chiffres.");
            }
            else if(ControleImage()== false){
                error.setVisible(true);
                error.setText("Image est obligatoire (non nulle).");
            }
            else if(ControleQte()== false){
                error.setVisible(true);
                error.setText("Quantité ne doit pas etre négative.");
            }
            else if(ControlePrix()== false){
                error.setVisible(true);
                error.setText("Prix ne doit etre négative.");
            }
            else if(ControleStrings(marque)== false){
                error.setVisible(true);
                error.setText("Marque ne doit contenir que des lettres et chiffres.");
            }
            else if(ControleStrings(nom)== false){
                error.setVisible(true);
                error.setText("Nom du produit ne doit contenir que des lettres et chiffres.");
            } else if (ControleNull()==false) {
                error.setVisible(true);
                error.setText("Des données sont manquantes.");
            } 
            else if (ControleCouleur()==false) {
                error.setVisible(true);
                error.setText("Aucun couleur est choisi.");
            }
            */
           
            
            /*FXMLLoader loader = new FXMLLoader
            (getClass()
            .getResource("ShowCategory.fxml"));
            try {
            Parent root = loader.load();
            ShowCategoryController apc = loader.getController();
            
            textNomCat.getScene().setRoot(root);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
            
            */
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    
    @FXML
    private void showProd(ActionEvent event){
      
        FXMLLoader loader = new FXMLLoader
            (getClass()
            .getResource("/Gui/HomePage.fxml"));
            try {
            Parent root = loader.load();
            
            genreBox.getScene().setRoot(root);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
            
    }
    


}