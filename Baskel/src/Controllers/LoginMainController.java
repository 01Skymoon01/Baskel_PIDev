/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Gui.HomePageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class LoginMainController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private CheckBox remember;
    @FXML
    private ImageView progress;
    @FXML
    private Label progressLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        progress.setVisible(false);
        progressLabel.setVisible(false);

        
    }    

    @FXML
    private void login(ActionEvent event) {
        
        progress.setVisible(true);
        progressLabel.setVisible(true);

        PauseTransition pt = new PauseTransition();
        
        pt.setDuration(Duration.seconds(3));
        
        pt.setOnFinished(
        ev -> {
        progress.getScene().getWindow().hide();

        Stage home = new Stage();
   
        
        System.out.print("Login succesfully");
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/HomePage.fxml"));
                   
         try {
              Parent root = loader.load();
                      Scene scene = new Scene(root);

                      home.setScene(scene);
                      home.show();              
         } catch (IOException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        );
        pt.play();
        

       
    }
    
}
      