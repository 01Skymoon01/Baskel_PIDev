/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.SessionUser;
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
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SignInController implements Initializable {
    
    @FXML
    private Button Login;
    
    UserService sp = new UserService();
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void Login(ActionEvent event) {

        // progress.setVisible(true);
        //progressLabel.setVisible(true);
        PauseTransition pt = new PauseTransition();
        
        pt.setDuration(Duration.seconds(3));
        
        pt.setOnFinished(
                ev -> {
                    // progress.getScene().getWindow().hide();

                    Stage home = new Stage();

                    //System.out.print("Login succesfully");
                    try {
                        try {
                            if (authenticateUser().equals("fail")) {
                                
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Login failed");
                                alert.setHeaderText("Verifier username ou mot de passe !");
                                alert.setContentText("");
                                alert.show();
                                
                                System.out.println("ERROR");
                                
                            } else if (authenticateUser().equals("a:0:{}") == false) {
                                FXMLLoader loader = new FXMLLoader(getClass()
                                        .getResource("/Gui/HomePage.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                
                                home.setScene(scene);
                                home.show();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("ERREUR");
                                alert.setHeaderText("Bienvenue !");
                                alert.setContentText("Authentifié en tant que : Admin");
                                alert.show();
                            } else if (authenticateUser().equals("a:0:{}") == true) {
                                String usernamep = this.username.getText();
                                ResultSet r = sp.GetUserProfile(usernamep);
                                int id = 0;
                                String username = "";
                                String emaildb = "";
                                int cin = 0;
                                while (r.next()) {
                                    
                                    id = r.getInt("id");
                                    SessionUser.getInstance().setId(id);
                                    
                                    username = r.getString("username");
                                    SessionUser.getInstance().setUsername(username);
                                    
                                    emaildb = r.getString("email");
                                    SessionUser.getInstance().setEmail(emaildb);
                                    
                                    cin = r.getInt("cin");
                                    SessionUser.getInstance().setCin(cin);
                                    
                                    System.out.println("Id: " + id);
                                }
                                Parent root = FXMLLoader.load(getClass().getResource("/GuiFront/MainMenuFront.fxml"));

                                /*  FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/GuiFront/MainMenuFront.fxm"));*/
                                //Parent root = loader.load();
                                Scene scene = new Scene(root);
                                /* String usernamep = this.username.getText();
                 ResultSet r =  sp.GetUserProfile(usernamep);
                 ProfileController U = loader.getController();
                 String username = "";
                 String emaildb = "";
                 String cin = "";
                 int id=0;
                 while(r.next()) {
                     
                 id= r.getInt("id");
                 username = r.getString("username");
                 //String username = "maissen";
                 //String cin = "roo"; 
                  emaildb = r.getString("email");
                 //String emaildb = "maissen@gmail.com";
                 //String emaildb = "roo"; 
                 cin = r.getString("cin");
                 //String cin = "11404040";
                 
                 //U.cin = r.getString("cin");
                 //String passdb = "roo"; 
                 System.out.println("Id: "+ id);
                }
                 U.setData(cin,username,emaildb);*/
                                
                                scene.setFill(Color.TRANSPARENT);
                                home.setScene(scene);
                                
                                home.setScene(scene);
                                home.initStyle(StageStyle.TRANSPARENT);
                                home.show();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Login Success");
                                alert.setHeaderText("Bienvenue !");
                                alert.setContentText("Authentifié en tant que : Client");
                                alert.show();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        pt.play();
        
    }
    
    public String authenticateUser() throws SQLException {
        
        String userName = this.username.getText(); //Keeping user entered values in temporary variables.
        String entredpassword = this.password.getText();
        //String hashpassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(this.username.getText());
        System.out.println(this.password.getText());
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet;
        
        String userNameDB;
        String passwordDB;
        String roleDB;
        int enabled;
        resultSet = sp.getUserPass(userName);
        
        while (resultSet.next()) // Until next row is present otherwise it return false
        {
            userNameDB = resultSet.getString("userName"); //fetch the values present in database
            passwordDB = resultSet.getString("password");
            roleDB = resultSet.getString("roles");
            enabled = resultSet.getInt("enabled");
            byte[] passbyte;
            passbyte = entredpassword.getBytes(StandardCharsets.UTF_8);
            BCrypt.Result resultStrict = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verifyStrict(passbyte, passwordDB.getBytes());
            Boolean resultStrictbool = resultStrict.verified;
            System.out.println(resultStrict);
            if (userName.equals(userNameDB) && (enabled == 1) && resultStrictbool) {
                return roleDB; ////If the user entered values are already present in database, which means user has already registered so I will return SUCCESS message.
            }
        }
        
        return "fail";
        
    }
}
