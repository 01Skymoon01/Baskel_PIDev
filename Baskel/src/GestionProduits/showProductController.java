/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionProduits;

import Entite.Categories;
import Entite.Produits;
import GestionCategories.ShowCategoryController;
import ServiceProduits.ServiceProduits;
import ServiceProduits.UploadServices;
import java.awt.Stroke;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Formatter;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.apache.commons.io.FileUtils;




/**
 *
 * @author Ery's Desktop
 */
public class showProductController implements Initializable {
    
    
    @FXML
    private TableView <Produits> tableProd;
    @FXML
    private TableColumn <Produits,Integer> ref_p;
    @FXML
    private TableColumn <Produits,String> nom_p;
    @FXML
    private Label labelGenre,colors,colorLab;
    @FXML
    private ImageView imageProd;
    @FXML
    private VBox detailProd;
    @FXML
    private Rectangle rectangleImg;
    @FXML
    private HBox colorbox;
    @FXML
    private Pagination pagination;
    
    @FXML
    private Label nomCat,genre,quantite,prix,marque,description,prixLab,propos,stock,labelRemise;
    @FXML
    private TextArea desc;
    @FXML
    private TextField textUpdate,textRemise;
    @FXML
    private Button btnRemise;
    @FXML
    private Pane showprodpanel;
    @FXML
    private Rectangle rect;
    
    ObservableList<Produits> arr ;
    
    int itemPerPage=5, pageCount;

    private FilteredList<Produits> filteredData;
    
    
    
   
    PieChart.Data data[] = new PieChart.Data[2];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceProduits sp = new ServiceProduits();
        
