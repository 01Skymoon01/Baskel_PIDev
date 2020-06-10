package GestionFrites;

import Entite.Personne;
import Entite.Reclamation;

import Services.ReclamationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class ReclamationBackController implements Initializable {

    private Button btnOverview;

    private Button btnOrders;

    private Button btnCustomers;

    private Button btnMenus;


    private Pane pnlCustomer;

    private Pane pnlOrders;

    private Pane pnlOverview;

    private Pane pnlMenus;
    
  
    @FXML
    private TableView<Reclamation> TabRecBack;
    @FXML
    private TableColumn<Reclamation, Integer> useridBack;
    @FXML
    private TableColumn<Reclamation, Integer> idRBack;
    @FXML
    private TableColumn<Reclamation, Date> dateRBack;
    @FXML
    private TableColumn<Reclamation, String> objetRBack;
    @FXML
    private TableColumn<Reclamation, String> etatRBack;
    @FXML
    private TableColumn<Reclamation, String> detailsRBack;
    @FXML
    private Pane pnlOverview1;
    @FXML
    private Button supprimerRBACK;
    @FXML
    private Button idModif1;
    @FXML
    private Button TraiterR;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<Reclamation, String> supprimer;
   
    

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        ReclamationService sp = new ReclamationService();
        ObservableList<Reclamation> l ;
        l=sp.getAllReclamationsBACK();
       
        idRBack.setCellValueFactory(new PropertyValueFactory<>("idR"));
        useridBack.setCellValueFactory(new PropertyValueFactory<>("userid"));
        dateRBack.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        objetRBack.setCellValueFactory(new PropertyValueFactory<>("objetR"));
        etatRBack.setCellValueFactory(new PropertyValueFactory<>("etatR"));
        detailsRBack.setCellValueFactory(new PropertyValueFactory<>("detailsR"));
        supprimer();
        TabRecBack.setItems(l);
        
        
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
		sortedData.comparatorProperty().bind( TabRecBack.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 TabRecBack.setItems(sortedData);
       
 
        
    }
    
   // ba9et your way through life 
    
     
            

        
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }

    @FXML
    private void ItemSelected(MouseEvent event) {
    }


    /*****************************************************************************************************************************/
    
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
        idRBack.setCellValueFactory(new PropertyValueFactory<>("idR"));
        useridBack.setCellValueFactory(new PropertyValueFactory<>("userid"));
        dateRBack.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        objetRBack.setCellValueFactory(new PropertyValueFactory<>("objetR"));
        etatRBack.setCellValueFactory(new PropertyValueFactory<>("etatR"));
        detailsRBack.setCellValueFactory(new PropertyValueFactory<>("detailsR"));

        supprimer();
       
 
        TabRecBack.setItems(sp.getAllReclamationsBACK());
    }
     /*****************************************************************************************************************************/
    
    
   /* 
    @FXML
    private void supprimerRBACK(ActionEvent event) {
        
        ReclamationService sp = new ReclamationService();
        sp.SupprimerReclamation(TabRecBack.getSelectionModel().getSelectedItem().getIdR());
        
        TabRecBack.getItems().clear();
        TabRecBack.setItems(sp.getAllReclamationsFRONT());
    }
*/
 
    @FXML
    private void sendPDF(ActionEvent event) {
      ReclamationService sp = new ReclamationService();
        
         try {
            sp.sendPDF();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(ReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
    }

    @FXML
    private void TraiterR(ActionEvent event) {
        
         int id =TabRecBack.getSelectionModel().getSelectedItem().getIdR();
           ReclamationService sp = new ReclamationService();
           Reclamation p = new Reclamation ();
           
           p.setEtatR("Traitee");
            p.setIdR(id);
            
            sp.traiterReclamation(p);
       TabRecBack.setItems(sp.getAllReclamationsBACK());     
       TabRecBack.getItems().clear();
        TabRecBack.setItems(sp.getAllReclamationsBACK());
        
         Notifications notificationBuilder = Notifications.create()
                .title ("Reclamation traitee!")
                .text("Votre Reclamation a ete traitee.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
            
    }

    @FXML
    private void Recherche(KeyEvent event) {
    }
   
    
   

}
