/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commande;
import Entite.Details_commande;
import Services.CommandeService;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class RevenueStatisticsController implements Initializable {

    @FXML
    private Label idClient;
    @FXML
    private Label Totale;
    @FXML
    private Label lEtativraison;
    @FXML
    private Label etat;
    @FXML
    private Label Day;
    @FXML
    private Label Month;
    @FXML
    private Label year;
    @FXML
    private Label ref;
    @FXML
    private PieChart pieChart;

    CommandeService sp = new CommandeService();
 final NumberAxis xAxis = new NumberAxis(1, 31, 1);
        final NumberAxis yAxis = new NumberAxis();
    @FXML
    AreaChart<String,Number> ac ;
    @FXML
    private StackedBarChart<String, Number> ProduitCharts;
   // private BarChart<String, Number> ProduitCharts;
            
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chartsCommandes();
           ;
        } catch (SQLException ex) {
            Logger.getLogger(RevenueStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            AreaChartRevenue();
        } catch (SQLException ex) {
            Logger.getLogger(RevenueStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ChartForProduit() ;
        } catch (SQLException ex) {
            Logger.getLogger(RevenueStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    void chartsCommandes() throws SQLException {

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("non paye", Double.valueOf(sp.NbrCommandeNonPaye())),
                        new PieChart.Data("paye", Double.valueOf(sp.NbrCommandePaye())));
        pieChart.setData(pieChartData);
        pieChart.setTitle("Comamandes");
      /*  final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        pieChart.getData().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + "%");
            });
        });*/
    }
    
    
    void AreaChartRevenue() throws SQLException {
    
        
        ac.setTitle("Revenue chaque moins");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("2020");
            List<Commande> liste = sp.RevenueChaqueMoins();
                         

       for(int i=0; i<liste.size(); i++){
//        System.out.println(Integer.valueOf(liste.get(i).getRevenue())); 
              seriesApril.getData().add(new XYChart.Data(Months(liste.get(i).getEtat()) , Double.valueOf(liste.get(i).getRevenue())));
       }

                ac.getData().addAll(seriesApril);
    }
    
        void ChartForProduit() throws SQLException {
    
        
        ProduitCharts.setTitle("Best Saler");
 
          List<Details_commande> liste = sp.Best5Saler();
        for(int i=0; i<liste.size(); i++){
          XYChart.Series seriesApril= new XYChart.Series();
       seriesApril.setName(liste.get(i).getNomProduit());
          
                         

     
        //System.out.println(Integer.valueOf(liste.get(i).getRevenue())); 
              seriesApril.getData().add(new XYChart.Data(liste.get(i).getNomProduit() , Double.valueOf(liste.get(i).getQteProduit())));
                ProduitCharts.getData().addAll(seriesApril);
       }

              
    }
    
    String Months(int nbr){
        switch (nbr) {
            case 1:
                return "janvier";
            case 2:
                return "fevrier";
            case 3:
                return "mars";
            case 4:
                return "avril";
            case 5:
                return "mail";
            case 6:
                return "juin";
            case 7:
                return "juillet";
            case 8:
                return "aout";
            case 9:
                return "septembre";
            case 10:
                return "october";
            case 11:
                return "november";
            case 12:
                return "december";
            default:
                break;
        }
    return "";
    }
}
