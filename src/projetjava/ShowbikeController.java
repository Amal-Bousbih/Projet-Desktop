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
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static projetjava.ShowZoneController.newZone;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ShowbikeController implements Initializable {
    ObservableList list= FXCollections.observableArrayList();

    
    @FXML
    private TableColumn<Velo, Integer> column1;
    @FXML
    private TableColumn<Velo, Integer> column2;
    @FXML
    private TableColumn<Velo, String> column3;
    @FXML
    private TableColumn<Velo, Date> column4;
    @FXML
    private TableColumn<Velo, String> column5;
    @FXML
    private TableColumn<Velo, Boolean> column6;
    @FXML
    private TableColumn<Velo, Double> column7;
    
    @FXML
    private TableView<Velo> table1;
 public static Velo newVelo = new Velo();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
    
         try{
            list.clear();
            services sp = new services() ;
           
                column1.setCellValueFactory(new PropertyValueFactory<Velo, Integer>("idz"));
                column2.setCellValueFactory(new PropertyValueFactory<Velo, Integer>("zone"));
                //column2.setText(sp.getNomZone(1));
                column3.setCellValueFactory(new PropertyValueFactory<Velo, String>("nom"));
                column4.setCellValueFactory(new PropertyValueFactory<Velo, Date>("debutservice"));
                column5.setCellValueFactory(new PropertyValueFactory<Velo, String>("type"));
                column6.setCellValueFactory(new PropertyValueFactory<Velo, Boolean>("disponible"));
                column7.setCellValueFactory(new PropertyValueFactory<Velo, Double>("prix"));
                
               
        
                refreshTable1(sp.afficherVelo());
                
   
        }
        catch(Exception ex){
            System.out.println("erreur load");
        }
      
    }    

    
    
     private void refreshTable1(List<Velo> a) {
        ObservableList<Velo> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(a);
        table1.setItems(obs);
        FilteredList<Velo> filteredData = new FilteredList<>(obs, b -> true);
       
       SortedList<Velo> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table1.comparatorProperty());
        table1.setItems(sortedData);
        for (int i=0; i<obs.size(); i++)
        {
            System.out.println(obs.get(i).toString());
            System.out.println();
            
        }
        
        
        
        

    }

    @FXML
    private void deletevelo(ActionEvent event) {
        
         Velo v = table1.getSelectionModel().getSelectedItem();
                services s = new services();
                s.deleteVelo(v);
            
            refreshTable1(s.afficherVelo());
    }
    
    
    
    @FXML
    private boolean updatetableVelo(ActionEvent event) {

        try {
            Velo v = table1.getSelectionModel().getSelectedItem();
            services sp = new services();
            newVelo = sp.getSelectedRowFromGrid2(sp.getId2(table1.getSelectionModel().getSelectedItem().getNom()),table1.getSelectionModel().getSelectedItem().getZone(),table1.getSelectionModel().getSelectedItem().getNom(), table1.getSelectionModel().getSelectedItem().getDebutservice(),table1.getSelectionModel().getSelectedItem().getType(),table1.getSelectionModel().getSelectedItem().getDisponible(),table1.getSelectionModel().getSelectedItem().getPrix());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateVelo.fxml"));
            Parent root = (Parent) fxmlLoader.load();            
            Stage stage = new Stage();
            stage.setTitle("Update");
            stage.setScene(new Scene(root));
            stage.show();
            /*UpdateZoneController x = new UpdateZoneController();
            x.zone.setText(newZone.getNom());
            x.stock.setText(String.valueOf(newZone.getStcok()));*/
            
            refreshTable1(sp.afficherVelo());
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
             
             
            return true;
    }
}
