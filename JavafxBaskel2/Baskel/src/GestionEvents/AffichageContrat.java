/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import Entite.Contrat;
import Entite.Event;
import Entite.Partenaire;
import Services.ContratService;
import Services.EventService;
import Services.PartenaireService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class AffichageContrat implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField filterField;
    @FXML
    private TextField idDescriptionIN;
    @FXML
    private Label LBLNOM;
    @FXML
    private Label LBLDATE;
    @FXML
    private Label LBLEMAIL;
    @FXML
    private Label LBLDESC;
    @FXML
    private Label LBLNBP;
    @FXML
    private Label LBLWAE;
    @FXML
    private TableView<Contrat> idTabEVB;
    @FXML
    private TableColumn<Contrat, Integer> id;
    @FXML
    private Button idSign;
    @FXML
    private Button idSupp;
    @FXML
    private TableColumn<Contrat, String> idEv = new TableColumn<>("Event");
    @FXML
    private TableColumn<Contrat, String> idPart = new TableColumn<>("Partenaire");
    @FXML
    private TableColumn<Contrat, String> idPack;
    @FXML
    private TableColumn<Contrat, String> idDesc;
    @FXML
    private ComboBox<Event> ComboEvent;
    @FXML
    private ComboBox<Partenaire> comboParten;
    private ComboBox<String> comboPack;
    @FXML
    private TextField idPackIN;

    /**
     * Initializes the controller class.
     */
    /*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       comboPack .getItems().addAll("Silver", "Gold", "Premium");
       EventService ee = new EventService() ; 
       ObservableList<Event> Evv ;
       Evv=ee.getAllEvent();
     
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
         idEv.setCellValueFactory(new PropertyValueFactory<>("id_event"));
         idPart.setCellValueFactory(new PropertyValueFactory<>("id_partenaire"));
         idPack.setCellValueFactory(new PropertyValueFactory<>("pack"));
         idDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
         
  
        
        
        ComboEvent.setItems(Evv); 
        
        ComboEvent.setConverter(new StringConverter<Event>(){
        @Override
        public String toString(Event object) {
     return object.getNom();
        }

        @Override
        public Event fromString(String string) {
        return null;        }
        
        ContratService sp = new ContratService();
        ObservableList<Contrat> l ;
        l=sp.getAllContrat();
         idTabEVB.setItems(l);
        
       
    
}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContratService sp = new ContratService();
        ObservableList<Contrat> l;
        l = sp.getAllContrat();

        /* try {
            nbrvhicule = sp.getNumberVehicule();
                    lblnbrvehicule.setText(nbrvhicule);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idEv.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId_event().toString()));
        // idEv.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        //idPart.setCellValueFactory(new PropertyValueFactory<>("id_partenaire"));
        idPart.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId_partenaire().toString()));
        idPack.setCellValueFactory(new PropertyValueFactory<>("pack"));
        idDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        idTabEVB.setItems(l);

        /*
         FOR COMBOBOX BEL CLE 
         */
        
        PartenaireService ls = new PartenaireService();
        ObservableList<Partenaire> li;
        li = ls.getAllPartenaire();
        comboParten.setItems(li);

        comboParten.setConverter(new StringConverter<Partenaire>() {
            @Override
            public String toString(Partenaire object) {
                return object.getId()+" "+object.getNom();
            }

            @Override
            public Partenaire fromString(String string) {
                return null;
            }

        }
        );
        
        
        

        EventService lsS = new EventService();
        ObservableList<Event> EE;
        EE = lsS.getAllEvent();
        ComboEvent.setItems(EE);

        ComboEvent.setConverter(new StringConverter<Event>() {
            @Override
            public String toString(Event object) {
                return object.getId()+" "+object.getNom();
            }

            @Override
            public Event fromString(String string) {
                return null;
            }

        }
        );

    }
    
    
    
    
    

    @FXML
    private void ItemSelected(MouseEvent event) {

        Contrat e = new Contrat();
        e = idTabEVB.getSelectionModel().getSelectedItem();

        idDesc.setText(e.getDescription());

    }

    @FXML
    private void Signer(ActionEvent event) {

        String desc = idDescriptionIN.getText();
        int pack = Integer.parseInt(idPackIN.getText());

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(ComboEvent.getSelectionModel().getSelectedItem().toString2());
        String ch = "";
        while (m.find()) {
            ch = m.group();

        }
        Pattern o = Pattern.compile("\\d+");
        Matcher k = o.matcher(comboParten.getSelectionModel().getSelectedItem().toString2());
        String chh = "";
        while (k.find()) {
            chh = k.group();

        }
        
              int foo = Integer.parseInt(ch);
              int foo2 = Integer.parseInt(chh);
             
        
            

        Contrat c = new Contrat(pack, desc, new Event(), new Partenaire());

        ContratService sp = new ContratService();
        System.out.println(foo);
        System.out.println(foo2);
        sp.Signercontrat(c, foo, foo2);

       

        idTabEVB.getItems().clear();
        idTabEVB.setItems(sp.getAllContrat());
        
        

    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

}
