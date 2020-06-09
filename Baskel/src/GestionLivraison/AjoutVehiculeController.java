package GestionLivraison;



import Entite.Livreur;
import Entite.Vehicule;
import Services.FormValidation;
import Services.LivreurService;
import Services.VehiculeService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AjoutVehiculeController implements Initializable {


    private Pane pnlOverview;

    @FXML
    private TableView<Vehicule> tabv;
    @FXML
    private TableColumn<Vehicule, Integer> idv;
    @FXML
    private TableColumn<Vehicule, String> matv;
    @FXML
    private TableColumn<Vehicule, String> marv;
    @FXML
    private TableColumn<Vehicule, String> typev;
    @FXML
    private TableColumn<Vehicule, Integer> idl;
    @FXML
    private TextField mat;
    @FXML
    private TextField mar;
    @FXML
    private ComboBox<Livreur> liv;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Pane pnlOverview1;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifer;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Label  matl;
    @FXML
    private Label  marl;
    @FXML
    private Label lblnbrvehicule;
    @FXML
    private TextField recherche;
 
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    VehiculeService sp = new VehiculeService();
        ObservableList<Vehicule> l ;
        l=sp.getAllVehicule();
        String nbrvhicule ;
        try {
            nbrvhicule = sp.getNumberVehicule();
                    lblnbrvehicule.setText(nbrvhicule);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        idv.setCellValueFactory(new PropertyValueFactory<>("id"));
        matv.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marv.setCellValueFactory(new PropertyValueFactory<>("marque"));
        typev.setCellValueFactory(new PropertyValueFactory<>("type"));
          idl.setCellValueFactory(new PropertyValueFactory<>("user"));
        tabv.setItems(l);
         type.getItems().addAll("Camion","Vehicule");
/*
         FOR COMBOBOX BEL CLE 
         */
         LivreurService ls = new LivreurService();
                ObservableList<Livreur> li ;
             li=ls.getAllLivreur();
liv.setItems(li);
liv.setConverter(new StringConverter<Livreur>(){
        @Override
        public String toString(Livreur object) {
return object.getId()+object.getNom()+" "+ object.getPrenom();
        }

        @Override
        public Livreur fromString(String string) {
return null;        }
    
}
       




);
         

  FilteredList<Vehicule> filteredData = new FilteredList<>(l, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(v -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (v.getMatricule().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (v.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                 else if (v.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                                                                       
                           
			
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Vehicule> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind( tabv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 tabv.setItems(sortedData);
    }
   
    @FXML
    public void ajouterVehicule (ActionEvent event) throws SQLException{
        
   
       
boolean matt=FormValidation.textFieldNotEmpty(mat, matl, "la matricule est vide");
         boolean mart=FormValidation.textFieldNotEmpty(mar, marl,  "la marque est vide");
//       boolean matt=Home.FormValidation.textFieldNotEmpty(mat, matl, "la matricule est vide");
               // boolean mart=Home.FormValidation.textFieldNotEmpty(mar, marl, "la marque est vide");
if(matt && mart){
       String chktype = type.getValue();
  //      String txtlivreur = liv.getValue();
         String txtmat = mat.getText();
         String txtmar = mar.getText();
      
         Pattern p = Pattern.compile("\\d+");
                  Matcher m = p.matcher(liv.getSelectionModel().getSelectedItem().toString());
  String ch= "";
                  while(m.find()) {
                      ch=m.group();
          
        }


int foo = Integer.parseInt(ch);
     //  int iliv= Integer.parseInt(txtlivreur);
        // Date d = (Date) java.time.LocalDate.now();
                
       
            Vehicule v = new Vehicule (  txtmat,txtmar, chktype, new Livreur() );
          
             VehiculeService sp = new VehiculeService();
             sp.ajouterVehicule(v,foo);
               String nbrvhicule ;
        try {
            nbrvhicule = sp.getNumberVehicule();
                    lblnbrvehicule.setText(nbrvhicule);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       // tabv.getItems().clear();
        tabv.setItems(sp.getAllVehicule());
}
      
        
    }
    
    
    @FXML
    private void ItemSelected(MouseEvent event) {
        Vehicule cr = new Vehicule();
     
         cr = tabv.getSelectionModel().getSelectedItem();
           
          
            type.getSelectionModel().select(cr.getType());
              liv.getSelectionModel().select(cr.getUser());
            mat.setText(cr.getMatricule());
            
            mar.setText(cr.getMarque());
           
        
    }
    
    
    /**
     * MODIFIER
     */
    @FXML
    private void modifierVehicule (ActionEvent event) {
       
          String type1 = type.getValue();
         String mat1 = mat.getText();
                  String mar1 = mar.getText();

         int id =tabv.getSelectionModel().getSelectedItem().getId();

          VehiculeService sp = new VehiculeService();
           Vehicule p = new Vehicule ();
           
           p.setType(type1);
           p.setMarque(mar1);
           p.setMatricule(mat1);
           p.setId(id);
             sp.modifierVehicule(p);
             
             tabv.setItems(sp.getAllVehicule());
        tabv.getItems().clear();
        tabv.setItems(sp.getAllVehicule());
        
    }
    
    
    
 
    
    
    

    
    
    public void handleClicks(ActionEvent actionEvent) {
        
    }

  

    @FXML
    private void SupprimerVehicule(ActionEvent event) {
        
        VehiculeService sp = new VehiculeService();
        sp.SupprimerVehicule(tabv.getSelectionModel().getSelectedItem().getId());
           String nbrvhicule ;
        try {
            nbrvhicule = sp.getNumberVehicule();
                    lblnbrvehicule.setText(nbrvhicule);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabv.getItems().clear();
        tabv.setItems(sp.getAllVehicule());
    }
    
    
}
