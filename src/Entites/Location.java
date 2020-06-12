/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.time.temporal.Temporal;
import java.time.Duration;
/**
 *
 * @author amalb
 */
public class Location {
    
    int ID;
    int  zone;
    int velo;
    Date dateDebut;
    Date dateFin;
    
     public Location( int zone, int velo, Date dateDebut, Date dateFin)
    {
        
        this.zone = zone;
        this.velo = velo;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
     
    public Location(int ID, int zone, int velo, Date dateDebut, Date dateFin)
    {
        this.ID = ID;
        this.zone = zone;
        this.velo = velo;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
   

    public Location() {
        
    }

    public int getID() {
        return ID;
    }
    
public int getZone() {
        return zone;
    }

 public int getVelo() {
        return velo;
    }

  public Date getDateDebut() {
        return dateDebut;
    }

  public Date getDateFin() {
        return dateFin;
    }
  
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setZone(int zone) {
        this.zone = zone;
    }

    public void setVelo(int velo) {
        this.velo = velo;
    }

   
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public String toString()
    {
        return "ID: "+ID+", Zone: "+zone+", Velo: "+velo+"DateDebut" + dateDebut + ", dateFin: "+ dateFin +"Disponibility" ;
    }

    
}
