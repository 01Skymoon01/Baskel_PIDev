/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionShop;

import Entite.Produits;
import GuiFront.MainMenuFrontController;
import ServiceProduits.ServiceProduits;
import Services.CommandeService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ShopController implements Initializable {

    int id_client =5;
    private SplitPane lista;
    @FXML
    private TilePane tilePane;

    int count = 0;

    private int nRows = 1;  //no of row for tile pane
    private int nCols = 10;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;

    File filesJpg[]; // file array to store read images info

    ServiceProduits sp = new ServiceProduits();
    CommandeService spN = new CommandeService();

    @FXML
    private Label nbrProduitsPanier;
    @FXML
    private AnchorPane myAnchor;
    @FXML
    private Button GoToCart;
    @FXML
    private FontAwesomeIconView IconPanier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);

        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        try {
            Display();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        try {
            nbrProduitsPanier.setText("("+Integer.toString(spN.NbrProduitAuPanier(id_client))+")");
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Display() throws SQLException {
        List<Integer> liste = new ArrayList();
        liste = sp.ShowProductAllRef();
        createElements(liste);
    }

    private void createElements(List<Integer> liste) throws SQLException {
        tilePane.getChildren().clear();

        for (int l = 0; l < liste.size(); l++) {
            System.out.println(liste.get(l));
            count = liste.get(l);
            for (int j = 0; j < nRows; j++) {

                tilePane.getChildren().add(createPage(count));
                //  count++;

            }
        }

    }

    public HBox createPage(int index) throws SQLException {

        Pane newPane = new Pane();

        // Pane newPane2 = new Pane();
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/GestionShop/ItemProduit.fxml"));

        try {
            Parent root = loader.load();
            ItemProduitController apc = loader.getController();

            String imagename = sp.ShowImage(index);
            Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
            apc.setImage(image);

            String name = sp.showProdNom(index);
            apc.setNom(name);

            Double prix = sp.ShowPrix(index);
            apc.setPrix(prix);

            apc.setIdItem(index);
            newPane = apc.returnPane();

        } catch (IOException ex) {
            // Logger.getLogger(AddPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HBox pageBox = new HBox();
        pageBox.setPadding(new Insets(15, 12, 15, 12));
        pageBox.getChildren().add(newPane);

        //pageBox.getChildren().add(print);          
        return pageBox;
    }

    void filter(String couleur) throws SQLException {

        Produits p = new Produits();
        List<Integer> liste = new ArrayList();
        int id;
        List<Produits> arr = new ArrayList();
        arr = sp.ShowProduct();
        for (int i = 0; i < arr.size(); i++) {
            id = arr.get(i).getRef_p();
            String clr = sp.ShowColors(id);

            String clrNew2 = clr.substring(1, clr.length() - 1);
            String[] colorsArray;
            if (clrNew2.contains(" ")) {
                colorsArray = clrNew2.split(", ");
            } else {
                colorsArray = clrNew2.split(",");
            }

            for (String colorsArray1 : colorsArray) {

                if (colorsArray1.equals(couleur)) {

                    p = sp.ShowProductFiltreFront(id);

                    liste.add(p.getRef_p());
                }
            }
        }
        createElements(liste);

    }

    @FXML
    private void filterBlue(ActionEvent event) throws SQLException {

        filter("\"blue\"");

    }

    @FXML
    private void filterWhite(ActionEvent event) throws SQLException {
          filter("\"whitesmoke\"");
    }

    @FXML
    private void filterPurple(ActionEvent event) throws SQLException {
          filter("\"purple\"");
    }

    @FXML
    private void Filterpink(ActionEvent event) {
        try {
            filter("\"pink\"");
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filterBlack(ActionEvent event) {
                try {
            filter("\"black\"");
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filterDarkred(ActionEvent event) {
                try {
            filter("\"darkred\"");
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filteryellow(ActionEvent event) {
                try {
            filter("\"yellow\"");
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filterGreen(ActionEvent event) {
                try {
            filter("\"green\"");
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ReturnAll(MouseEvent event) {
        try {
            Display();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToCart(ActionEvent event) throws IOException {
        
       /*  FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/GestionPanierEtWishlist/LIstaItems.fxml"));

        try {
            Stage stage = new Stage();
            Parent root = loader.load();
          
            Scene scene = new Scene(root);

            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);    
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
      }*/
       
             Stage parentStage = (Stage) GoToCart.getScene().getWindow();
       
 
   
     
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
             Parent root = loader.load();

             MainMenuFrontController apc = loader.getController();
             apc.AfficherPanier();
     
              Scene scene=new Scene(root);
              parentStage.setScene(scene);
           
              parentStage.show();
        
    }

    @FXML
    private void turnToRed(MouseEvent event) {
    }


}
