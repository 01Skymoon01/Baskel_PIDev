/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFrites;

import Entite.Technicien;
import Services.FormValidation;
import Services.TechnicienService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class TechnicienBackController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnum;
    @FXML
    private Button AjoutE;
    @FXML
    private TableView<Technicien> TabTech;
    @FXML
    private TableColumn<Technicien, Integer> id;
    @FXML
    private TableColumn<Technicien, String> nom;
    @FXML
    private TableColumn<Technicien, String> prenom;
    @FXML
    private TableColumn<Technicien, String> email;
    @FXML
    private TableColumn<Technicien, Integer> num;
    @FXML
    private TableColumn<Technicien, Integer> cin;
    @FXML
    private Button idModif;
    
    @FXML
    private Label lblcin;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lblemail;
    @FXML
    private Label lblnum;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<Technicien, String> supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         TechnicienService sp = new TechnicienService();
        ObservableList<Technicien> l ;
        l=sp.getAllTechniciensBACK();
        id.setCellValueFactory(new PropertyValueFactory<>("idT"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        num.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        supprimer();
        TabTech.setItems(l);
        
        
        
        
                    FilteredList<Technicien> filteredData = new FilteredList<>(l, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Technicien -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Technicien.getIdT()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Technicien.getNumtel()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                  else if (Technicien.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                   else if (Technicien.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                   else if (Technicien.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                 
				else if (String.valueOf(Technicien.getCin()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Technicien> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind( TabTech.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 TabTech.setItems(sortedData);
       
        
        
        
        
    }    

    @FXML
    private void AjoutE(ActionEvent event) {
        
        boolean testcin = FormValidation.textFieldNotEmpty(tfcin, lblcin, "champ invalide");
        boolean testcin2 = FormValidation.textFieldTypeNumberCIN(tfcin, lblcin, "champ invalide");
        boolean testnom = FormValidation.textFieldNotEmpty(tfnom, lblnom,"champ invalide");
        boolean testnom2 = FormValidation.textFieldTypeAlphabet(tfnom, lblnom,"champ invalide");
        boolean testprenom = FormValidation.textFieldNotEmpty(tfprenom, lblprenom,"champ invalide");
        boolean testprenom2 = FormValidation.textFieldNotEmpty(tfprenom, lblprenom,"champ invalide");
        boolean testemail = FormValidation.textFieldNotEmpty(tfemail, lblemail,"champ invalide");
        boolean testemail2 = FormValidation.textFieldTypeEmail(tfemail, lblemail,"champ invalide");
        boolean testnum = FormValidation.textFieldNotEmpty(tfnum, lblnum,"champ invalide");
        boolean testnum2 = FormValidation.textFieldTypeNumberCIN(tfnum, lblnum,"champ invalide");
        
        if(testcin2 && testcin && testnom && testnom2 && testprenom && testprenom2 && testemail && testnum && testnum2 && testemail2){
            
         int cin = Integer.parseInt(tfcin.getText());  
        
         String nom = tfnom.getText();
         String prenom = tfprenom.getText();
          String email = tfemail.getText();
          int num = Integer.parseInt(tfnum.getText());
                      
            Technicien p = new Technicien ( cin, nom, prenom, email ,num);
            
             TechnicienService sp = new TechnicienService();
             sp.ajouterTechnicien(p);
        TabTech.setItems(sp.getAllTechniciensBACK());     
        TabTech.getItems().clear();
        TabTech.setItems(sp.getAllTechniciensBACK());
        
         Notifications notificationBuilder = Notifications.create()
                .title ("Technicien Ajoute!")
                .text("Un nouveau technicien a ete ajoute.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
        
       
        
        }
        
    }

    @FXML
    private void ItemSelected(MouseEvent event) {
           Technicien cr = new Technicien();
     
         cr = TabTech.getSelectionModel().getSelectedItem();
           
            tfnom.setText(cr.getNom());
            tfcin.setText(Integer.toString(cr.getCin()));
            tfemail.setText(cr.getEmail());
            tfprenom.setText(cr.getPrenom());
            tfnum.setText(Integer.toString(cr.getNumtel()));    
           
        
    }

    @FXML
    private void ModifierE(ActionEvent event) {
        
        
        boolean testcin = FormValidation.textFieldNotEmpty(tfcin, lblcin, "champ invalide");
        boolean testcin2 = FormValidation.textFieldTypeNumberCIN(tfcin, lblcin, "champ invalide");
        boolean testnom = FormValidation.textFieldNotEmpty(tfnom, lblnom,"champ invalide");
        boolean testnom2 = FormValidation.textFieldTypeAlphabet(tfnom, lblnom,"champ invalide");
        boolean testprenom = FormValidation.textFieldNotEmpty(tfprenom, lblprenom,"champ invalide");
        boolean testprenom2 = FormValidation.textFieldNotEmpty(tfprenom, lblprenom,"champ invalide");
        boolean testemail = FormValidation.textFieldNotEmpty(tfemail, lblemail,"champ invalide");
        boolean testemail2 = FormValidation.textFieldTypeEmail(tfemail, lblemail,"champ invalide");
        boolean testnum = FormValidation.textFieldNotEmpty(tfnum, lblnum,"champ invalide");
        boolean testnum2 = FormValidation.textFieldTypeNumberCIN(tfnum, lblnum,"champ invalide");
        
        if(testcin2 && testcin && testnom && testnom2 && testprenom && testprenom2 && testemail && testnum && testnum2 && testemail2){
         
         int cin = Integer.parseInt(tfcin.getText());
         String nom = tfnom.getText();
         String prenom = tfprenom.getText();
          String email = tfemail.getText();
          int num = Integer.parseInt(tfnum.getText());
         
         int id =TabTech.getSelectionModel().getSelectedItem().getIdT();

          TechnicienService sp = new TechnicienService();
           Technicien p = new Technicien ();
           
           p.setCin(cin);
           p.setNom(nom);
           p.setPrenom(prenom);
            p.setNumtel(num);
             p.setEmail(email);
              p.setIdT(id);
           
             sp.modifierTechnicien(p);
             
          TabTech.setItems(sp.getAllTechniciensBACK());
      TabTech.getItems().clear();
        TabTech.setItems(sp.getAllTechniciensBACK());
        
         Notifications notificationBuilder = Notifications.create()
                .title ("Technicien Modifie!")
                .text("Un technicien a ete modife.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
        
        }
    }
    
    
     void supprimer(){
    
     
        //******************************Button Supprimer ***************************/
         Callback<TableColumn<Technicien, String>, TableCell<Technicien, String>> cellFactory2 = (param) -> {
           final TableCell<Technicien, String> cell = new TableCell<Technicien, String>() {
           
                @Override
                public void updateItem(String item, boolean empty) {
                
                    TechnicienService sp = new TechnicienService();
                    
                      super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
 
                        final Button editButton = new Button("X");
                       editButton.setStyle("-fx-background-color : none");
                       
                       
                       /***css**/
                        editButton.setOnMouseEntered( (event) ->

                        editButton.setStyle("-fx-text-fill : #800080; -fx-background-color : none;")
                       );
                      
                        editButton.setOnMouseExited( (event) ->

                        editButton.setStyle("-fx-text-fill : black;-fx-background-color : none;")
                       );
                        //end css ****/
                        
                        editButton.setOnAction(event -> {
                           
                            
                            Technicien c = getTableView().getItems().get(getIndex());
                            
                            System.out.print(c);
                            
                            sp.SupprimerTechnicien(c.getIdT()); 
                            Afficher();
                            
                              Notifications notificationBuilder = Notifications.create()
                .title ("Technicien Suprimme!")
                .text("Un technicien a ete suppime.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showError();
                            
                           
                        }
                        );
                              
                        
                           //w nHotoha fi screen ya habibi
                             setGraphic(editButton);
                             setText(null);
                    }
                }
                    
                };
               return cell;
           };
             
        //nhotou boutton fi cell jdida fi cell 9dima
        supprimer.setCellFactory(cellFactory2);
        

        
        //END BUTTON SUPPRIMER*****************************************************/
    
    }
     
     
     void Afficher(){
    
        TechnicienService sp = new TechnicienService();
         id.setCellValueFactory(new PropertyValueFactory<>("idT"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        num.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));

        supprimer();
       
 
        TabTech.setItems(sp.getAllTechniciensBACK());
    }
/*
    @FXML
    private void SupprimerEventB(ActionEvent event) {
        
           TechnicienService sp = new TechnicienService();
        sp.SupprimerTechnicien(TabTech.getSelectionModel().getSelectedItem().getIdT());
        
        TabTech.getItems().clear();
        TabTech.setItems(sp.getAllTechniciensBACK());
        
           Notifications notificationBuilder = Notifications.create()
                .title ("Technicien Suprimme!")
                .text("Un technicien a ete suppime.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showError();
      
    }*/

    @FXML
    private void Recherche(KeyEvent event) {
    }
    
   
    
    
    
}
