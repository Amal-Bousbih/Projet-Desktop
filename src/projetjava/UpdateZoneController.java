/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import Entites.Zone;
import SererviceApp.services;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class UpdateZoneController implements Initializable {

    @FXML
    public TextField zone;
    @FXML
    public TextField stock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        zone.setText(ShowZoneController.newZone.getNom());
        stock.setText(String.valueOf(ShowZoneController.newZone.getStcok()));
        
    }    

  
    
    @FXML
    public void updateZone(ActionEvent event) {
        
        services s = new services();
        Zone z = new Zone();
        z.setId(ShowZoneController.newZone.getId());
        z.setNom(zone.getText());
        z.setStcok(Integer.parseInt(stock.getText()));
        System.out.println(z.toString());
        s.updateZone(z.getId(), z.getNom(), z.getStcok());
        
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Area !!");
            alert.setHeaderText("Success !!");
            alert.setContentText("Area successfully modified !!");
            alert.showAndWait(); 
            
        /* List<Zone> lz = s.afficherZone();
         ShowZoneController sh = new ShowZoneController();
         if(sh.table1==null)
         {
             System.out.println("sh est null");
         }
         //sh.table1.refresh();
        /*for(int i=0;i<lz.size();i++)
                 {
                    System.out.println(lz.get(i).toString());
                 }
         
        
        sh.refreshTable2(s.afficherZone());*/
       //s.updateZone(z);
      
       
        
    }
    
    
  
    
}
