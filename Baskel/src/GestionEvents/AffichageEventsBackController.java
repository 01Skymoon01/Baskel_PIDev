/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import Entite.Event;

import Services.EventService;
import Services.FormValidationEvent;
import Services.MailEvent;
import static Services.MailEvent.sendMail;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
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
import javax.mail.MessagingException;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.control.textfield.AutoCompletionBinding;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class AffichageEventsBackController implements Initializable {

    @FXML
    private TableView<Event> idTabEVB;
    @FXML
    private TableColumn<Event, Integer> id;
    @FXML
    private TableColumn<Event, String> idNomEv;
    @FXML
    private TableColumn<Event, Date> idDateEv;
    @FXML
    private TableColumn<Event, String> idDescEv;
    @FXML
    private TableColumn<Event, Integer> idNBPEV;
    @FXML
    private TableColumn<Event, String> idEmailRespEV;
    @FXML
    private TableColumn<Event, String> idWAEV;
    @FXML
    private Button idSuppEVB;
    @FXML
    private TextField idNomIN;
    @FXML
    private TextField idDescriptionIN;
    @FXML
    private TextField idNBPIN;
    @FXML
    private TextField idWAIN;
    @FXML
    private DatePicker idDateIN;
    @FXML
    private TextField idEmRespIN;
    @FXML
    private Button idModif;
    @FXML
    private Button idAjoutE;
    @FXML
    private Label LBLNOM;
    @FXML
    private Label LBLDATE;
    @FXML
    private Label LBLDESC;
    @FXML
    private Label LBLNBP;
    @FXML
    private Label LBLEMAIL;
    @FXML
    private Label LBLWAE;
    @FXML
    private TextField filterField;
    @FXML
    private Button StatBtn;
    @FXML
    private ImageView baskelLogo;

    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] _possibleSuggestions = {"Charity Event", "Bikes Event", "Marathon"};

    private Set<String> possibleSuggestions = new HashSet<>(Arrays.asList(_possibleSuggestions));

    private void autoCompletionLearnWord(String newWord) {
        possibleSuggestions.add(newWord);

        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(filterField, possibleSuggestions);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Build PopOver look and feel
        Label lblName = new Label("Baskel is an Ecofriendly Platform founded by ");
        Label lblSubject = new Label(" group of students in 2020 and it serves for giving an ");
        Label lblCityStateZip = new Label("alternative that saves the environment and limits gas exhaustion ");
        lblCityStateZip.setStyle("-fx-text-fill   :#ffffff;" + "-fx-font-weight: bold;" + "-fx-font-size:14;");
        lblSubject.setStyle("-fx-text-fill   :#ffffff;" + "-fx-font-weight: bold;" + "-fx-font-size:14;");
        lblName.setStyle("-fx-text-fill   :#ffffff;" + "-fx-font-weight: bold;" + "-fx-font-size:14;");

        VBox vBox = new VBox(lblName, lblSubject, lblCityStateZip);

        vBox.setPrefHeight(100.0);
        vBox.setPrefWidth(400.0);

        vBox.setStyle("-fx-background-color    :#48204f;" + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0.5, 0.0, 0.0);");
        //Create PopOver and add look and feel
        PopOver popOver = new PopOver(vBox);

        baskelLogo.setOnMouseEntered(mouseEvent -> {

            popOver.show(baskelLogo);
        });

        baskelLogo.setOnMouseExited(mouseEvent -> {

            popOver.hide();
        });

        TextFields.bindAutoCompletion(filterField, "Charity Event", "Bikes Event", "Marathon");

        autoCompletionBinding = TextFields.bindAutoCompletion(filterField, possibleSuggestions);
        filterField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case ENTER:
                        autoCompletionLearnWord(filterField.getText().trim());
                        break;
                    default:
                        break;
                }
            }
        });

        EventService sp = new EventService();
        ObservableList<Event> l;
        l = sp.getAllEvent();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idDateEv.setCellValueFactory(new PropertyValueFactory<>("date"));
        idNomEv.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idDescEv.setCellValueFactory(new PropertyValueFactory<>("description"));
        idEmailRespEV.setCellValueFactory(new PropertyValueFactory<>("emailresponsable"));
        idWAEV.setCellValueFactory(new PropertyValueFactory<>("whyattend"));
        idNBPEV.setCellValueFactory(new PropertyValueFactory<>("nbParticipants"));

        idTabEVB.setItems(l);

        FilteredList<Event> filteredData = new FilteredList<>(l, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (event.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (event.getWhyattend().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (event.getEmailresponsable().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(event.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(event.getNbParticipants()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTabEVB.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        idTabEVB.setItems(sortedData);
    }

    @FXML
    private void AjoutE(ActionEvent event) throws Exception {

        boolean testNom = FormValidationEvent.textFieldTypeAlphabet(idNomIN, LBLNOM, "Ce message ne doit contenir que des lettres");
        boolean testDate = FormValidationEvent.dateValidRDV(idDateIN, LBLDATE, " cette date est inferieure à la date d'aujourd'hui");
        boolean testDesc = FormValidationEvent.textFieldTypeAlphabet(idDescriptionIN, LBLDESC, "Ce message ne doit contenir que des lettres");
        boolean testNB = FormValidationEvent.textFieldTypeNumber(idNBPIN, LBLNBP, "Veuilez saisir que des chiffres");
        boolean testEmail = FormValidationEvent.textFieldTypeEmail(idEmRespIN, LBLEMAIL, " Cet Email n'est pas valide");

        if (testNom && testDate && testEmail && testDesc && testNB) {
            Date date_debut = java.sql.Date.valueOf(idDateIN.getValue());
            String NBP = idNBPIN.getText();
            int num = Integer.parseInt(NBP);
            String nom = idNomIN.getText();
            String desc = idDescriptionIN.getText();
            String EmailResp = idEmRespIN.getText();
            String WA = idWAIN.getText();
            Event p = new Event(date_debut, nom, desc, num, EmailResp, WA);
            EventService sp = new EventService();
            sp.ajouterEvent(p);
            idTabEVB.setItems(sp.getAllEvent());
            idTabEVB.getItems().clear();
            idTabEVB.setItems(sp.getAllEvent());

            try {
                Services.MailEvent.sendMail(EmailResp);
            } catch (Exception ex) {
                Logger.getLogger(AffichageEventsBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image img = new Image("/images/baskel.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Un event a ete ajoute !!")
                    .text("Un nouveau  evenement a été ajouté dans votre Baskel liste")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }

    }

    @FXML
    private void SupprimerEventB(ActionEvent event) {

        EventService sp = new EventService();
        sp.supprimerEvent(idTabEVB.getSelectionModel().getSelectedItem().getId());
        idTabEVB.getItems().clear();
        idTabEVB.setItems(sp.getAllEvent());
    }

    @FXML
    private void ItemSelected(MouseEvent event) throws ParseException {

        Event e = new Event();
        e = idTabEVB.getSelectionModel().getSelectedItem();

        idDateIN.setValue(LocalDate.now());
        idNBPIN.setText(Integer.toString(e.getNbParticipants()));

        idNomIN.setText(e.getNom());
        idDescriptionIN.setText(e.getDescription());
        idEmRespIN.setText(e.getEmailresponsable());
        idWAIN.setText(e.getWhyattend());

        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = originalFormat.parse(e.getDate().toString());
        String formattedDate = targetFormat.format(date);

//datenaiss.setValue(LOCAL_DATE(formattedDate));
        LocalDate localDate = null;
        DateTimeFormatter formatter = null;
        formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        localDate = LocalDate.parse(formattedDate, formatter);
        idDateIN.setValue(localDate);

    }

    @FXML
    private void ModifierE(ActionEvent event) {

        boolean testNom = FormValidationEvent.textFieldTypeAlphabet(idNomIN, LBLNOM, "Ce message ne doit contenir que des lettres");
        boolean testDate = FormValidationEvent.dateValidRDV(idDateIN, LBLDATE, " cette date est inferieure à la date d'aujourd'hui");
        boolean testDesc = FormValidationEvent.textFieldTypeAlphabet(idDescriptionIN, LBLDESC, "Ce message ne doit contenir que des lettres");
        boolean testNB = FormValidationEvent.textFieldTypeNumber(idNBPIN, LBLNBP, "Veuilez saisir que des chiffres");
        boolean testEmail = FormValidationEvent.textFieldTypeEmail(idEmRespIN, LBLEMAIL, " Cet Email n'est pas valide");

        Date date_debut = java.sql.Date.valueOf(idDateIN.getValue());
        String NBP = idNBPIN.getText();
        int num = Integer.parseInt(NBP);
        String nom = idNomIN.getText();
        String desc = idDescriptionIN.getText();
        String EmailResp = idEmRespIN.getText();
        String WA = idWAIN.getText();

        if (testNom && testDate && testEmail && testDesc && testNB) {
            int id = idTabEVB.getSelectionModel().getSelectedItem().getId();
            EventService sp = new EventService();
            Event p = new Event();
            p.setId(id);
            p.setNom(nom);
            p.setDate(date_debut);
            p.setEmailresponsable(EmailResp);
            p.setNbParticipants(num);
            p.setWhyattend(WA);
            p.setDescription(desc);
            sp.modifierE(p);
            idTabEVB.setItems(sp.getAllEvent());
            idTabEVB.getItems().clear();
            idTabEVB.setItems(sp.getAllEvent());

        }

    }

    private void PDF(ActionEvent event) {

        EventService sp = new EventService();

        try {
            sp.sendPDF();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(AffichageEventsBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void navPart(ActionEvent event) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AffichagePartenaires.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            // main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void StatisticsOpen(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChartsEvents.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            // main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void EventMailContact(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventEmailContact.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            // main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
