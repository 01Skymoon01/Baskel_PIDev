/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Gui.HomePageController;
//import Services.BCrypt;
import bcrypt.BCrypt;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Arrays;

/**
 * FXML Controller class
 *
 * @author pinxh
 */
public class PasswrdEditController implements Initializable {
    
    public String cin;
    @FXML
    private TextField mdp1;
    @FXML
    private TextField mdp2;
    
    UserService sp = new UserService();
    @FXML
    private Button confirmer2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
   
    }    

 
    
    private void test() throws SQLException{
        
        System.out.println("hello");

  int cintmp = Integer.parseInt(this.cin);
        String mdptmp1 = this.mdp1.getText();
        String mdptmp2 = this.mdp2.getText();
        if (mdp1.equals(mdp2)){
            System.out.println(mdptmp1);
            System.out.println(mdptmp2);
        String bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(13, mdptmp1.toCharArray());
        System.out.println(bcryptChars);
        String hashpassword = bcryptChars;
        sp.changePasswrd(hashpassword,cintmp);
        System.out.println("Mot de passe modifié !"); 
    
    
    }
 
    
}

    @FXML
    private void confirmer(ActionEvent event) throws SQLException {
        test();
    }
}
 
