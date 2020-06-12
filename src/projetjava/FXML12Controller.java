/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import Entites.Velo;
import Entites.Zone;
import SererviceApp.services;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class FXML12Controller implements Initializable {
ObservableList list= FXCollections.observableArrayList();
    @FXML
    private Button btn_shArea;
    @FXML
    private Button btn_addzone;
    
    
    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane pane;
    public int i=0;
    public int j=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        // TODO
    }    

    @FXML
    private void afficherZone(ActionEvent event) {
        Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("ShowZone.fxml"));

                
                pane.getChildren().add(nodes[i]);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

     @FXML
    void Chart(ActionEvent event) {
      
         Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("PieChart.fxml"));

                
                pane.getChildren().add(nodes[i]);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void Rental(ActionEvent event) {
        
        Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("showLocation.fxml"));

                
                pane.getChildren().add(nodes[i]);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


   

    @FXML
    private void shbike(ActionEvent event) {
         Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("showbike.fxml"));

                
                pane.getChildren().add(nodes[i]);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void ajouterVelo(ActionEvent event) {
       
        Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("ADDBike.fxml"));

                
                pane.getChildren().add(nodes[i]);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ajouterBike(ActionEvent event) {
        
         Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("ADDBike.fxml"));

                
                pane.getChildren().add(nodes[i]);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ADDArea(ActionEvent event) {
        Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("ADDZone.fxml"));

                
                pane.getChildren().add(nodes[i]);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
}
