/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import SererviceApp.services;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ADDBikeController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextField Type;
    @FXML
    private TextField Disponibility;
    @FXML
    private TextField Price;
    @FXML
    private ComboBox<String> Area;
    @FXML
    private DatePicker Date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        services sp = new services();
        Area.getItems().addAll(sp.LoadNameFromeZone());
        // TODO
    }    

    @FXML
    private void ADDtoStok(ActionEvent event) {
        services sp = new services() ;
        int x = sp.GetIdZone2(Area.getValue());
        sp.AddVeloToStock(x, Name.getText().toString(), java.sql.Date.valueOf(Date.getValue()), Type.getText().toString(), Disponibility.getText(), Double.parseDouble(Price.getText().toString()));
         
        
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Bike !!");
            alert.setHeaderText("Success !!");
            alert.setContentText("Bike successfully added !!");
            alert.showAndWait(); 
    }
    
}
