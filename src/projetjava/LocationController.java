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
import java.net.URL;
import java.sql.Date;
import java.util.Collection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.lang.model.element.Element;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class LocationController implements Initializable {
    
ObservableList list= FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> zone;
    @FXML
    private ComboBox<String> velo;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private ListView<Collection> liste3;
    public int i=0;

    @FXML
    private TableColumn<Location, Integer> Column1;
    @FXML
    private TableColumn<Location, Integer> column2;
    @FXML
    private TableColumn<Location, Date> Colomn3;
    @FXML
    private TableColumn<Location, Date> colomn4;
    @FXML
    private TableView<Location> table1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        services sp = new services() ;
        zone.getItems().addAll(sp.LoadNameFromeZone());
    
         try{
            list.clear();
            //services sp = new services();
            //for (List<String> e: sp.afficherLocation()){
            //liste3.getItems().add(e);
            
            
           
                Column1.setCellValueFactory(new PropertyValueFactory<Location, Integer>("zone"));
                column2.setCellValueFactory(new PropertyValueFactory<Location, Integer>("velo"));
                Colomn3.setCellValueFactory(new PropertyValueFactory<Location, Date>("dateDebut"));
                colomn4.setCellValueFactory(new PropertyValueFactory<Location, Date>("dateFin"));
                 
                refreshTable(sp.afficherLocation());
                
         
            
   
            
             
            //
                //table1.getItems().add(sp.afficherLocation());
                
            //}
           
           /* List <Location> le=sp.afficherLocation();
             int  a= le.get(i).getZone();
            int b= le.get(i).getVelo();
             System.out.println("erreur ");
             Date s =le.get(i).getDateDebut();
            Date c =le.get(i).getDateFin();
            list.addAll(a,b,s,c);
            System.out.println(list);
            
            i++;*/
        }
        catch(Exception ex){
            System.out.println("erreur load");
        }
        // TODO
    }    

    
     private void refreshTable(List<Location> a) {
        ObservableList<Location> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(a);
        table1.setItems(obs);
        FilteredList<Location> filteredData = new FilteredList<>(obs, b -> true);
       
       SortedList<Location> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table1.comparatorProperty());
        table1.setItems(sortedData);
//        System.out.println(obs);

    }

    @FXML
    private void addlocation(ActionEvent event) {
        Zone z = new Zone();
        Velo v = new Velo();
        Location l = new Location();
        services SE=new services();
        int i = 0;
    
         java.sql.Date date1 = java.sql.Date.valueOf(datedebut.getValue());
         java.sql.Date date2 = java.sql.Date.valueOf(datefin.getValue());
         
         
         
         
          if (date1.after(date2)) {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("date fin doit etre superieur a date debut!!");
            alert.showAndWait(); 
          }
          
         if ((0 == SE.NombreStock(zone.getValue().toString()))){
             
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fin de stock!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("Stock des vélos dans cette zone est épuisé !!");
            alert.showAndWait(); 
         }
        
             if ((0 == SE.DisponibilityVelo(velo.getValue().toString()))){
                 
                 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vélo Non Disponible!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("Vélo déjà loué choisisez une autre !!");
            alert.showAndWait(); 
                 
             }
          else{
                 
       /* z.SetName(zone.getValue().toString());
        v.SetName(velo.getValue().toString());
        l.setDateDebut(java.sql.Date.valueOf(datedebut.getValue()));
        l.setDateFin(java.sql.Date.valueOf(datefin.getValue()));
        */
       
        SE.AddLocation(SE.GetIdZone(zone.getValue().toString()),SE.GetIdVelo(velo.getValue().toString()), java.sql.Date.valueOf(datedebut.getValue()),java.sql.Date.valueOf(datefin.getValue()));
        
        SE.RemoveVeloFromStock(zone.getValue().toString());
        System.out.println("kkkk");
        SE.DisplayAVelo(velo.getValue().toString());
        
         }
    }
         

    @FXML
    private void addvelo(ActionEvent event) {
        
        Zone z = new Zone();
        Velo v = new Velo();
        services sp = new services() ;
            //velo.getItems().addAll(sp.LoadNameFromeVelo());
       
    }
    
}
