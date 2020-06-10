/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commande;
import Entite.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author pinxh
 */
public class ClientController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField Recherche;
    @FXML
    private TableView<User> Userlist;
    @FXML
    private TableColumn<User, String> cin;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> email;
    
    UserService sp = new UserService();
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button modifierbtn;
    private final ObservableList<User> dataList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, String> mdp;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Button desactiver;
    @FXML
    private Button activer;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Afficher();
        Recherche();
        
    }    
    
    public void Afficher(){

        
        /*TABLEAU*/id.setCellValueFactory(new PropertyValueFactory<>("id"/*CLASSE*/));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        
        //password.setCellValueFactory(new PropertyValueFactory<>("password"));
        try {
            Userlist.setItems(sp.readAll());
         
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerb(ActionEvent event) {
        
        User t = this.Userlist.getSelectionModel().getSelectedItem();
        int id = t.getId();
        try {
            
             Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez vous vraiement supprimer ?");
            alert.setContentText("Ok pour confirmer.");
            Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
   
    sp.SupprimerUser(id);
    Afficher();   
     Alert alert2 = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Supprimé !");
            alert.setContentText("");
            alert.showAndWait();
         
           
} else {
    Afficher();
}
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void modifierb(ActionEvent event) {
       
    try {
          User t = this.Userlist.getSelectionModel().getSelectedItem();
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/com/xemacscode/Update.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          UpdateController U = fxmlLoader.getController();
          U.setData(t.getId(), t.getUsername(), t.getEmail());
          Stage stage = new Stage();
          stage.setScene(new Scene(root1));
          stage.show();
          Afficher();
          
          
                    } 
          catch (IOException ex) {
          System.out.println("cannot load window");
          Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
      @FXML
    private void desactiverCompte(ActionEvent event) throws SQLException {
         User t = this.Userlist.getSelectionModel().getSelectedItem();
         sp.DesactiverUser(t.getId());
          Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Opération effectuée");
             alert.setHeaderText("Compte numéro '" + t.getId() + "' associé a l'utilisateur '" + t.getUsername() + "'  Desactivé !");
            alert.setContentText("");
            alert.showAndWait();
      
    
    }
    
     @FXML
    private void activerCompte(ActionEvent event) throws SQLException {
         User t = this.Userlist.getSelectionModel().getSelectedItem();
         sp.activerUser(t.getId());
          Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Opération effectuée");
            System.out.println(t.getId());
            System.out.println(t.getUsername());
            alert.setHeaderText("Compte numéro '" + t.getId() + "' associé a l'utilisateur '" + t.getUsername() + "'  Activé !");
            alert.setContentText("");
            alert.showAndWait();
    
    }
    
    
    void Recherche (){
    
    Recherche.setOnKeyPressed((KeyEvent ke) -> {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            System.out.println("Roo");
            /*TABLEAU*/cin.setCellValueFactory(new PropertyValueFactory<>("cin"/*CLASSE*/));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
          //password.setCellValueFactory(new PropertyValueFactory<>("password"));
            
       
         
         try {
              Userlist.setItems(sp.RechercherUser(Recherche.getText()));
            } catch (SQLException ex) {
              Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        else {Afficher();}
    });
    }
    
    User getinputs(){
        return this.Userlist.getSelectionModel().getSelectedItem();
    }

   

  

}
