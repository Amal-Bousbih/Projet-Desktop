/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import Entites.Location;
import Entites.Velo;
import Entites.Zone;
import SererviceApp.services;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class FrontController implements Initializable {

    @FXML
    private Button btn_shArea;
    @FXML
    private Button btn_addzone;
    @FXML
    private ComboBox<String> zone;
    @FXML
    private ComboBox<String> velo;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        services sp = new services() ;
        zone.getItems().addAll(sp.LoadNameFromeZone());
    }    

    @FXML
    private boolean afficherZone(ActionEvent event) {
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Bike.fxml"));
            Parent root = (Parent) fxmlLoader.load();            
            Stage stage = new Stage();
            stage.setTitle("Bike Information");
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
       
    }

    private boolean handleButtonAction(ActionEvent event) {
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Price.fxml"));
            Parent root = (Parent) fxmlLoader.load();            
            Stage stage = new Stage();
            stage.setTitle("Price Information");
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }

    @FXML
    private void shbike(ActionEvent event) {
    }

    @FXML
    private void AddLocation(ActionEvent event) {
        Zone z = new Zone();
        Velo v = new Velo();
        Location l = new Location();
        services SE=new services();
        int i = 0;
    
         java.sql.Date date1 = java.sql.Date.valueOf(datedebut.getValue());
         java.sql.Date date2 = java.sql.Date.valueOf(datefin.getValue());
         
         
         
         
          if (date1.after(date2)) {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please fill!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("date fin doit etre superieur a date debut!!");
            alert.showAndWait(); 
          }
          else 
          {
             if ((0 == SE.NombreStock(zone.getValue().toString())))
             {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setTitle("End of stock !!");
                 alert.setHeaderText("WARNING !");
                 alert.setContentText("Stock of bikes in this area is exhausted !!");
                 alert.showAndWait(); 
             } 
             else
             {
               if ((0 == SE.DisponibilityVelo(velo.getValue().toString()))){
                   
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bike Not Available !!");
                alert.setHeaderText("WARNING !");
                alert.setContentText("Bike already rented choose another !!");
                alert.showAndWait(); } 
               else 
               {
                   SE.AddLocation(SE.GetIdZone(zone.getValue().toString()),SE.GetIdVelo(velo.getValue().toString()), java.sql.Date.valueOf(datedebut.getValue()),java.sql.Date.valueOf(datefin.getValue()));
                   SE.RemoveVeloFromStock(zone.getValue().toString());
                   SE.DisplayAVelo(velo.getValue().toString());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" ");
                    alert.setHeaderText("Addition form");
                    alert.setContentText("your Form is successfully added\n Your Price = "+SE.GetPriceOfLocation(zone.getValue().toString(), date1, date2)*SE.GetPriceOfVelo(velo.getValue().toString())+"dt");
                    alert.showAndWait();
               }
             }
          }
    }
       /* z.SetName(zone.getValue().toString());
        v.SetName(velo.getValue().toString());
        l.setDateDebut(java.sql.Date.valueOf(datedebut.getValue()));
        l.setDateFin(java.sql.Date.valueOf(datefin.getValue()));
        */
       
        
   

    @FXML
    private void Area(ActionEvent event) {
        
         Zone z = new Zone();
        Velo v = new Velo();
        services sp = new services() ;
            velo.getItems().addAll(sp.LoadNameFromeVelo(zone.getValue().toString()));
    }

    @FXML
    private void selectedItems(MouseEvent event) {
    }

    @FXML
    private boolean price(ActionEvent event) {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Price.fxml"));
            Parent root = (Parent) fxmlLoader.load();            
            Stage stage = new Stage();
            stage.setTitle("Price Information");
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }
    
}
