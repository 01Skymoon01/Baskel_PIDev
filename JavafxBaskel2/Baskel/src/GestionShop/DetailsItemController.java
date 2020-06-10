/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionShop;

import Entite.Produits;
import Entite.Ratings;
import Entite.SessionUser;
import Entite.Wishlist;
import ServiceProduits.ServiceProduits;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DetailsItemController implements Initializable {

    @FXML
    private Label NomProduit;
    @FXML
    private HBox colorbox;
    @FXML
    private Label nomProd;
    @FXML
    private Label desc;

      ServiceProduits sp = new ServiceProduits();
    @FXML
    private Label stock;
    @FXML
    private Label PrixP2;
    @FXML
    private Label prixP;
    @FXML
    private Label prixP1;
    private Label nomProd1;
    @FXML
    private Rectangle rectangleImg;
    @FXML
    private Rating rating;
    
    int idP ;
    int idClient = SessionUser.getInstance().getId();
    
            @FXML
    private Button submitRating;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }    
  
    
    void setDetails(int id) throws SQLException{
        idP = id;
        String value=sp.showProdNom(id);
        NomProduit.setText(value);
        nomProd.setText(value);
//        nomProd1.setText(value);
        
        String value2=sp.ShowDescription(id);
        desc.setText(value2);
        
        int value3=sp.ShowQuantite(id);
        stock.setText(Integer.toString(value3));
        
        int value4=sp.ShowQuantite(id);
        Double value5=sp.ShowRemise(id);
        if(value4 > 0 )
        stock.setText("En stock :) ");
        else stock.setText("Hors stock :) ");
        
        setImage(id);
        
        double sumRate=sp.sommeRate(idP);
        double countRate=sp.getAllRates(idP);
        double total=sumRate / countRate;
   
        rating.setRating(total);
    }
    
    void setImage(int id) {
        try {
            String imagename = sp.ShowImage(id);
            Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
            rectangleImg.setFill(new ImagePattern(image));
        } catch (SQLException ex) {
            Logger.getLogger(DetailsItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void ShowColors(int id) throws SQLException{
    
                Circle circleBlue=new Circle(700,400,9,Color.BLUE);
                Circle circleBlack=new Circle(700,400,9,Color.BLACK);
                circleBlack.setStroke(Color.DARKGRAY);
                Circle circleYellow=new Circle(700,400,9,Color.YELLOW);
                Circle circlePink=new Circle(700,400,9,Color.PINK);
                Circle circleRed=new Circle(700,400,9,Color.RED);
                Circle circleWhite=new Circle(700,400,9,Color.WHITE);
                Circle circlePurple=new Circle(700,400,9,Color.PURPLE);
                Circle circleGreen=new Circle(700,400,9,Color.GREEN);
                colorbox.setSpacing(8);
                
                
                
               String clr=sp.ShowColors(id);
         System.out.println(clr);
               String clrNew2=clr.substring(1, clr.length()-1);
            System.out.println(clrNew2);
            String[] colorsArray;
            if(clrNew2.contains(" ")){
              colorsArray = clrNew2.split(", "); }
            else{
                colorsArray = clrNew2.split(","); 
            }
               
               
                colorbox.getChildren().clear();
                for(int i=0;i<colorsArray.length;i++){
                    if(colorsArray[i].equals("\"black\"")){
                        colorbox.getChildren().add(circleBlack);
                        
                    }
                    if(colorsArray[i].equals("\"blue\"")){
                        colorbox.getChildren().add(circleBlue);
                    }
                    if(colorsArray[i].equals("\"darkred\"")){
                        colorbox.getChildren().add(circleRed);
                    }
                    if(colorsArray[i].equals("\"yellow\"")){
                        colorbox.getChildren().add(circleYellow);
                    }
                    if(colorsArray[i].equals("\"green\"")){
                        colorbox.getChildren().add(circleGreen);
                    }
                    if(colorsArray[i].equals("\"pink\"")){
                        colorbox.getChildren().add(circlePink);
                    }
                    if(colorsArray[i].equals("\"whitesmoke\"")){
                        colorbox.getChildren().add(circleWhite);
                    }
                    if(colorsArray[i].equals("\"purple\"")){
                        colorbox.getChildren().add(circlePurple);
                    }
                    
                    
                    
                    
                    
               
                }
                
    
    }
    
    
    
    @FXML
    private void submitRating(ActionEvent event){
     
      
        try {
            Produits p =sp.showAllInfoOfProduct(idP);
            
            double rate=rating.getRating();
            
            
            
            double sumRate=sp.sommeRate(idP);
            double countRate=sp.getAllRates(idP);
            double total=0;
                System.out.println("rating = " +sp.getAllRates(idP));

            if(countRate==0){
               total = rate;
            } 
             total=sumRate / countRate;
            System.out.println(sumRate+" / "+countRate);
            System.out.println("TOTAL RAAAATE: "+total);
            
           
            
            Ratings r=new Ratings(p,idClient,rate,total);
            
            if(sp.existRating(idClient, p.getRef_p()) == 0 ){
            sp.AddRating(r);
            
            } 
            else{
               sp.UpdateRating(rate,r.getIdClient(),r.getP().getRef_p()); 
               sp.UpdateRatingTotal(total,r.getP().getRef_p());
            }
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(DashFX.class.getName()).log(Level.SEVERE, null, ex);
        }
  
            
    }
    
    

    @FXML
    private void removeWindow(MouseEvent event) {
        
           Stage stage = (Stage) colorbox.getScene().getWindow();
    // do what you have to do
           stage.close();
    }



    @FXML
    private void AjouterWishlist(ActionEvent event) throws SQLException {
        
        Produits p =sp.showAllInfoOfProduct(idP);
//        System.out.println(p);
        Wishlist w = new Wishlist(idClient,p);
  //              System.out.println(w);
        if (sp.produitExistWishlist(idP,idClient)==0){
        sp.AjouterAWishlist(w); 
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Baskel");
            alert.setHeaderText("Produit ajouté");
            alert.setContentText(p.getNom_p());

            alert.showAndWait();
        
        }
        else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Baskel");
            alert.setHeaderText("Produit existe déjà.");
            alert.setContentText(p.getNom_p());

            alert.showAndWait();
        }
      /*  
           
         FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/GestionPanierEtWishlist/WishlistItems.fxml"));

        try {
            Stage stage = new Stage();
            Parent root = loader.load();
          // DetailsItemController apc = loader.getController();
            
            //apc.setImage(rectangleImg.getFill());
            // apc.setDetails(Integer.parseInt(refP.getText()));
            // apc.ShowColors(Integer.parseInt(refP.getText()));
            Scene scene = new Scene(root);

            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);    
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
      }*/
    }
    
}
