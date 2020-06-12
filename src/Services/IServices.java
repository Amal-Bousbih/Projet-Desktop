/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entites.Location;
import Entites.User;

import Entites.Velo;
import Entites.Zone;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author amalb
 */
public interface IServices {
    
    public void AddVeloToStock (int ZoneID, String Nom, Date date, String Type, String disp, Double prix);
    public void RemoveVeloFromStock(String NomZone);
    public void DisplayAVelo (String NomVelo);
    public List<Zone> afficherZone();
    public List<Velo> afficherVelo();
    public void AddZone (String Nom, int Stock);
    public void RemoveZone (Zone z);
    public void DisplayZone (Zone z);
    public List<Location> afficherLocation();
    public void AddLocation (int zone , int velo , Date datedebut ,Date datefin);
    public List<String> LoadNameFromeZone();
    public List<String> LoadNameFromeVelo(String Nom);
    public int NombreStock(String nomZone);
    public int DisponibilityVelo (String NomVelo );
    public int GetIdZone(String NomZone);
    public int GetIdVelo(String NomVelo);
    public int GetIdZone2(String NomZone);
    public Location RechercheLocation(String rech);
    public long GetPriceOfLocation(String NomVelo, Date DateDeb, Date DateFin);
    public void deletezone(Zone z);
    public ArrayList<String> GetNomZone();
    public int GetStockZone(String nom);
    public void updateZone( int ID, String Nom, int Stock);
    public void deleteVelo(Velo v) ;
    public void updateVelo(int id, int zone , String Nom, Date debutservice, String type, Boolean disponible, Double prix);
    public boolean loginInterface(String username, String password);
     public Zone getSelectedRowFromGrid(int ID, String Nom, int Stock);
   public Velo getSelectedRowFromGrid2(int id, int zone , String Nom, Date debutservice, String type, Boolean disponible, Double prix);
   public int getId2(String s);
   public String getNomZone(int id);
   public boolean CheckRememberState(String username);
   public void SaveUsername(String username);
   public User RechercheUser(String rech);
    public int GetStock();
    public List<String> LoadUsername();
    public Double GetPriceOfVelo(String s);
    public boolean CheckUserName(String s);
    public boolean CheckPassword(String username , String password);
    public String getpassword(String s);
    public boolean Usersaved(String s);
}
