/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hp
 */
public class Baskel extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
           
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/com/xemacscode/Main.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/GestionShop/Shop.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/GuiFront/MainMenuFront.fxml"));
        
        
     //  Parent root = FXMLLoader.load(getClass().getResource("/GestionEvents/AffichageEvents.fxml"));
     //  Parent root = FXMLLoader.load(getClass().getResource("/GestionEvents/AffichageEventsFront.fxml"));
     //  Parent root = FXMLLoader.load(getClass().getResource("EventEmailContact.fxml"));
      
     //  Parent root = FXMLLoader.load(getClass().getResource("ChartsEvents.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
