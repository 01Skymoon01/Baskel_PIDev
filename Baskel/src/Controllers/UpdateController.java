/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.User;
import Services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pinxh
 */
public class UpdateController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    
    UserService sp = new UserService();
    @FXML
    private Button confirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setEditable(false);
        
    }    
    
    void setData(int id,String username,String email){
    this.name.setText(String.valueOf(id));
    this.email.setText(email);
    this.password.setText(username);
    }
    
 

    @FXML
    private void updateUser(ActionEvent event) {
        
        int tmp = Integer.parseInt(name.getText());
        try {
            
            sp.ModifierUser(tmp,  password.getText(), email.getText());
            
         
            
           
        } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
