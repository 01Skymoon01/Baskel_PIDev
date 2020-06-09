/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionPanierEtWishlist;

import Controllers.PanierController;
import Entite.Panier;
import Entite.Produits;
import Entite.SessionUser;
import GestionShop.ItemProduitController;
import GestionShop.ShopController;
import GuiFront.MainMenuFrontController;
import ServiceProduits.ServiceProduits;
import Services.CommandeService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class LIstaItemsController implements Initializable {

    @FXML
    private AnchorPane myAnchor;
    @FXML
    private TilePane tilePane;

     int count = 0;

    private int nRows = 1;  //no of row for tile pane
    private int nCols = 10;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;

    File filesJpg[]; // file array to store read images info

    ServiceProduits sp = new ServiceProduits();
    @FXML
    private Button Valider;

    
    Produits porduit = new Produits();
    
    CommandeService spN = new CommandeService();
    
    int idClient=5;
    
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

    }

    void Display() throws SQLException {
        List<Integer> liste = new ArrayList();
        liste = spN.showIdPanierParIdClient(idClient);
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

    public VBox createPage(int index) throws SQLException {

        
        
        List<Panier> ProduitP = new ArrayList();
        ProduitP = spN.afficherAllPanierParIdPanier(index);
        Pane newPane = new Pane();

        //Pane newPane2 = new Pane();
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/GestionPanierEtWishlist/item.fxml"));
//  System.out.println("Image d element:" +ProduitP.get(0).getPrivate_prod());
        try {
            Parent root = loader.load();
            ItemController apc = loader.getController();

            String imagename = ProduitP.get(0).getPrivate_prod();
            Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
            apc.setImage(image);

            String name = ProduitP.get(0).getNom_prod();
            apc.setNom(name);
           
            apc.setIdPanier(index);
         int qte = sp.ShowQuantite(ProduitP.get(0).getRefP());
           apc.setQte(qte,ProduitP.get(0).getQte_prod());
            Double prix = ProduitP.get(0).getPrix_prod();
           apc.setPrix(prix);
           
            
  //          apc.setIdItem(index);
           newPane = apc.returnPane();

        } catch (IOException ex) {
            // Logger.getLogger(AddPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VBox pageBox = new VBox();
        pageBox.setPadding(new Insets(-5, 0, -10, -10)); //(top/right/bottom(aala khater erij 15 mch 14 )/left)
        pageBox.getChildren().add(newPane);

                
        return pageBox;
    } 

    @FXML
    private void BlackButton2(MouseEvent event) {
        Valider.setStyle("-fx-background-color : black");

    }

    @FXML
    private void RedButton2(MouseEvent event) {
        Valider.setStyle("-fx-background-color : red");

    }

    @FXML
    private void Valider(ActionEvent event) throws SQLException, IOException {
        System.out.println(".. Valider Panier de client = "+SessionUser.getInstance().getId());
            spN.ValiderPanier(SessionUser.getInstance().getId());
            Stage parentStage = (Stage) Valider.getScene().getWindow();
         

             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
             Parent root = loader.load();

             MainMenuFrontController apc = loader.getController();
             apc.AfficherValiderCommande();
            
              Scene scene=new Scene(root);
              parentStage.setScene(scene);
           
              parentStage.show();
        System.out.println(".. Fin de validation = "+SessionUser.getInstance().getId());

    }
    
}
