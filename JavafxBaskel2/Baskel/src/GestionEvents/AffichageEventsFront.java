/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import Entite.Event;
import Entite.Reservation;
import Entite.SessionUser;
import GuiFront.MainMenuFrontController;
import Services.EventService;
import Services.ReservationService;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class AffichageEventsFront implements Initializable {

    @FXML
    private Button MyResBtn;
      @FXML
    private TableView<Event> idTabEVB;
    @FXML
    private TableColumn<Event, Integer> id;
    @FXML
    private TableColumn<Event,String> idNomEv;
    @FXML
    private TableColumn<Event,Date> idDateEv;
    @FXML
    private TableColumn<Event,String> idDescEv;
    @FXML
    private TableColumn<Event, Integer> idNBPEV;
    @FXML
    private TableColumn<Event,String> idEmailRespEV;
    @FXML
    private TableColumn<Event,String> idWAEV;
    @FXML
    private Button BtnRes;
    @FXML
    private MaterialDesignIconView MesReservations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    //css
            MesReservations.setOnMouseEntered((event)
                -> MesReservations.setStyle(" -fx-fill : red;")
        );

        MesReservations.setOnMouseExited((event)
                -> MesReservations.setStyle("-fx-fill : black;")
        );

        BtnRes.setOnMouseEntered((event)
                -> BtnRes.setStyle(" -fx-background-color : red;")
        );

        BtnRes.setOnMouseExited((event)
                -> BtnRes.setStyle("-fx-background-color : black;")
        );

                
         
        
        
        
        
        
        EventService sp = new EventService();
        ObservableList<Event> l ;
        l=sp.getAllEvent();
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
         idDateEv.setCellValueFactory(new PropertyValueFactory<>("date"));
         idNomEv.setCellValueFactory(new PropertyValueFactory<>("nom"));
         idDescEv.setCellValueFactory(new PropertyValueFactory<>("description"));
         idEmailRespEV.setCellValueFactory(new PropertyValueFactory<>("emailresponsable"));
         idWAEV.setCellValueFactory(new PropertyValueFactory<>("whyattend"));
         idNBPEV.setCellValueFactory(new PropertyValueFactory<>("nbParticipants"));
  
         idTabEVB.setItems(l);
         
         
         
    }

    @FXML
    private void MesReservations(ActionEvent event) throws IOException {
        
        System.out.println(".. Afficher MesReservations de client = "+SessionUser.getInstance().getId());
            
            Stage parentStage = (Stage) idTabEVB.getScene().getWindow();
         

             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
             Parent root = loader.load();

             MainMenuFrontController apc = loader.getController();
             apc.AfficherMesReservations();
            
              Scene scene=new Scene(root);
              parentStage.setScene(scene);
           
              parentStage.show();
        System.out.println(".. End Afficher MesReservations de client = "+SessionUser.getInstance().getId());
   
      
           
    }

    @FXML
    private void ItemSelected(MouseEvent event) {
    }

    @FXML
    private void Reserver(ActionEvent event) {
        EventService sp = new EventService();
      
        
        
        Event E= new Event();
        E=idTabEVB.getSelectionModel().getSelectedItem();
        int idEvent = E.getId();
        System.out.println(idEvent);
                
        ReservationService Rs = new ReservationService();
        
        try {
             Reservation R = new Reservation();
          Rs.Reserver(R, idEvent);
            System.out.println("success");
            Image img = new Image("/images/baskel.png");
        Notifications notificationBuilder = Notifications.create()
                .title ("Congratulations")
                .text("Vous avez reservé une place dans notre prochain evenement !")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
             notificationBuilder.darkStyle();
        notificationBuilder.show();
        } catch (Exception e) {
        }
       
        
         
         idTabEVB.getItems().clear();
        idTabEVB.setItems(sp.getAllEvent());
    }
    
}
