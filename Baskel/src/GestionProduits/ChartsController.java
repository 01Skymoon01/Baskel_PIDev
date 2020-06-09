/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionProduits;

import ServiceProduits.ServiceProduits;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Ery's Desktop
 */
public class ChartsController implements Initializable {

    @FXML
    private PieChart piechart;
    
    ServiceProduits sp = new ServiceProduits();
    int in_stock=0, hors_stock=0;
   
    PieChart.Data data[] = new PieChart.Data[2]; 
            
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            in_stock=sp.InStock();
            hors_stock=sp.HorsStock();
            
            int values[] = {in_stock, hors_stock}; 
            String status[] = {"In Stock", "Hors Stock"}; 
   
        for (int i = 0; i < 2; i++) { 
            data[i] = new PieChart.Data(status[i], values[i]); 
            System.out.println(status[i]+":"+values[i]);
        }
        
        piechart= new
                PieChart(FXCollections.observableArrayList(data)); 
            
            
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
