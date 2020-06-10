/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionProduits;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ery's Desktop
 */
public class CalculatorController implements Initializable {

    @FXML
    private Button six;
    @FXML
    private Button three;
    @FXML
    private Button two;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button one;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button mult;
    @FXML
    private Button zero;
    @FXML
    private Button equals;
    @FXML
    private Button clear;
    @FXML
    private Button div;
    @FXML
    private Button point;
    @FXML
    private Button delete;
    @FXML
    private TextField t;


    static double a=0,b=0,result=0;
    static int operator=0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void Calculate(ActionEvent event) {
    
        
        if(event.getSource()==one){
        t.setText(t.getText().concat("1"));}
        
        
        if(event.getSource()==two){
        t.setText(t.getText().concat("2"));}
        
        
        if(event.getSource()==three){
        t.setText(t.getText().concat("3"));}
        
        
        if(event.getSource()==four){
        t.setText(t.getText().concat("4"));}
        
        
        if(event.getSource()==five){
        t.setText(t.getText().concat("5"));}
        
        
        if(event.getSource()==six){
        t.setText(t.getText().concat("6"));}
        
        
        if(event.getSource()==seven){
        t.setText(t.getText().concat("7"));}
        
        
        if(event.getSource()==eight){
        t.setText(t.getText().concat("8"));}
        
        
        if(event.getSource()==nine){
        t.setText(t.getText().concat("9"));}
        
        
        if(event.getSource()==zero){
        t.setText(t.getText().concat("0"));}
        
        
        if(event.getSource()==point){
        t.setText(t.getText().concat("."));}
        
        
        if(event.getSource()==plus)
        {
            a=Double.parseDouble(t.getText());
            operator=1;
            t.setText("");
        } 
        
        
        if(event.getSource()==minus)
        {
            a=Double.parseDouble(t.getText());
            operator=2;
            t.setText("");
        }
        
        
        if(event.getSource()==mult)
        {
            a=Double.parseDouble(t.getText());
            operator=3;
            t.setText("");
        }
        
        if(event.getSource()==div)
        {
            a=Double.parseDouble(t.getText());
            operator=4;
            t.setText("");
        }
        
        if(event.getSource()==equals)
        {
            b=Double.parseDouble(t.getText());
            switch(operator)
            {
            case 1: result=a+b;
            break;
            
            case 2: result=a-b;
            break;
            
            case 3: result=a*b;
            break;
            
            case 4: result=a/b;
            break;
            
            default: result=0;
            }
            t.setText(""+result);
        }
        
        if(event.getSource()==clear){
        t.setText("");}
        
        if(event.getSource()==delete)
        {
        String s=t.getText();
        t.setText("");
            for(int i=0;i<s.length()-1;i++)
            t.setText(t.getText()+s.charAt(i));
        } 
    }
    
}
