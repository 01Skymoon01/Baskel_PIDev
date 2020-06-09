package GestionEvents;





import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private Button btnOverview;
    
   

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;



    
    
    
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnPackages1;
    @FXML
    private AnchorPane holderPane;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
         

    }
   
 
 
    
    
    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        
    }
}
