/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionLivraison;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import Entite.Livreur;
import Entite.Vehicule;
import Services.FormValidation;
import Services.LivreurService;
import Services.VehiculeService;
import static com.xemacscode.Launcher.main;
//import static gui.Adhma.main;
//import static home.Main.main;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import static sample.Main.main;

/**
 * FXML Controller class
 *
 * @author Achraf Zaafrane
 */
public class LivreurController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker datenaiss;
    @FXML
    private TableView<Livreur> tabv;
    @FXML
    private TableColumn<Livreur, Integer> idl;
    @FXML
    private TableColumn<Livreur, String> noml;
    @FXML
    private TableColumn<Livreur, String> prenoml;
    @FXML
    private TableColumn<Livreur, Date> datenaissl;
    @FXML
    private TableColumn<Livreur, Integer> soldel;
    @FXML
    private TableColumn<Livreur, String> etatl;
    @FXML
    private Button btnmodifer;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Label lblnom;
     @FXML
    private Label lblprenom;
   @FXML
    private Label lbldateNaiss;
    @FXML
    private Label lblnbrliv;
    @FXML
    private TextField recherche;
    /**
     * Initializes the controller class.
     */
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        
   
        
        LivreurService sp = new LivreurService();
        ObservableList<Livreur> l ;
        l=sp.getAllLivreur();
                String nbrlivreurs;
        try {
            nbrlivreurs = sp.getNumberLivreur();
                    lblnbrliv.setText(nbrlivreurs);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idl.setCellValueFactory(new PropertyValueFactory<>("id"));
        noml.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenoml.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        datenaissl.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));
        soldel.setCellValueFactory(new PropertyValueFactory<>("solde"));
        etatl.setCellValueFactory(new PropertyValueFactory<>("etat"));
    
        tabv.setItems(l);
        
        
        
       FilteredList<Livreur> filteredData = new FilteredList<>(l, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(v -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (v.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (v.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                                                                         else if (v.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                                                                       
                           
			
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Livreur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind( tabv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		 tabv.setItems(sortedData);
   }    

    @FXML
    private void ajouterVehicule(ActionEvent event) throws ParseException {
        
        boolean nomvide=FormValidation.textFieldNotEmpty(nom, lblnom, "le nom  est vide");
         boolean prenomvide=FormValidation.textFieldNotEmpty(prenom, lblprenom,  "le prenom est vide");
         boolean nomalpha=FormValidation.textFieldTypeAlphabet(nom, lblnom,  "le nom doit contenir que des alphabets");
                  boolean prenomalpha=FormValidation.textFieldTypeAlphabet(prenom, lblprenom,  "le prenom doit contenir que des alphabets");
                                    boolean datevalid=FormValidation.dateValid(datenaiss, lbldateNaiss,  "la date naissance doit etre inferieur a aujourd'hui");
if(nomvide && prenomvide && nomalpha && prenomalpha && datevalid ){
    
    try {
			// Construct data
			String apiKey = "apikey=" + "N9E04Jca7J4-3OC7O4dtrOuyhaqqPjoNYUaDyKjmf9	";
			String message = "&message=" + "Felicitation  " +nom.getText()+" "+prenom.getText()+" vous etes inscrit";
			String sender = "&sender=" + nom.getText()+" "+prenom.getText();
			String numbers = "&numbers=" + "92300008";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                System.out.println(line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			//return "Error "+e;
		}
    String txtnom = nom.getText();
         String txtprenom = prenom.getText();
      Date dd = java.sql.Date.valueOf(datenaiss.getValue());
                
      
            Livreur v = new Livreur( txtnom, txtprenom, dd, 0,"en attente");
            
             LivreurService sp = new LivreurService();
             sp.ajouterLivreur(v);
                         String nbrlivreurs;
        try {
            nbrlivreurs = sp.getNumberLivreur();
                    lblnbrliv.setText(nbrlivreurs);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabv.setItems(sp.getAllLivreur());
}
           
    }

    @FXML
    private void ItemSelected(MouseEvent event) throws ParseException {
      Livreur cr = new Livreur();
     
         cr = tabv.getSelectionModel().getSelectedItem();



            nom.setText(cr.getNom());
                 prenom.setText(cr.getPrenom());
                 
DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
DateFormat targetFormat = new SimpleDateFormat("MM-dd-yyyy");

Date date = originalFormat.parse(cr.getDateNaiss().toString());
String formattedDate = targetFormat.format(date);  
//datenaiss.setValue(LOCAL_DATE(formattedDate));

                  LocalDate localDate = null;
        DateTimeFormatter formatter = null;
        formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        localDate = LocalDate.parse(formattedDate, formatter);
      datenaiss.setValue(localDate);
 

    }

    
    @FXML
    private void modifierVehicule(ActionEvent event) {
        

                  String nom1 = nom.getText();
          String prenom1 = prenom.getText();
                      Date dd1 = java.sql.Date.valueOf(datenaiss.getValue());

             int id =tabv.getSelectionModel().getSelectedItem().getId();

          LivreurService sp = new LivreurService();
           Livreur p = new Livreur ();
               p.setNom(nom1);
               p.setPrenom(prenom1);
               p.setDateNaiss(dd1);
              p.setId(id);
             sp.modifierLivreur(p);
             
        tabv.setItems(sp.getAllLivreur());
        tabv.getItems().clear();
        tabv.setItems(sp.getAllLivreur());
        
    }

    @FXML
    private void SupprimerVehicule(ActionEvent event) {
           LivreurService sp = new LivreurService();
        sp.SupprimerLivreur(tabv.getSelectionModel().getSelectedItem().getId());
        
        tabv.getItems().clear();
        tabv.setItems(sp.getAllLivreur());
                    String nbrlivreurs;
        try {
            nbrlivreurs = sp.getNumberLivreur();
                    lblnbrliv.setText(nbrlivreurs);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void pressButton(ActionEvent event){               
    try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Vehiculee.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
           // main.stg.close();
    } catch(Exception e) {
       e.printStackTrace();
      }
 }
    
    public void pressButtonlivraison(ActionEvent event){               
    try {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Livraison.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
           // main.stg.close();
    } catch(Exception e) {
       e.printStackTrace();
      }
 }
}
