/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import Entites.Location;
import SererviceApp.services;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.net.URL;
import static java.rmi.Naming.list;
import java.sql.Date;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ShowLocationController implements Initializable {
    ObservableList list= FXCollections.observableArrayList();
 private final ObservableList<Location> dataList = FXCollections.observableArrayList();

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
     @FXML
    private TextField searchbar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        services sp = new services() ;
        
    
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
        System.out.println(obs);

    }
        // TODO
}    
    

