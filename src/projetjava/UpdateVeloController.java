/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import Entites.Velo;
import SererviceApp.services;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.Alert;
/**
 * FXML Controller class
 *
 * @author amalb
 */
public class UpdateVeloController implements Initializable {

    @FXML
    public TextField Name;
    @FXML
    public TextField Type;
    @FXML
    public TextField Disponibility;
    @FXML
    public TextField Price;
    @FXML
    public ComboBox<String> Area;
    @FXML
    public DatePicker Date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Area.setValue(String.valueOf(ShowbikeController.newVelo.getZone()));
        Name.setText(ShowbikeController.newVelo.getNom());
        Date.setValue(ShowbikeController.newVelo.getDebutservice().toLocalDate());
        Type.setText(ShowbikeController.newVelo.getType());
        
        Disponibility.setText(String.valueOf(ShowbikeController.newVelo.getDisponible()));
        Price.setText(String.valueOf(ShowbikeController.newVelo.getPrix()));
        services sp = new services();
        Area.getItems().addAll(sp.LoadNameFromeZone());
        // TODO
    }    

    @FXML
    public void UpdateBike(ActionEvent event) {
        
        services s = new services();
        Velo v = new Velo();
       
       int x = s.GetIdZone2(Area.getValue());
       v.setIdz(ShowbikeController.newVelo.getIdz());
       v.setZone(x);
       v.setNom(Name.getText());
       v.setDebutservice(java.sql.Date.valueOf(Date.getValue()));
       v.setType(Type.getText());
       v.setDisponible( Boolean.parseBoolean(Disponibility.getText()));
       v.setPrix(Double.parseDouble(Price.getText().toString()));
       System.out.println(v.toString());
       s.updateVelo(v.getIdz(),v.getZone(),v.getNom(),v.getDebutservice(),v.getType(),v.getDisponible(),v.getPrix());
      
        
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Bike !!");
            alert.setHeaderText("Success !!");
            alert.setContentText("Bike successfully modified !!");
            alert.showAndWait(); 
        
       
    }
    
}
