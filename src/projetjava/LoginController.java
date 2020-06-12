/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import Entites.User;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class LoginController implements Initializable {

    @FXML
    private Label outputlogin;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btnsignin;
    @FXML
    private TextField txtmail;
    @FXML
    private CheckBox remember;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onlogin(ActionEvent event) {
        
         String username = txtmail.getText();
        String mdpP = txtpassword.getText();

        services s = new services();
        User u = new User();

        /*if (s.loginInterface(username, mdpP) == false) {
            JOptionPane.showMessageDialog(null, "Username/mot de passe incorrect!");
        }*/
        if(!s.CheckUserName(username))
        {
           JOptionPane.showMessageDialog(null, "Username is incorrect!"); 
        }
        else
        {
            if(!s.CheckPassword(username,mdpP))
            {
                JOptionPane.showMessageDialog(null, "Password is incorrect!");
            }
        }
        
        if(remember.isSelected())
        {
            if (s.CheckRememberState(username))
            {
                JOptionPane.showMessageDialog(null, "Username is already saved !");
            }
            else 
            {
                s.SaveUsername(username);
                JOptionPane.showMessageDialog(null, "Username has been saved !");
            }
        }
         
        System.out.println(s.loginInterface(username, mdpP));
        JOptionPane.showMessageDialog(null, "You are connected !");
            System.out.println("Vous êtes connecté !");
     }

    @FXML
    private void passwordclicked(MouseEvent event) {
        services s = new services();
        if(s.Usersaved(txtmail.getText()))
        {
            txtpassword.setText(s.getpassword(txtmail.getText()));   
        }
       
    }
    
}
