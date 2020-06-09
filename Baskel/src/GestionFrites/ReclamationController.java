package GestionFrites;

import Entite.Reclamation;
import Services.ReclamationService;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class ReclamationController implements Initializable {

    @FXML
    private ComboBox<String> CombObjetRecFront;
    @FXML
    private TextArea TFdetailsFront;
    @FXML
    private TableView<Reclamation> TabRecFront;
    @FXML
    private TableColumn<Reclamation, Date> dateRFront;
    @FXML
    private TableColumn<Reclamation, String> objetRFront;
    @FXML
    private TableColumn<Reclamation, String> etatRFront;
    @FXML
    private TableColumn<Reclamation, String> detailsRFront;
    @FXML
    private Button ajouterRec;
    @FXML
    private TableColumn<Reclamation, Integer> idR;
    @FXML
    private Button modifierRec;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<Reclamation, String> supprimer;
    
  
    
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //css
         /*css*/
        modifierRec.setOnMouseEntered((event)
                -> modifierRec.setStyle(" -fx-background-color : red;")
        );

        modifierRec.setOnMouseExited((event)
                -> modifierRec.setStyle("-fx-background-color : black;")
        );

        ajouterRec.setOnMouseEntered((event)
                -> ajouterRec.setStyle(" -fx-background-color : red;")
        );

        ajouterRec.setOnMouseExited((event)
                -> ajouterRec.setStyle("-fx-background-color : black;")
        );
       CombObjetRecFront.getItems().addAll("Produit non conforme", "Produit abime", "Livraison non recu", "Livraison non conforme", "Probleme de facturation", "Autres..");
       ReclamationService sp = new ReclamationService();
        ObservableList<Reclamation> l ;
        l=sp.getAllReclamationsFRONT();
        idR.setCellValueFactory(new PropertyValueFactory<>("idR"));
        dateRFront.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        objetRFront.setCellValueFactory(new PropertyValueFactory<>("objetR"));
        //etatRFront.setCellValueFactory(new PropertyValueFactory<>("etatR"));
        etatRFront.setCellValueFactory(new PropertyValueFactory<>("etatR"));
        detailsRFront.setCellValueFactory(new PropertyValueFactory<>("detailsR"));
        supprimer();
        TabRecFront.setItems(l);
 
        
         
            FilteredList<Reclamation> filteredData = new FilteredList<>(l, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Reclamation.getIdR()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Reclamation.getDateR()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
                                  else if (Reclamation.getEtatR().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                   else if (Reclamation.getObjetR().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                   else if (Reclamation.getDetailsR().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                 
				else if (String.valueOf(Reclamation.getUserid()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind( TabRecFront.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 TabRecFront.setItems(sortedData);
       
 
        
    }
    
    @FXML
    public void ajouterRec (ActionEvent event) throws SQLException{
         String objet = CombObjetRecFront.getValue();
         String detail = TFdetailsFront.getText();
         Date d = new Date();
        // Date d = (Date) java.time.LocalDate.now();
                
            String nt = "non traitee";
            Reclamation p = new Reclamation ( 9, d, objet, nt ,detail);
            
             ReclamationService sp = new ReclamationService();
             sp.ajouterReclamation(p);
             
       TabRecFront.setItems(sp.getAllReclamationsFRONT());          
     TabRecFront.getItems().clear();
        TabRecFront.setItems(sp.getAllReclamationsFRONT());   
        
        try {
            adhma.Utils.Mail.sendMail("zeineb.sfaxi@esprit.tn");
            
              Notifications notificationBuilder = Notifications.create()
                .title ("Reclamation Ajoutee!")
                .text("Une reclamation a ete ajoutee. Verifiiez votre email.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
            //  notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
        
        } catch (Exception ex) {
            Logger.getLogger(RDVController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
      void supprimer(){
    
     
        //******************************Button Supprimer ***************************/
         Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory2 = (param) -> {
           final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
           
                @Override
                public void updateItem(String item, boolean empty) {
                
                    ReclamationService sp = new ReclamationService();
                    
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
                           
                            
                            Reclamation c = getTableView().getItems().get(getIndex());
                            
                            System.out.print(c);
                            
                            sp.SupprimerReclamation(c.getIdR()); 
                            Afficher();
                      
                           
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
    
        ReclamationService sp = new ReclamationService();
          ObservableList<Reclamation> l ;
        l=sp.getAllReclamationsFRONT();
        idR.setCellValueFactory(new PropertyValueFactory<>("idR"));
        dateRFront.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        objetRFront.setCellValueFactory(new PropertyValueFactory<>("objetR"));
        etatRFront.setCellValueFactory(new PropertyValueFactory<>("etatR"));
        detailsRFront.setCellValueFactory(new PropertyValueFactory<>("detailsR"));

        supprimer();
       
 
        TabRecFront.setItems(sp.getAllReclamationsFRONT());
    }
     /*****************************************************************************************************************************/
    
    
    
        
    private void supprimerRec (ActionEvent event) {
        ReclamationService sp = new ReclamationService();
        sp.SupprimerReclamation(TabRecFront.getSelectionModel().getSelectedItem().getIdR());
         TabRecFront.setItems(sp.getAllReclamationsFRONT());   
        TabRecFront.getItems().clear();
        TabRecFront.setItems(sp.getAllReclamationsFRONT());
        
         Notifications notificationBuilder = Notifications.create()
                .title ("Reclamation Supprimee!")
                .text("Une reclamation a ete supprimee.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
              //notificationBuilder.darkStyle();
        notificationBuilder.showInformation();
      
    }
    
    
    @FXML
    private void ItemSelected(MouseEvent event) {
        Reclamation cr = new Reclamation();
     
         cr = TabRecFront.getSelectionModel().getSelectedItem();
          
            CombObjetRecFront.getSelectionModel().select(cr.getObjetR());
            TFdetailsFront.setText(cr.getDetailsR());
           
        
    }
    
    
   
    
    
    
     @FXML
    private void modifierRec (ActionEvent event) {
       
          String objet = CombObjetRecFront.getValue();
         String detail = TFdetailsFront.getText();
         int id =TabRecFront.getSelectionModel().getSelectedItem().getIdR();

          ReclamationService sp = new ReclamationService();
           Reclamation p = new Reclamation ();
           
           p.setObjetR(objet);
           p.setDetailsR(detail);
           p.setIdR(id);
           
             sp.modifierReclamation(p);
             
        TabRecFront.setItems(sp.getAllReclamationsFRONT());    
       TabRecFront.getItems().clear();
        TabRecFront.setItems(sp.getAllReclamationsFRONT());
        
         Notifications notificationBuilder = Notifications.create()
                .title ("Reclamation Modifiee!")
                .text("Une reclamation a ete modifiee.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
            //  notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
        
    }

    @FXML
    private void Recherche(KeyEvent event) {
    }


    
    
    
}
