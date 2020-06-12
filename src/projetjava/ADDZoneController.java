/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import SererviceApp.services;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ADDZoneController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextField Stock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouetNouvelleZone(ActionEvent event) {
        
        services sp  = new services();
        sp.AddZone(Name.getText(), Integer.parseInt(Stock.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Bike !!");
            alert.setHeaderText("Success !!");
            alert.setContentText("Area successfully added !!");
            alert.showAndWait(); 
        
    }
    
}
