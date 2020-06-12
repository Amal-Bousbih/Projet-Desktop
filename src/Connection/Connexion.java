/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author amalb
 */
public class Connexion {
    
    

    private final String URL = "jdbc:mysql://localhost:3306/velo";
    private final String LOGIN = "root";
    private final String PASSWORD = "";
    public static Connexion instance;
    public Connection cnx;
    
    private Connexion() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
       
    }
    
     public Connection getCnx() {
        return cnx;
    }

    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

   
}
