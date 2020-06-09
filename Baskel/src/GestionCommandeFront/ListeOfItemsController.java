/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCommandeFront;

import Entite.Commande;
import Entite.Panier;
import Entite.Produits;
import Entite.SessionUser;
import GestionShop.ShopController;
import ServiceProduits.ServiceProduits;
import Services.CommandeService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ListeOfItemsController implements Initializable {

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
    
    int idClient=SessionUser.getInstance().getId();
    
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
        liste = spN.readIdCommandeForClient(idClient);
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

        
        
        List<Commande> UneCommande = new ArrayList();
        UneCommande = spN.readCommande(index);
        Pane newPane = new Pane();

       
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/GestionCommandeFront/Item.fxml"));
        try {
            Parent root = loader.load();
            GestionCommandeFront.ItemController apc = loader.getController();

            int idC = UneCommande.get(0).getId();
            Double PrixC = UneCommande.get(0).getTOtalPrix();
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            
            apc.setIdCommande(idC);
            apc.setPrixCommande(PrixC);
             apc.setDateCommande(dateFormat.format(UneCommande.get(0).getDate()));
            
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
