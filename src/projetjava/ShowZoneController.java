/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import Connection.Connexion;
import Entites.Location;

import Entites.Zone;
import SererviceApp.services;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public  class ShowZoneController implements Initializable {
ObservableList list= FXCollections.observableArrayList();
    
    @FXML
    private TextField searchbar;
    @FXML
    public TableColumn<Zone, String> column1;
    @FXML
    public TableColumn<Zone, Integer> column2;
    @FXML
    public  TableView<Zone> table1 = new TableView<>();
    Connection cnx =null;
    
    ResultSet rs = null;
    PreparedStatement pst = null;
    public static Zone newZone = new Zone();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
     
        try{
            list.clear();
           
                //column11.setCellValueFactory(new PropertyValueFactory<Zone, Integer>("id"));
                column1.setCellValueFactory(new PropertyValueFactory<Zone, String>("nom"));
                column2.setCellValueFactory(new PropertyValueFactory<Zone, Integer>("stcok"));
                
                services sp = new services();
                
                refreshTable2(sp.afficherZone());
                
            }
        catch(Exception ex){
            System.out.println("erreur load"+ex.toString());
        }
        // TODO
    
            }
   public  void  refreshTable2(List<Zone> a) {
        ObservableList<Zone> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(a);
        table1.setItems(obs);
        FilteredList<Zone> filteredData = new FilteredList<>(obs, b -> true);
        searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (aff.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
                
                else {
                    return false; // Does not match.
                }
            });
        });
       SortedList<Zone> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table1.comparatorProperty());
        table1.setItems(sortedData);
        for (int i=0; i<obs.size(); i++)
        {
            System.out.println(obs.get(i).toString());
            System.out.println();
            
        }

    } 

    @FXML
    private void Delete() throws SQLException {
        
        Zone v = table1.getSelectionModel().getSelectedItem();
                services s = new services();
                s.deletezone(v);
            
            refreshTable2(s.afficherZone());
    }

   

    @FXML
    private boolean updatetableZone(ActionEvent event) {
        
         try {
            Zone z = table1.getSelectionModel().getSelectedItem();
            services sp = new services();
            newZone = sp.getSelectedRowFromGrid(sp.getId(table1.getSelectionModel().getSelectedItem().getNom()), table1.getSelectionModel().getSelectedItem().getNom(),table1.getSelectionModel().getSelectedItem().getStcok());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateZone.fxml"));
            Parent root = (Parent) fxmlLoader.load();
                        
            Stage stage = new Stage();
            stage.setTitle("Price Information");
            stage.setScene(new Scene(root));
            stage.show();
            refreshTable2(sp.afficherZone());
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
             
             
            return true;
    }
}
