/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import UtilFunctions.Email;
import Entite.User;
import Gui.com.xemacscode.MainController;
import bcrypt.BCrypt;
import Services.UserService;
//import bcrypt.BCrypt;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author pinxh
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField cin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    private boolean validateMail(String mail){
    
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(mail);
        
        if (controler.matches()){
        return true;
        }
        return false;
    }
    
    private boolean validateName(String name){
    
        String masque = "^[a-zA-Z0-9\\s]+";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(name);
        
        if (controler.matches() && (name.length() >= 4) ){
        return true;
        }
        return false;
    }

    @FXML
    private void signup(ActionEvent event) throws SQLException, IOException, MessagingException {
        
        String name = this.name.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        String regexTarget = "\\b$2a$\\b";
        /******BCRYPT WORKING String hashpassword = BCrypt.hashpw(password, BCrypt.gensalt(13));*/
        String bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(13, password.toCharArray());
        System.out.println(bcryptChars);
        
       String hashpassword = bcryptChars;
        //String hashpass = hashpassword.replace(regexTarget, "$2y$");
        //System.out.println(bcryptChars);
        int  cin = Integer.parseInt(this.cin.getText());
        if (validateMail(email) && validateName(name)){
        User p = new User(cin,name, email, hashpassword);
        UserService sp = new UserService();
            sp.ajouter(p);
            Email e = new Email();
            String sujet = "compte crée avec succés";
            String message = "Salut" + name + ", votre compte a été crée avec succés, veuillez accéder à notre site pour découvrir nos produits. ";
            //String tmp = "nour.khedher@esprit.tn";
            e.sendEmail(email , sujet, message);
            
          
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/Gui/com/xemacscode/SignIn.fxml"));
            
            
            
             
             Parent root = (Parent)loader.load();
             
             this.name.getScene().setRoot(root); }
        else {
            
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR");
                alert.setHeaderText("Veuillez entrer les champs correctement");
                alert.setContentText("Authentification échouée");
                alert.show();
            
        }
           
            
         }
        
    }
    
