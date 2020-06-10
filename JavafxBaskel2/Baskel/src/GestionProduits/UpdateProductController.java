/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionProduits;

import Entite.Categories;
import Entite.Produits;
import ServiceProduits.ServiceProduits;
import ServiceProduits.UploadServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Ery's Desktop
 */
public class UpdateProductController implements Initializable  {

    /**
     * Initializes the controller class.
     */
    
    
 
    
    @FXML
    private TextField refToUp,textProdNom,textQte,textPrix;
    
    @FXML
    private TextArea areaDesc;
    
    @FXML
    public CheckBox blue,black,green,yellow,pink,darkred,purple,whitesmoke;
    @FXML
    private Label error;
    
    private FileChooser fileChooser;
    
    private File file;
    
    String  imageFile="";
    
    ObservableList<String> checkBoxList=FXCollections.observableArrayList();
    
    ServiceProduits sp = new ServiceProduits();
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private Button browse;
    @FXML
    private Button updateBtn;
    @FXML
    private Label labelRef;
    
    
    
    public void setLabelText(String text){
      // labelRef.setText(text);
     //  this.refToUp = text;
        refToUp.setText(text);
        
       
   }

    void showInfo(ActionEvent event){
        try {
            String ok=refToUp.getText();
            int id=Integer.parseInt(ok);
            //int id=123456;
            
            String nom=sp.showProdNom(id);
            //System.out.print(nom);
            textProdNom.setText(nom);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setLabelText(refToUp.getText());
      //  showInfo();
    }    
  
    
    @FXML
    private void fileChoosing(ActionEvent event){
       fileChooser=new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),

                new FileChooser.ExtensionFilter("Audio Files", "*wav", "*.mp3", "*.aac"),

                new FileChooser.ExtensionFilter("Text Files", "*txt")
            
            );
            
          
           
                Window ownerWindow = null;
                file = fileChooser.showOpenDialog(ownerWindow);
                
                if(file != null){
                     imageFile = file.getAbsolutePath();
                }
            
    }
    
    @FXML
    private void modifierProd(ActionEvent event){
     
      
        try {
            UploadServices up =new UploadServices();
            
            
            
             int id = Integer.parseInt(refToUp.getText());
           // int id = 123456;
            
         
            String nom=sp.showProdNom(id);
            String marque=sp.ShowMarque(id);
            String desc=sp.ShowDescription(id);
            String genre=sp.ShowGenre(id);
            double prix=sp.ShowPrix(id);
            int quantite=sp.ShowQuantite(id);
            String image=sp.ShowImage(id);
            int refC=sp.ShowRefC(id);
            
            
            //    String color=sp.ShowColors(id);
            
            
            String clr=sp.ShowColors(id);
             
               //String clrNew= clr.substring(0, clr.length()-1);
               String clrNew2=clr.substring(1, clr.length()-1);
               //System.out.println(clrNew2);
               //System.out.print(clrNew2);
               String[] colorsArray = clrNew2.split(", ");
               checkBoxList.addAll(Arrays.asList(colorsArray));
            
            
            
            if(up.upload(imageFile).compareTo("")!=0){
                image = up.upload(imageFile);
               // System.out.println(path);
                
               // sp.modifierProduitImage(path, id);
            }
            
            if(textProdNom.getText().compareTo("") != 0){
            //sp.modifierProduitNom(textProdNom.getText(), id);
            nom=textProdNom.getText();
            }
            
            if(textQte.getText().compareTo("") != 0){
            //sp.modifierProduitQte(Integer.parseInt(textQte.getText()),id);
            quantite=Integer.parseInt(textQte.getText());
            }
            
            if(textPrix.getText().compareTo("") != 0){
           // sp.modifierProduitPrix(Integer.parseInt(textPrix.getText()),id);
            prix=Double.parseDouble(textPrix.getText());
            sp.setRemiseAZero(0,id);
            }
            if(areaDesc.getText().compareTo("") != 0){
            //sp.modifierProduitDesc(areaDesc.getText(), id);
            desc=areaDesc.getText();
            }
            
            checkBoxList.clear();
            if(blue.isSelected() ){
                if(!checkBoxList.contains(blue.getId())){
                checkBoxList.add("\"blue\"");}
                
            }
            if(black.isSelected()){
                if(!checkBoxList.contains(black.getId())){
                checkBoxList.add("\"black\"");}
                
            }
            if(green.isSelected()){
                if(!checkBoxList.contains(green.getId())){
                checkBoxList.add("\"green\"");}
                
            }
            if(yellow.isSelected()){
                if(!checkBoxList.contains(yellow.getId())){
                checkBoxList.add("\"yellow\"");}
                
            }
            if(pink.isSelected()){
                if(!checkBoxList.contains(pink.getId())){
                checkBoxList.add("\"pink\"");}
                
            }
            if(darkred.isSelected()){
                if(!checkBoxList.contains(darkred.getId())){
                checkBoxList.add("\"darkred\"");}
                
            }
            if(purple.isSelected()){
                if(!checkBoxList.contains(purple.getId())){
                checkBoxList.add("\"purple\"");}
                
            }
            if(whitesmoke.isSelected()){
                if(!checkBoxList.contains(whitesmoke.getId())){
                checkBoxList.add("\"whitesmoke\"");}
                
            }
            //sp.modifierProduitCouleur(checkBoxList,id);
            
            Categories c=sp.getCategorie(refC);
            
            
           
            Produits p= new Produits(id,c,nom,marque,prix,quantite,genre,desc,image);
            
            
             if(quantite >= 0 && prix >= 0 && ControleStrings(nom)==true){
                sp.updateProduct(p, checkBoxList);}
             else if(quantite <0 ){
                  error.setVisible(true);
                error.setText("Quantité ne doit pas etre négative.");
             }
             else if(prix <0 ){
                error.setVisible(true);
                error.setText("Prix ne doit etre négative.");
             }
             else if(ControleStrings(nom)== false ){
                 error.setVisible(true);
                error.setText("Nom du produit ne doit contenir que des lettres et chiffres.");
             }
             
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
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
               
    }
         
    

    
  

    @FXML
    private void showProd(ActionEvent event){
      
        FXMLLoader loader = new FXMLLoader
            (getClass()
            .getResource("/Gui/HomePage.fxml"));
            try {
            Parent root = loader.load();
            
            refToUp.getScene().setRoot(root);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
            
    }
    
}


