/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionLivraison;


import Entite.Livraison;
import Entite.Livreur;
import Services.FormValidation;
import Services.LivraisonService;
import Services.LivreurService;
import com.itextpdf.text.DocumentException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Achraf Zaafrane
 */
public class LivraisonControllers implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField recherche;
    @FXML
    private TableView<Livraison> tabv;
    @FXML
    private TableColumn<Livraison, Integer> idl;
    @FXML
    private Label lblnbrliv;
    @FXML
    private TableColumn<Livraison, Date> dateliv;
    @FXML
    private TableColumn<Livraison, String> code;
    @FXML
    private TableColumn<Livraison, Integer> idliv;
    @FXML
    private TableColumn<Livraison, Integer> idcommande;
    @FXML
    private Button sendPDF;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        LivraisonService sp = new LivraisonService();
        ObservableList<Livraison> l ;
        l=sp.getAllLivraison();
                String nbrlivreurs;
        try {
            nbrlivreurs = sp.getNumberLivraison();
                    lblnbrliv.setText(nbrlivreurs);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idl.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateliv.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
        code.setCellValueFactory(new PropertyValueFactory<>("codeConf"));
        idliv.setCellValueFactory(new PropertyValueFactory<>("idLivreur"));
        idcommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
    
        tabv.setItems(l);
        
        
        
        FilteredList<Livraison> filteredData = new FilteredList<>(l, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(v -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (v.getCodeConf().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                                                                       
                           
			
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Livraison> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind( tabv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 tabv.setItems(sortedData);
   }    

  
@FXML
    private void sendPDF(ActionEvent event) {
        
         LivraisonService sp = new LivraisonService();
        
         try {
            sp.sendPDF();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(LivraisonControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
