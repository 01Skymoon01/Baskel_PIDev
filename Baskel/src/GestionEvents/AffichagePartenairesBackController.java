/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import Entite.Partenaire;
import Services.PartenaireService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;










/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class AffichagePartenairesBackController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField idNomIN;
    @FXML
    private TextField idDescriptionIN;
    @FXML
    private TextField idRepIN;
    @FXML
    private ComboBox<String> idTypeIN;
    @FXML
    private TableColumn<Partenaire, Integer> id;
    @FXML
    private TableColumn<Partenaire, String> idNomP;
    @FXML
    private TableColumn<Partenaire, String> idDescP;
    @FXML
    private TableColumn<Partenaire, String> idTypeP;
    @FXML
    private TableColumn<Partenaire, String> idRepP;
    @FXML
    private Button idModif;
    @FXML
    private Button idSuppEVB;
    @FXML
    private TableView<Partenaire> idTabP;
    @FXML
    private Button AjoutP;
    @FXML
    private Button idSignCon;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         idTypeIN.getItems().addAll("Silver", "Gold", "Premium");
       PartenaireService sp = new PartenaireService();
        ObservableList<Partenaire> l ;
        l=sp.getAllPartenaire();
        
        
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
         idNomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
         idDescP.setCellValueFactory(new PropertyValueFactory<>("description"));
         idTypeP.setCellValueFactory(new PropertyValueFactory<>("type"));
         idRepP.setCellValueFactory(new PropertyValueFactory<>("representant"));
        
  
         idTabP.setItems(l);
    }    
    
    
    @FXML
    public void pressButton(ActionEvent event){               
    try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AffichageContrat.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
           // main.stg.close();
    } catch(Exception e) {
       e.printStackTrace();
      }
 }


    
    @FXML
    private void AjoutP(ActionEvent event) {
       
       
       
        String nom = idNomIN.getText();
        String desc = idDescriptionIN.getText();
        String type = idTypeIN.getValue();
        String rep= idRepIN.getText();
       
            Partenaire p = new Partenaire (nom,desc,type,rep);
            PartenaireService sp = new PartenaireService();
             sp.ajouterPartenaire(p);
        idTabP.getItems().clear();
        idTabP.setItems(sp.getAllPartenaire());
        
        Image img = new Image("/images/baskel.png");
        Notifications notificationBuilder = Notifications.create()
                .title ("Une partenariat s'est établie!")
                .text("Un nouveau nouveau partenaire a été ajouté dans votre Baskel liste")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void ItemSelected(MouseEvent event) {
        Partenaire e = new Partenaire();
         e= idTabP.getSelectionModel().getSelectedItem();
  
         idNomIN.setText(e.getNom());
         idDescriptionIN.setText(e.getDescription());
         idRepIN.setText(e.getRepresentant());
         idTypeIN .getSelectionModel().select(e.getType());
         
        
    }

    @FXML
    private void ModifierP(ActionEvent event) {
        
       
        String nom = idNomIN.getText();
        String desc = idDescriptionIN.getText();
        String type = idTypeIN.getValue();
        String rep= idRepIN.getText();
        
        
        
         
         int id =  idTabP.getSelectionModel().getSelectedItem().getId();
          PartenaireService sp = new PartenaireService();
           Partenaire p = new Partenaire();
           p.setId(id);
           p.setNom(nom);
           p.setDescription(desc);
           p.setRepresentant(rep);
           p.setType(type);
         
             sp.modifierE(p);
       idTabP.getItems().clear();
        idTabP.setItems(sp.getAllPartenaire());
    }
    
    
    
    

    @FXML
    private void supprimerP(ActionEvent event) {
        PartenaireService sp = new PartenaireService();
        sp.supprimerPartenaire(idTabP.getSelectionModel().getSelectedItem().getId());
         idTabP.getItems().clear();
        idTabP.setItems(sp.getAllPartenaire());
    }

    
}
