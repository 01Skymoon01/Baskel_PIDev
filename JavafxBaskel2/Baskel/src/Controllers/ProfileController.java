/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.SessionUser;
import Entite.User;
import GuiFront.MainMenuFrontController;
import Services.UploadServices;
import Services.UserService;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author pinxh
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    UserService sp = new UserService();
    @FXML
    private Label emailp;
    @FXML
    private Label cinp;
    @FXML
    private Button changerpassbtn;
    @FXML
    private Button commandesbtn;
    @FXML
    private Button reclamationsbtn;
    @FXML
    private Button livraisonbtn;
    @FXML
    private Label usernamep;
    @FXML
    private Button upload;
    
    private String imgPath = "";
    @FXML
    private Rectangle imagerec;
    
    String imagename;
    
    int cin;
    
    
    
    
    void setData(int id,String username,String email) throws SQLException{
    
    
    this.cinp.setText(String.valueOf(id));
    //System.out.println("55");
    //System.out.println(this.cinp.getText());
//this.cinp.get
    //this.cin = Integer.parseInt(id);
    this.emailp.setText(email);
    this.usernamep.setText(username);
    //U.cin = r.getString("cin");
    this.cin = sp.getCin(this.usernamep.getText());
    //this.imagename = "";
   /* try {
        
            int tmp = Integer.parseInt(this.cinp.getText());
            System.out.println("tmp : " + tmp);
          /* this.imagename = sp.ShowImage(tmp);
         
           if (this.imagename != null){
            System.out.println("imagename : " + this.imagename);  
           // System.out.print("URL : "  + "http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + this.imagename);
            
            Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/"  + this.imagename);
            System.out.println("image : " + image);
            if ( image != null ) {
            //System.out.print(image);
            imagerec.setFill(new ImagePattern(image));
            }
           else { imagerec.setFill(Color.TRANSPARENT);
           }
            /*
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Image chargée !");
            alert.setContentText("redémarrer l'application pour terminer");
            alert.show();}*/
           
       /*    
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
    }

  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        try {
            setData(SessionUser.getInstance().getId() ,SessionUser.getInstance().getUsername(),SessionUser.getInstance().getEmail());
        } catch (SQLException ex) {
           // Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        System.out.println(this.cinp.getText());
        //int cint = Integer.parseInt(this.cinp.getText());
        //String cin = rs.getString("cin");
       
        
    }    
    
  
    @FXML
    private void changerPass(ActionEvent event) {
     
        
        try {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/com/xemacscode/PasswrdEdit.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          PasswrdEditController U = fxmlLoader.getController();
          U.cin = this.cinp.getText();
          Stage stage = new Stage();
          stage.setScene(new Scene(root1));
          stage.show();
         
            } 
          catch (IOException ex) {
          System.out.println("cannot load window");
          Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        
          
          
          }
        
        
        
        
        
        
    }

    @FXML
    private void getCommandes(ActionEvent event) throws IOException {
        
        System.out.println(".. Afficher Commande de client = "+SessionUser.getInstance().getId());
            
            Stage parentStage = (Stage) cinp.getScene().getWindow();
         

             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
             Parent root = loader.load();

             MainMenuFrontController apc = loader.getController();
             apc.AfficherCommande();
            
              Scene scene=new Scene(root);
              parentStage.setScene(scene);
           
              parentStage.show();
        System.out.println(".. End Afficher Commande de client = "+SessionUser.getInstance().getId());
    }

    @FXML
    private void getReclamations(ActionEvent event) {
    }

    @FXML
    private void getLivraisons(ActionEvent event) {
    }

    @FXML
    private void uploadImage(ActionEvent event) throws SQLException {
    
   
        
        FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("All Files", "*.*"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*wav", "*.mp3", "*.aac"),
                new ExtensionFilter("Text Files", "*txt")
            );
            Window ownerWindow = null;
        File file = fileChooser.showOpenDialog(ownerWindow);
                if(file != null){
            this.imgPath = file.getAbsolutePath();
            int cin = Integer.parseInt(this.cinp.getText());
            UploadServices up =new UploadServices();
            //System.out.println("image path upload :" + this.imgPath);
            String path=up.upload(this.imgPath);
            System.out.println("path profile controller : " + path);
            sp.AddImage(cin, path);
            
                }
            
            
           
    System.out.print(this.imgPath);
    
    }
    
    
    
    
}
