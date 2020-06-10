/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFrites;

import Entite.RDV;
import Entite.SessionUser;
import Services.FormValidation;
import Services.RDVService;
import adhma.Utils.Mail;
import java.net.URL;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class RDVController implements Initializable {

    @FXML
    private TextArea TFdetailsFront;
    @FXML
    private ComboBox<String> CombObjetRDVFront;
    @FXML
    private Button ajouterRDV;
    @FXML
    private TableColumn<RDV, Integer> idRDV;
    @FXML
    private TableColumn<RDV, Date> dateDepotRDVFront;
    @FXML
    private TableColumn<RDV, Date> DateRDV;
    @FXML
    private TableColumn<RDV, String> objetRDVFront;
    @FXML
    private TableColumn<RDV, String> etatRDVFront;
    @FXML
    private TableColumn<RDV, String> detailsRDVFront;
    @FXML
    private Button modifierRDV;
    @FXML
    private TableView<RDV> TabRDVFront;
    @FXML
    private TableColumn<RDV, String> technicienRDV;
    @FXML
    private DatePicker dateRDVpicker;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblDetails;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<RDV, String> supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*css*/
        modifierRDV.setOnMouseEntered((event)
                -> modifierRDV.setStyle(" -fx-background-color : red;")
        );

        modifierRDV.setOnMouseExited((event)
                -> modifierRDV.setStyle("-fx-background-color : black;")
        );

        ajouterRDV.setOnMouseEntered((event)
                -> ajouterRDV.setStyle(" -fx-background-color : red;")
        );

        ajouterRDV.setOnMouseExited((event)
                -> ajouterRDV.setStyle("-fx-background-color : black;")
        );

        CombObjetRDVFront.getItems().addAll("Reparation", "Maintenance technique", "Rendez-vous technicien", "Probleme de facturation", "Autres..");
        RDV cr = new RDV();
        RDVService sp = new RDVService();
        ObservableList<RDV> l;
        l = sp.getAllRDVFRONT();
        idRDV.setCellValueFactory(new PropertyValueFactory<>("idRDV"));
        dateDepotRDVFront.setCellValueFactory(new PropertyValueFactory<>("dateDeptRDV"));
        DateRDV.setCellValueFactory(new PropertyValueFactory<>("dateRDV"));
        objetRDVFront.setCellValueFactory(new PropertyValueFactory<>("objetRDV"));
        etatRDVFront.setCellValueFactory(new PropertyValueFactory<>("etatRDV"));
        detailsRDVFront.setCellValueFactory(new PropertyValueFactory<>("detailsRDV"));

        // technicienRDV.setCellValueFactory(new PropertyValueFactory<>("aa"));
        // technicienRDV.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().toString()));
        technicienRDV.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().toString()));
        supprimer();
        TabRDVFront.setItems(l);

        FilteredList<RDV> filteredData = new FilteredList<>(l, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(RDV -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(RDV.getIdRDV()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(RDV.getDateRDV()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(RDV.getDateDeptRDV()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (RDV.getEtatRDV().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (RDV.getObjetRDV().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (RDV.getDetailsRDV().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(RDV.getTechnicienid()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(RDV.getUserid()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RDV> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TabRDVFront.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        TabRDVFront.setItems(sortedData);

    }

    @FXML
    private void ItemSelected(MouseEvent event) throws ParseException {

        RDV cr = new RDV();

        cr = TabRDVFront.getSelectionModel().getSelectedItem();

        CombObjetRDVFront.getSelectionModel().select(cr.getObjetRDV());

        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("MM-dd-yyyy");

        Date date = originalFormat.parse(cr.getDateRDV().toString());
        String formattedDate = targetFormat.format(date);
//datenaiss.setValue(LOCAL_DATE(formattedDate));

        LocalDate localDate = null;
        DateTimeFormatter formatter = null;
        formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        localDate = LocalDate.parse(formattedDate, formatter);
        dateRDVpicker.setValue(localDate);

        TFdetailsFront.setText(cr.getDetailsRDV());

    }

    @FXML
    private void ajouterRDV(ActionEvent event) {

        boolean testDate = FormValidation.dateValidRDV(dateRDVpicker, lblDate, "date invalide");
        if (testDate) {
            String objet = CombObjetRDVFront.getValue();
            String detail = TFdetailsFront.getText();
            Date d = new Date(); // date depot ta3 lyoum
            Date dd = java.sql.Date.valueOf(dateRDVpicker.getValue()); // date ta3 el rdv

            String nt = "non traitee";

            //  public RDV(  Date dateRDV, String objetRDV, String etatRDV, String detailsRDV, int userid, Date dateDeptRDV)          
            RDV p = new RDV(dd, objet, nt, detail, SessionUser.getInstance().getId(), d);

            RDVService sp = new RDVService();
            sp.ajouterRDV(p);
            TabRDVFront.setItems(sp.getAllRDVFRONT());
            TabRDVFront.getItems().clear();
            TabRDVFront.setItems(sp.getAllRDVFRONT());

            try {
                Mail.sendMail("nour.khedher@esprit.tn");

                Notifications notificationBuilder = Notifications.create()
                        .title("Rendez-vous Ajoute!")
                        .text("Un rendez-vous a ete ajoute. Verifier votre email")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);
                //  notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();

            } catch (Exception ex) {
                Logger.getLogger(RDVController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void modifierRDV(ActionEvent event) {

        boolean testDate = FormValidation.dateValidRDV(dateRDVpicker, lblDate, "date invalide");
        if (testDate) {

            String objet = CombObjetRDVFront.getValue();
            String detail = TFdetailsFront.getText();
            //Date d = new Date(); // date depot ta3 lyoum
            Date dd = java.sql.Date.valueOf(dateRDVpicker.getValue()); // date ta3 el rdv

            int id = TabRDVFront.getSelectionModel().getSelectedItem().getIdRDV();

            RDVService sp = new RDVService();
            RDV p = new RDV();

            p.setObjetRDV(objet);
            p.setDetailsRDV(detail);
            p.setDateRDV(dd);
            p.setIdRDV(id);

            sp.modifierRDV(p);

            TabRDVFront.setItems(sp.getAllRDVFRONT());
            TabRDVFront.getItems().clear();
            TabRDVFront.setItems(sp.getAllRDVFRONT());

            Notifications notificationBuilder = Notifications.create()
                    .title("Rendez-vous Modifiee!")
                    .text("Un rendez-vous a ete modifie.")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            //  notificationBuilder.darkStyle();
            notificationBuilder.showConfirm();

        }
    }

    void supprimer() {

        //******************************Button Supprimer ***************************/
        Callback<TableColumn<RDV, String>, TableCell<RDV, String>> cellFactory2 = (param) -> {
            final TableCell<RDV, String> cell = new TableCell<RDV, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    RDVService sp = new RDVService();

                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        final Button editButton = new Button("X");
                        editButton.setStyle("-fx-background-color : none");

                        /**
                         * *css*
                         */
                        editButton.setOnMouseEntered((event)
                                -> editButton.setStyle("-fx-text-fill : #800080; -fx-background-color : none;")
                        );

                        editButton.setOnMouseExited((event)
                                -> editButton.setStyle("-fx-text-fill : black;-fx-background-color : none;")
                        );
                        //end css ****/

                        editButton.setOnAction(event -> {

                            RDV c = getTableView().getItems().get(getIndex());

                            System.out.print(c);

                            sp.SupprimerRDV(c.getIdRDV());
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

    void Afficher() {
        RDV cr = new RDV();
        RDVService sp = new RDVService();
        ObservableList<RDV> l;
        l = sp.getAllRDVFRONT();
        idRDV.setCellValueFactory(new PropertyValueFactory<>("idRDV"));
        dateDepotRDVFront.setCellValueFactory(new PropertyValueFactory<>("dateDeptRDV"));
        DateRDV.setCellValueFactory(new PropertyValueFactory<>("dateRDV"));
        objetRDVFront.setCellValueFactory(new PropertyValueFactory<>("objetRDV"));
        etatRDVFront.setCellValueFactory(new PropertyValueFactory<>("etatRDV"));
        detailsRDVFront.setCellValueFactory(new PropertyValueFactory<>("detailsRDV"));

        // technicienRDV.setCellValueFactory(new PropertyValueFactory<>("aa"));
        // technicienRDV.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().toString()));
        technicienRDV.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTechnicienid().toString()));

        supprimer();

        TabRDVFront.setItems(sp.getAllRDVBACK());
    }

    /**
     * **************************************************************************************************************************
     */

    /*   
    JUSTE ZID BOUTON ESEMHA supprimerRDV IN CASE OF DANGER OU BECH NRAJ3OU BOUTON

    @FXML
    private void supprimerRDV(ActionEvent event) {
        
          RDVService sp = new RDVService();
        sp.SupprimerRDV(TabRDVFront.getSelectionModel().getSelectedItem().getIdRDV());
        TabRDVFront.setItems(sp.getAllRDVFRONT());
        TabRDVFront.getItems().clear();
        TabRDVFront.setItems(sp.getAllRDVFRONT());
        
         Notifications notificationBuilder = Notifications.create()
                .title ("Rendez-vous Supprimee!")
                .text("Un rendez-vous a ete supprime.")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
            //  notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
      
        
        
    }*/
    @FXML
    private void Recherche(KeyEvent event) {
    }

}
