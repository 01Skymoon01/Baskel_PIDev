/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import Entite.Reservation;
import Services.ReservationService;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class Affichagereservations implements Initializable {

    @FXML
    private TableView<Reservation> idTabRes;
    @FXML
    private TableColumn<Reservation, String> Event= new TableColumn<>("Event");
    @FXML
    private TableColumn<Reservation, String> Etat;
    @FXML
    private Button Btnconf;
    @FXML
    private Button BtnAnnul;
    @FXML
    private TableColumn<Reservation, Integer> idRes; 
    @FXML
    private MaterialDesignIconView MesReservations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //css
                Btnconf.setOnMouseEntered((event)
                -> Btnconf.setStyle(" -fx-background-color : green;")
        );

        Btnconf.setOnMouseExited((event)
                -> Btnconf.setStyle("-fx-background-color : black;")
        );

        BtnAnnul.setOnMouseEntered((event)
                -> BtnAnnul.setStyle(" -fx-background-color : red;")
        );

        BtnAnnul.setOnMouseExited((event)
                -> BtnAnnul.setStyle("-fx-background-color : black;")
        );

        
        ReservationService sp = new ReservationService();
        ObservableList<Reservation> l;
        l = sp.getAllRes();
        
        idRes.setCellValueFactory(new PropertyValueFactory<>("id"));
        Event.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId_event().toString()));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        idTabRes.setItems(l);
    }    

    @FXML
    private void ItemSelected(MouseEvent event) {
    }
    
    

    @FXML
    private void Confirmer(ActionEvent event) {
        
         int id =idTabRes.getSelectionModel().getSelectedItem().getId();
         System.out.println(id);
           ReservationService sp = new ReservationService();
           Reservation p = new Reservation();
           
           p.setEtat("Confirmée");  
           System.out.println("etat set");
            p.setId(id);
            System.out.println("id is set");
            
            sp.Confirmer(p);
            System.out.println("success");
            
       idTabRes.setItems(sp.getAllRes());     
       idTabRes.getItems().clear();
          idTabRes.setItems(sp.getAllRes());     
        
       Image img = new Image("/images/baskel.png");
        Notifications notificationBuilder = Notifications.create()
                .title ("Confirmé!")
                .text("Vous avez Confirmé votre reservation !")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.show();
            
    }

    @FXML
    private void Annuler(ActionEvent event) {
        
        int id =idTabRes.getSelectionModel().getSelectedItem().getId();
         System.out.println(id);
           ReservationService sp = new ReservationService();
           Reservation p = new Reservation();
           
           p.setEtat("Annulée");  
           System.out.println("etat set");
            p.setId(id);
            System.out.println("id is set");
            
            sp.Confirmer(p);
            System.out.println("success");
            
       idTabRes.setItems(sp.getAllRes());     
       idTabRes.getItems().clear();
          idTabRes.setItems(sp.getAllRes());     
        
       Image img = new Image("/images/baskel.png");
        Notifications notificationBuilder = Notifications.create()
                .title ("Annulé!")
                .text("Vous avez Annulé votre reservation!")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.showError();
                
    }
    
}
