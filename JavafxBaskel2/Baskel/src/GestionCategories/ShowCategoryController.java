/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCategories;

import Entite.Categories;
import Entite.Produits;
import Gui.HomePageController;

import ServiceProduits.ServiceProduits;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ery's Desktop
 */
public class ShowCategoryController implements Initializable {
    
    @FXML
    private TableView <Categories> tableCategorie;
    @FXML
    private TableColumn <Categories,Integer> ref_c;
    @FXML
    private TableColumn <Categories,String> libelle;
    @FXML
    private TextField textUpdate;
    @FXML
    private TextField textUpdate1;
    
    ObservableList<Categories> arr ;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnCat;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane showcatpanel;
    @FXML
    private Button btnRemove;
    @FXML
    private Button updateBtn;
    @FXML
    private Button btnAddCat;
    
    
    @FXML
    public void AddCategory(ActionEvent event) throws IOException {
        //System.out.println("You clicked me!");
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("addCategory.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     
        app_stage.setScene(home_page_scene);
        app_stage.show();  
            
           
    }
    
    
   
    public void ShowCat() {
    ServiceProduits sp = new ServiceProduits();
        try {
            arr = sp.ShowCategories();
        } catch (SQLException ex) {
            Logger.getLogger(ShowCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ref_c.setCellValueFactory(new PropertyValueFactory<>("ref_c"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        
        tableCategorie.setItems(arr);
    }
    
    
    @FXML      
    private void removeCat(ActionEvent event) throws IOException {
        ServiceProduits sp = new ServiceProduits();
        sp.supprimerProduit2(tableCategorie.getSelectionModel().getSelectedItem().getRef_c());
        sp.SupprimerCategorie(tableCategorie.getSelectionModel().getSelectedItem().getRef_c());
        
        /*
        tableCategorie.getItems().clear();
        try {
            tableCategorie.setItems(sp.ShowCategories());
        } catch (SQLException ex) {
            Logger.getLogger(ShowCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
       Stage parentStage = (Stage) tableCategorie.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.AfficherGestionCategories();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
        System.out.println("deleted");
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceProduits sp = new ServiceProduits();
        try {
            arr = sp.ShowCategories();
        } catch (SQLException ex) {
            Logger.getLogger(ShowCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ref_c.setCellValueFactory(new PropertyValueFactory<>("ref_c"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        
        tableCategorie.setItems(arr);
        
        
        
        
        
        FilteredList<Categories> filteredData = new FilteredList<>(arr, b -> true);
		
		
		textUpdate.textProperty().addListener((observable, oldValue, newValue)->{
                    filteredData.setPredicate(p->{
                        if (newValue == null || newValue.isEmpty()) {
					return true;
				}
		
				String lowerCaseFilter = newValue.toLowerCase();
                
				if (p.getLibelle().toLowerCase().contains(lowerCaseFilter) ) {
                                
					return true; 
				}
				else if (String.valueOf(p.getRef_c()).contains(lowerCaseFilter)){
                             System.out.println(p.getRef_c());
                                    return true;
                                    
                                }
                                else  
                                    return false;
                                
                    });
                });
  
                SortedList<Categories> sortedData = new SortedList<>(filteredData);
	
		sortedData.comparatorProperty().bind(tableCategorie.comparatorProperty());

		tableCategorie.setItems(sortedData);
    }
    
    
 
   
     @FXML
    private void updateCat(ActionEvent event) throws IOException {
        
        ServiceProduits sp = new ServiceProduits();
        Categories cr= tableCategorie.getSelectionModel().getSelectedItem();
        //String libelleText=cr.getLibelle();
        //TextField textUpdate;
        
        //textUpdate.setText(libelleText);
       
       
      
        if (textUpdate1.getText() != null) {
           
           sp.modifierContrat(textUpdate1.getText(),cr.getRef_c());
    
           
            
      }
        
  
    
         Stage parentStage = (Stage) tableCategorie.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/HomePage.fxml"));
        Parent root = loader.load();

        HomePageController apc = loader.getController();
        apc.AfficherGestionCategories();

        Scene scene = new Scene(root);
        parentStage.setScene(scene);

        parentStage.show();
        }
        
        
        @FXML
    private void ShowProd(ActionEvent event) {
           
        FXMLLoader loader = new FXMLLoader
            (getClass()
             .getResource("../GestionProduits/showProduct.fxml"));
            try {
                Parent root = loader.load();
             loader.getController();
                
                textUpdate.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }      
    }


}
    

