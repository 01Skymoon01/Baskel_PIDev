/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionPanierEtWishlist;

import Entite.Panier;
import Entite.Produits;
import Entite.Wishlist;
import GestionShop.ShopController;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class WishlistItemsController implements Initializable {

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


    
    Produits porduit = new Produits();
    
    CommandeService spN = new CommandeService();
    
    int idClient=9;
    
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
        liste = sp.showIdWishlistByIdClient(idClient);
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

        
        
        List<Produits> ProduitP = new ArrayList();
        ProduitP = sp.afficherAllWhishlistParIdWhishlist(index);
        Pane newPane = new Pane();

       
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/GestionPanierEtWishlist/ItemWhishlist.fxml"));
  System.out.println("id d element:" +ProduitP.get(0).getRef_p());
        try {
            Parent root = loader.load();
           ItemWhishlistController apc = loader.getController();

           Produits p = new Produits();
            p = sp.showAllInfoOfProduct(ProduitP.get(0).getRef_p());
          // System.out.println(p.getImage());
           // Wishlist w = new Wishlist(idClient,p);
           apc.showDetails(p);
          
            
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

    
}
