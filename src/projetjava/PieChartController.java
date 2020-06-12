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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class PieChartController implements Initializable {
    @FXML
    private PieChart pieChart;

    @FXML
    private Label status;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadData();
    }    
    
    private void loadData()
    {
        services s = new services();
        ObservableList<Data> list = FXCollections.observableArrayList(
           /* new PieChart.Data("Java", 50),    
            new PieChart.Data("c++", 20),
            new PieChart.Data("python", 30),
            new PieChart.Data("c#", 10),
            new PieChart.Data("c", 15)*/
               
            );
        ArrayList<String> liste = new ArrayList<String>();
        liste = s.GetNomZone();
        for(int i=0; i<liste.size(); i++)
        {
            list.add(new PieChart.Data(liste.get(i).toString(), s.GetStockZone(liste.get(i).toString())));
        }
        pieChart.setData(list);
        
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
 
                @Override
                public void handle(MouseEvent event) {
                    status.setText(String.valueOf(data.getPieValue()/(10.0/100)) + "%");
                    
                }
            });
        }
    }
   

}
