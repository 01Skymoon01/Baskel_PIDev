/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents;

import adhma.Utils.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class AffichageChartsEvent implements Initializable {
    
    Connection En = Database.getInstance().getConnection();
    
    @FXML
    private Pane pnlOverview1;
    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private Button btnChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void LoadChart(ActionEvent event) {
        
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            Statement st = En.createStatement();
            String query = "select Nom,nb_participants from event order by nb_participants";
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            barChart.getData().add(series);
            
        } catch (Exception e) {
        }
    }
    
}