        try {
            arr = sp.ShowProduct();
        } catch (SQLException ex) {
            Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        
        tableProd.setItems(arr);

           try {
            pageCount = (sp.countProdRows() / itemPerPage) +1;
            pagination.setPageCount(pageCount);
            pagination.setPageFactory(this :: createPage);
        } catch (SQLException ex) {
            Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        
         tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               
               
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               //Math.Round(prix_p, 2);
              //System.out.println(prix_p);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                
              
                
                prix.setText(Double.toString(prix_p)+" DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
               
                
               
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
                    
                    
                    
                    
                    
                 //   colorbox.getChildren().ad
                }
                
                
                /***********************FOR IMAAAAAAAAAGE****************************/
                
     

                       
                          String imagename = sp.ShowImage(id);
                           Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                            rectangleImg.setFill(new ImagePattern(image)); 
                        //   imageProd.setImage(image);
                        
                     
                
                if(sp.ShowRemise(id) != 0){
                   labelRemise.setVisible(true);
                    String remise=Double.toString(sp.ShowRemise(id));
                    System.out.println("remise "+remise);
                   
                    labelRemise.setText(remise+" % de remise.");
                    //showprodpanel.getChildren().add(labelRemise);
                } else if(sp.ShowRemise(id)==0){
                    labelRemise.setVisible(false);
                }
                
                
                
                
                
                
                /********************************************************/
               
               
                
           
                
               
                    
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                
                
            }
               
                    
        });
         /*
     
          for(int i=0;i<arr.size();i++){
              
            try {
                // ServiceProduits sp2 =new ServiceProduits();
                int id= arr.get(i).getRef_p();
               // Label labRemise=new Label();
                
                //stock.setText("Sont encore en stock.");
                if(sp.ShowRemise(id) != 0){
                   labelRemise.setVisible(true);
                    String remise=Double.toString(sp.ShowRemise(id));
                    System.out.println("remise "+remise);
                    /*labRemise.setVisible(true);
                    labRemise.setTranslateX(277);
                    labRemise.setTranslateY(203);
                    labRemise.setStyle("-fx-border-color:red; -fx-background-color:  #F44336;");
                    labelRemise.setText(remise+" % de remise.");
                    //showprodpanel.getChildren().add(labelRemise);
                } else if(sp.ShowRemise(id)==0){
                    labelRemise.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
        */
         
         
         /*********************************Recherche*************************/
         
         
   filteredData = new FilteredList<>(arr, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		textUpdate.textProperty().addListener((observable, oldValue, newValue)->{
                    filteredData.setPredicate(p->
                            newValue == null || newValue.isEmpty() || p.getNom_p().toLowerCase()
                            .contains(newValue.toLowerCase()) || String.valueOf(p.getRef_p()).toLowerCase()
                            .contains(newValue.toLowerCase()));
            changeTableView(pagination.getCurrentPageIndex(), itemPerPage);
                    
                });
                
             
              
                
         
                
               
              int totalPage = (int) (Math.ceil(arr.size() * 1.0 / itemPerPage));
              System.out.print(totalPage);
                pagination.setPageCount(totalPage);
                pagination.setCurrentPageIndex(0);
                this.changeTableView(0, itemPerPage);
                pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), itemPerPage));
     
    }
    
    private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, arr.size());

        int minIndex = Math.min(toIndex, filteredData.size());
       
        SortedList<Produits> sortedData = new SortedList<>(
                        FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
                sortedData.comparatorProperty().bind(tableProd.comparatorProperty());

                tableProd.setItems(sortedData);
                
                 

    }
    
    
    
    @FXML
    private void supprimerProd(ActionEvent event) throws SQLException{
        
      UploadServices up =new UploadServices();
     ServiceProduits sp =new ServiceProduits();
      Produits p= tableProd.getSelectionModel().getSelectedItem();
      int id=p.getRef_p();
              if(id != 0){
                  
               
               sp.SupprimerWishProd(id);
               sp.deleteRating(id);
               sp.supprimerProduit(id);
               
               up.delete(p.getImage());
            }
         tableProd.getItems().clear();
        try {
            tableProd.setItems(sp.ShowProduct());
        } catch (SQLException ex) {
            Logger.getLogger(ShowCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void showProdDet(ActionEvent event){
        ServiceProduits sp = new ServiceProduits();
        //String imgName=sp.ShowProduct2(id);

        //String imagePath = "../../../../../C:/wamp64/www/WebProjectSymfony/Baskel/web/uploads/images/".concat(imgName);
        String imagePath = "nana.jpg";
        //File file = new File(imagePath);
        //String localUrl = file.toURI().toURL().toString();
        Image image=new Image(getClass().getResource("../resources/assets/"+imagePath).toExternalForm());
        //ImageView imageView=new ImageView(image);
        imageProd.setImage(image);
        //detailProd.getChildren().add(imageView);
   
    
    
    }
    
    
  
    @FXML
    private void ShowCategory(ActionEvent event) {
           
        FXMLLoader loader = new FXMLLoader
            (getClass()
             .getResource("/GestionCategories/ShowCategory.fxml"));
            try {
                Parent root = loader.load();
             loader.getController();
                
                tableProd.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }      
    }
    
    @FXML
    private void addProduct(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
            (getClass()
             .getResource("addProduct.fxml"));
            try {
                Parent root = loader.load();
             loader.getController();
                
                rectangleImg.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }  
    }
    
    
    @FXML
    private void updateProduct(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
            (getClass()
             .getResource("updateProduct.fxml"));
            try {
                Parent root = loader.load();
                loader.getController();
                UpdateProductController updateController = loader.getController();
                updateController.setLabelText(textUpdate.getText());
                System.out.print(textUpdate.getText());
                rectangleImg.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
           } 
       
    }
    
    
    
    private Node createPage2(int id,int pageIndex) {
                try {
                    ServiceProduits sp=new ServiceProduits();
                    int fromIndex = pageIndex * itemPerPage;
                    int to=itemPerPage;
                    
                //    Produits p =new Produits();

                    tableProd.setItems(FXCollections.observableArrayList(sp.ShowProductFiltre2(id,fromIndex,to)));
              

                } catch (SQLException ex) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return tableProd;
            }
    
     /********************************FILTEEER*******************************/
    
  /*
    @FXML
    private void showBlue(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
        
        
        
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
                
                
                
          
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("blue")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
        
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
    
    
    @FXML
    private void showRed(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("darkred")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
    
    @FXML
    private void showYellow(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("yellow")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
   
    
    
    @FXML
    private void showGreen(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("green")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
    
    @FXML
    private void showBlack(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("black")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
    
    @FXML
    private void showWhite(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("whitesmoke")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
    
    @FXML
    private void showPurple(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("purple")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
    
    @FXML
    private void showPink(ActionEvent event) {
        
        ServiceProduits sp =new ServiceProduits();
        ObservableList<Produits> arr2 =FXCollections.observableArrayList();
        Produits p =new Produits();
         
        for(int i =0; i< arr.size();i++){
            try {
                int id= arr.get(i).getRef_p();
             
                 String clr=sp.ShowColors(id);
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
                    colorbox.getChildren().clear();
                for (String colorsArray1 : colorsArray) {
                    if (colorsArray1.equals("pink")) {

                        p=sp.ShowProductFiltre(id); 
                        arr2.add(p);
                    }
                }           
            }
             catch (SQLException ex) {
                Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        tableProd.getItems().clear();
        tableProd.setItems(arr2);
     
        tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
              try{
               Produits p= tableProd.getSelectionModel().getSelectedItem();
               int id=p.getRef_p();
        
               String nomCategory,genre_p,marque_p,description2;
               nomCategory=sp.ShowCategoryProd(id);
               genre_p=sp.ShowGenre(id);
               marque_p=sp.ShowMarque(id);
               description2=sp.ShowDescription(id);
               
               int quantite_p=sp.ShowQuantite(id);
               double prix_p=sp.ShowPrix(id);
               
                nomCat.setText("*Catégorie: "+nomCategory);
                genre.setText("*Pour "+genre_p+".");
                marque.setText("*Marque: "+marque_p);
                description.setText(description2);
                if (quantite_p!=0){
                quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                } else {
                    quantite.setText("*Hors stock.");
                }
                prix.setText(Double.toString(prix_p)+"DT.");
                prixLab.setText("Prix:");
                propos.setText("À propos:");
                colorLab.setText("Couleur:");
              
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
               String clrNew2=clr.substring(1, clr.length()-1);          
               String[] colorsArray = clrNew2.split(", ");
               
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
                    
                 //   colorbox.getChildren().ad
                }
                  
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image)); 
        
              
                } catch (SQLException ex ) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }       
        });  
    }
    /************************************************************************/
    
    
    
    
    
    
    
    /***************PAGINATION***************************/
        
             private Node createPage(int pageIndex) {
                try {
                    ServiceProduits sp=new ServiceProduits();
                    int fromIndex = pageIndex * itemPerPage;
                    int to=itemPerPage;

                    tableProd.setItems(FXCollections.observableArrayList(sp.ShowProduct2(fromIndex,to)));
              

                } catch (SQLException ex) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return tableProd;
            }
        
        /****************************************************/
    
    
    
    /*
    @FXML
    private void CancelFilter(ActionEvent event) throws SQLException {
        
        ServiceProduits sp = new ServiceProduits();
        
         tableProd.getItems().clear();
                        
                        
        arr = sp.ShowProduct();
        ref_p.setCellValueFactory(new PropertyValueFactory<>("ref_p"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
       
        
        tableProd.setItems(arr);
        
        
        
        
        
         tableProd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
          
                try {
                    Produits p= tableProd.getSelectionModel().getSelectedItem();
                    int id=p.getRef_p();
                    String nomCategory,genre_p,marque_p,description2;
                    nomCategory=sp.ShowCategoryProd(id);
                    genre_p=sp.ShowGenre(id);
                    marque_p=sp.ShowMarque(id);
                    description2=sp.ShowDescription(id);
                    int quantite_p=sp.ShowQuantite(id);
                    double prix_p=sp.ShowPrix(id);
                    nomCat.setText("*Catégorie: "+nomCategory);
                    genre.setText("*Pour "+genre_p+".");
                    marque.setText("*Marque: "+marque_p);
                    description.setText(description2);
                    if (quantite_p!=0){
                        quantite.setText("*Quantité en stock: "+Integer.toString(quantite_p));
                    } else {
                        quantite.setText("*Hors stock.");
                    }   prix.setText(Double.toString(prix_p)+"DT.");
                    prixLab.setText("Prix:");
                    propos.setText("À propos:");
                    colorLab.setText("Couleur:");
                    //stock.setText("Sont encore en stock.");
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
                    String clrNew2=clr.substring(1, clr.length()-1);
                    String[] colorsArray = clrNew2.split(", ");
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
                    
                 //   colorbox.getChildren().ad
                }   
                    
                   
                    
                    
                    
                    String imagename = sp.ShowImage(id);
                    Image image = new Image("http://localhost/WebProjectSymfony/Baskel/web/uploads/images/" + imagename);
                    rectangleImg.setFill(new ImagePattern(image));
                 
                } catch (SQLException ex) {
                    Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
        });
      
    }
    
    */
    
    
    
    
    @FXML
    private void RemisePrix(ActionEvent event){
        
         if(textUpdate.getText().length()!=0){
       rect.setVisible(false);
       textRemise.setVisible(true);
         textRemise.setPromptText("... %");
                 }
         else{
             textUpdate.setPromptText("Taper la référence du produit d'abord.");
         }
    }
    
    
    @FXML
    private void updatePrix(ActionEvent event){
        try {
            ServiceProduits sp = new ServiceProduits();
           
            int id=Integer.parseInt(textUpdate.getText());
            double remise=Double.parseDouble(textRemise.getText());
            double oldPrix=sp.ShowPrix(id);
            
            
            double newPrix=oldPrix-((oldPrix*remise)/100);
            sp.updatePrix(newPrix, id);
            sp.updateSolde(remise,id);
        } catch (SQLException ex) {
            Logger.getLogger(showProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
    @FXML
    private void Calculator(ActionEvent event){
       /*
           FXMLLoader loader = new FXMLLoader
            (getClass()
            .getResource("calculator.fxml"));
            try {
            Parent root = loader.load();
            CalculatorController apc = loader.getController();
            
            textRemise.getScene().setRoot(root);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
      */
       
       try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("calculator.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 467, 500);
        Stage stage = new Stage();
        stage.setTitle("VOTRE CALCULATRICE ...");
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }
    }
    
    
    @FXML
    public void Statistiques(ActionEvent event) throws SQLException{
        
        ServiceProduits sp = new ServiceProduits();
        int in_stock=0, hors_stock=0;
        Stage stage = new Stage();
       
        stage.setTitle("VOTRE STOCK DE PRODUITS ..."); 
   
   
        PieChart.Data data[] = new PieChart.Data[2]; 
   
      
            in_stock=sp.InStock();
            hors_stock=sp.HorsStock();
            
            int values[] = {in_stock, hors_stock}; 
            String status[] = {"Produits en stock", "Produits hors Stock"};
   
        for (int i = 0; i < 2; i++) { 
            data[i] = new PieChart.Data(status[i]+" = "+values[i], values[i]); 
        } 
   
        // create a pie chart 
        PieChart pie_chart = new PieChart(FXCollections.observableArrayList(data)); 
   
        // create a Group 
        Group group = new Group(pie_chart);
        
        Pane root   = new Pane(group);
        
        
   
        // create a scene 
        Scene scene = new Scene(root, 550, 500); 
        
        String css = this.getClass().getResource("piechart.css").toExternalForm(); 
        scene.getStylesheets().add(css);
        // set the scene 
        stage.setScene(scene); 
   
        stage.show(); 
    }
    
    
    
}
