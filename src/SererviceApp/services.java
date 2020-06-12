/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SererviceApp;

import Entites.Location;
import Entites.Velo;
import Entites.Zone;
import Services.IServices;
import Connection.Connexion;
import Entites.User;
import java.io.IOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javax.sql.DataSource;
import org.mindrot.jbcrypt.BCrypt;
import projetjava.ShowZoneController;

/**
 *
 * @author amalb
 */
public class services implements IServices {
     
    Connection cnx;
    Zone z ;
    Velo v ;
    Location l ;
    public services() {
     
    cnx = Connexion.getInstance().getCnx();
}
    public services(Zone z , Velo v){
        cnx = Connexion.getInstance().getCnx();
        this.z = z;
        this.v = v;
    }

    public services(Zone z) {
       
        cnx = Connexion.getInstance().getCnx();
        this.z = z;     
    }
    
    public services (Zone z ,Velo v , Location l){
        cnx = Connexion.getInstance().getCnx();
        this.z = z;
        this.v = v;
        this.l = l ;
    }
    @Override
    public void AddVeloToStock(int ZoneID, String Nom, Date date, String Type, String disp, Double prix ) {
         
        try {
            String requete = "INSERT INTO `velo`(`idz`, `zone`, `nom`, `debutservice`, `type`, `disponible`, `prix`)  VALUES (NULL,'"+ZoneID+"','"+Nom+"','"+date+"','"+Type+"','"+disp+"','"+prix+"')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            /*pst.setInt(1, z.GetID());
            pst.setString(2, v.GetName());
            pst.setDate(3, v.getDateDebutService());
            pst.setString(4, v.GetType());
            pst.setBoolean(5, v.GetDisponibility());
            pst.setDouble(6, v.GetPrice()); */
            pst.executeUpdate();
            System.out.println("Vélo ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void RemoveVeloFromStock(String NomZone) {
        
        try {
            String requete = "UPDATE zone SET stcok = stcok-1 WHERE zone.nom = '"+NomZone+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.executeUpdate();
            System.out.println("Velo supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   

    @Override
    public List<Zone> afficherZone() {
        ArrayList<Zone> list = new ArrayList<>();
        try {
            String requete = "SELECT `nom`,`stcok` FROM zone";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Zone z = new Zone();
              //z.setId(rs.getInt("id"));
              z.setNom(rs.getString("nom"));
              z.setStcok(rs.getInt("stcok"));
             
              list.add(z);
      
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
  
}   
    
    

    @Override
    public List<Velo> afficherVelo() {
           ArrayList<Velo> list = new ArrayList<>();
        
        try{
        String requete = "SELECT `idz`, `zone`, `nom`, `debutservice`, `type`, `disponible`, `prix` FROM velo ";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Velo v = new Velo();
            v.setIdz(rs.getInt(1));
            v.setZone(rs.getInt(2));
            v.setNom(rs.getString(3));
            v.setDebutservice(rs.getDate(4));
            v.setType(rs.getString(5));
            v.setDisponible(rs.getBoolean(6));
            v.setPrix(rs.getDouble(7));
            list.add(v);
            }
       }
    
        catch(SQLException ex) {
           System.err.println(ex.getMessage());   }
        
        return list;
        
    }
    @Override
    public void AddZone(String Nom, int Stock) {
       
        try {
        String requete= "INSERT INTO `zone`(`nom`, `stcok`) VALUES ('"+Nom+"','"+Stock+"')";
        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.executeUpdate();
        System.out.println("Zone ajoutée !");
   
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void RemoveZone(Zone z) {
        
        try {
            String requete = "DELETE FROM `zone` WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, z.getId());
            pst.executeUpdate();
            System.out.println("Zone supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public void DisplayZone(Zone z) {
       try {
            String requete = "UPDATE `zone` SET `nom`=? ,`stcok`=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, z.getId());
            pst.setString(1, z.getNom());
            pst.setInt(2,z.getStcok());
            pst.executeUpdate();
            System.out.println("Velo modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   
    }

    @Override
    public List<Location> afficherLocation() {
     ArrayList<Location> list = new ArrayList<>();
        try {
            String requete = "SELECT `zone`,`velo`, `datedebut`,`datefin` FROM location";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Location l = new Location();
                l.setZone(rs.getInt(1));
                l.setVelo(rs.getInt(2));
                l.setDateDebut(rs.getDate(3));
                l.setDateFin(rs.getDate(4));
                list.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    @Override
    public void AddLocation(int zone , int velo , Date datedebut ,Date datefin) {
         try {
            String requete = "INSERT INTO `location`(`id` ,`zone`, `velo`, `datedebut`, `datefin`) VALUES (NULL , '"+zone+"','"+velo+"','"+datedebut+"','"+datefin+"')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            /*pst.setInt(1,z.GetID());
            pst.setInt(2, v.GetID());
            pst.setDate(3, l.getDateDebut());
            pst.setString(2, v.GetName());
            pst.setDate(3, v.getDateDebutService());
            pst.setString(4, v.GetType());
            pst.setBoolean(5, v.GetDisponibility());
            pst.setDouble(6, v.GetPrice());*/
            pst.executeUpdate(); 
            System.out.println("Formulaire ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void DisplayAVelo(String NomVelo) {
        
        try {
            String requete = "UPDATE  `velo` SET `disponible` = '0' WHERE velo.nom = '"+NomVelo+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Velo modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<String> LoadNameFromeZone() {
        List<String> list =new ArrayList<>();
        try {
            String requete = "SELECT nom  FROM zone";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1).toString());
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return  list;
    }

    @Override
     public List<String> LoadNameFromeVelo(String Nom) {
        List<String> list =new ArrayList<>();
        try {
            String requete = "SELECT v.nom FROM velo v , zone z WHERE v.zone = z.id AND z.nom = '"+Nom+"' AND v.disponible = '1'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1).toString());
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return  list;
    }

    @Override
    public int NombreStock(String nomZone) {
        int x = 0 ;
        try {
            String requete = "SELECT stcok  FROM zone WHERE nom = '"+nomZone+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               x=rs.getInt(1);}
            
              return x ; 

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
    }

    @Override
    public int DisponibilityVelo(String NomVelo) {
        int x = 0 ;
        try {
            String requete = "SELECT disponible  FROM velo , zone WHERE velo.zone = zone.id and  velo.nom = '"+NomVelo+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               x=rs.getInt(1);}
            
              return x ; 

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
        
    }

    @Override
    public int GetIdZone(String NomZone) {
       int x = 0 ;
        try {
            String requete = "SELECT zone  FROM location , zone WHERE location.zone = zone.id AND zone.nom = '"+NomZone+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               x=rs.getInt(1);}
            
              return x ; 

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;  
    }

    @Override
    public int GetIdVelo(String NomVelo) {
        
         int x = 0 ;
        try {
            String requete = "SELECT velo  FROM location , velo  WHERE location.velo = velo.idz AND velo.nom ='"+NomVelo+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               x=rs.getInt(1);}
            
              return x ; 

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
        
    }

    @Override
 public int GetIdZone2(String NomZone){
      int x = 0 ;
        try {
            String requete = "SELECT id FROM zone  WHERE  zone.nom ='"+NomZone+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               x=rs.getInt(1);}
            
              return x ; 

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
     
 }

    @Override
    public Location RechercheLocation(String rech) {
        try {
            String requete = "SELECT * FROM location WHERE velo=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, rech);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Location l = new Location();
                l.setID(rs.getInt("id"));                   //Soit par label soit par indice 
               l.setZone(rs.getInt("zone"));
                l.setVelo(rs.getInt("velo"));
                l.setDateDebut(rs.getDate("datedebut"));
                l.setDateFin(rs.getDate("datefin"));
                return l;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Location l = null;
        return l;
    }

    @Override
    public long GetPriceOfLocation(String NomVelo, Date DateDeb, Date DateFin) {
        
        long Jours = DAYS.between(LocalDate.parse(DateDeb.toString()), LocalDate.parse(DateFin.toString()));
        System.out.println(Jours);
         try {
            String requete = "SELECT `prix` FROM velo WHERE velo.nom = '"+NomVelo+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
           
                Jours = Jours * Double.valueOf(rs.getDouble("prix")).longValue();   
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(Jours);
       return Jours;
    }
   
    @Override
    public void deletezone(Zone z) {
       try {
            String requete = "DELETE FROM `zone` WHERE nom=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, z.getNom());

            pst.executeUpdate();
            System.out.println("Zone supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    
    
    @Override
     public void updateZone( int ID, String Nom, int Stock) {
        try {
            String requete = "UPDATE `zone` SET `nom`='"+Nom+"',`stcok`="+Stock+" WHERE id = "+ID+"";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Zone modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
     
     @Override
     public ArrayList<String> GetNomZone(){
         
        ArrayList<String> list = new ArrayList<>();
        try {
            String requete = "SELECT `nom` FROM zone";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
             
              list.add(rs.getString(1));
      
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
  
}   
      
     
    @Override
    public int GetStockZone(String nom){
       int x = 0;
        try {
            String requete = "SELECT `stcok` FROM zone WHERE nom = '"+nom+"' ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               x= rs.getInt(1);
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
}
    
    @Override
     public void deleteVelo(Velo v) {
       try {
            String requete = "DELETE FROM velo WHERE idz=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setInt(1, v.getIdz());

            pst.executeUpdate();
            System.out.println("Velo supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
     
    @Override
      public void updateVelo(int id,int zone , String Nom, Date debutservice, String type, Boolean disponible, Double prix) {
        try {
            String requete = "UPDATE `velo` SET `zone`="+zone+",`nom`='"+Nom+"',`debutservice`='"+debutservice+"',`type`='"+type+"',`disponible`="+disponible+",`prix`="+prix+" WHERE idz = "+id+"";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("vélo modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
      
public static boolean checkPassword(String password_plaintext, String stored_hash)
    {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$"))
        {
           
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return password_verified;
    }
    
    @Override
  public boolean loginInterface(String username, String password) {
      
        try {
            String requete = "SELECT password FROM fos_user_table WHERE password=pwdencrypt(userEnteredValue)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.first() == false) {
                return false;
            }
            
            if(rs.getString(1).toUpperCase().equals(password) == false || password.equals("") ) {
                return false;
            };
     
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Can not issue")) {
                System.out.println("username/password invalid!");
            } else {
                System.out.println(ex.getMessage());
            }
        }
        return true;
    }
 
 public void getRemember(String username, String password) 
 {
     try {
            String requete = "SELECT password FROM fos_user_table WHERE password=pwdencrypt(userEnteredValue)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, username);
            pst.setBoolean(2, true);
            ResultSet rs = pst.executeQuery();
            User  u = new User();
           
            if(rs.getString(1).toUpperCase().equals(password) == true || !password.equals("") ) {
              pst.setBoolean(13, u.getRemeber());
            };
     
        } catch (SQLException ex) {
            
        }
        
 }
 
    @Override
 public Zone getSelectedRowFromGrid(int ID, String Nom, int Stock)
 {
     Zone z = new Zone();
     z.setId(ID);
     z.setNom(Nom);
     z.setStcok(Stock);
     return z; 
 }

public int getId(String s)
{
    int x = 0;
    try {
            String requete = "SELECT id FROM zone WHERE nom = '"+s+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               x = rs.getInt("id");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return x;
}

    @Override
 public Velo getSelectedRowFromGrid2(int id, int zone , String Nom, Date debutservice, String type, Boolean disponible, Double prix)
 {
     Velo v = new Velo();
     v.setIdz(id);
     v.setZone(zone);
     v.setNom(Nom);
     v.setDebutservice(debutservice);
     v.setType(type);
     v.setDisponible(disponible);
     v.setPrix(prix);
     return v; 
 }
    @Override
 public int getId2(String s)
{
    int x = 0;
    try {
            String requete = "SELECT idz FROM velo WHERE nom = '"+s+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               x = rs.getInt("idz");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return x;
}
 
 
    @Override
    public String getNomZone(int id)
{
    String ch = "";
        
        try{
        String requete = "SELECT z.nom FROM velo v, zone z WHERE v.idz = z.id AND v.idz = "+id+" ";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ch = rs.getString(0);
            }
       }
    
        catch(SQLException ex) {
           System.err.println(ex.getMessage());   }
        
        return ch;
}
    
 public User RechercheUser(String rech) {
         
        try {
            String requete = "SELECT username FROM fos_user_table WHERE username='"+rech+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);

            //pst.setString(1, rech);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                rs.getString(1);
              
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        User u = null;
        return u;
    }
 
    @Override
 public boolean CheckRememberState(String username)
 {
     boolean r = false;
      try{
        String requete = "SELECT remember From fos_user_table WHERE username = '"+username+"'";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            r = rs.getBoolean(1);
            
            }
        
       }
    
        catch(SQLException ex) {
           System.err.println(ex.getMessage());   }
      System.out.println(r +" Amal ");
      return r;
 }
 
public void SaveUsername(String username)
{
    try {
            String requete = "UPDATE `fos_user_table` SET remember = 1 WHERE username = '"+username+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("user sauvgarder !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

    @Override
 public int GetStock(){
       int x = 0;
        try {
            String requete = "SELECT `stcok` FROM zone ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               x= rs.getInt(1);
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;

 }
 
    @Override
    public List<String> LoadUsername() {
        List<String> list =new ArrayList<>();
        try {
            String requete = "SELECT  * FROM `fos_user_table` WHERE username = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("username").toString());
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return  list;
    }
       
    @Override
    public Double GetPriceOfVelo(String s)
{
    
    Double x = 0.0 ;
        try {
            String requete = "SELECT  prix FROM `velo` WHERE nom = '"+s+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                x = rs.getDouble(1);
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
}
@Override
public boolean CheckUserName(String s)
    {
        boolean b = false ;
        try {
            String requete = "SELECT username FROM `fos_user_table`";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               if(rs.getString(1).toUpperCase().equals(s.toUpperCase()))
               {
                   b = true ;
               }
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return b ;
    }
    
    @Override
    public boolean CheckPassword(String username , String password)
{
    boolean b = false ;
     try {
            String requete = "SELECT password  FROM `fos_user_table` WHERE username='"+username+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               if(rs.getString(1).equals(password))
               {
                   b = true ;
               }
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     return b;
}
    
    @Override
    public String getpassword(String s)
{
    String ch = "";
  try {
            String requete = "SELECT password  FROM `fos_user_table` WHERE username ='"+s+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               ch = rs.getString(1);
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
      return ch ;
}
    
public boolean Usersaved(String s)
{
    boolean b = false;
    
     try {
            String requete = "SELECT remember  FROM `fos_user_table` WHERE username ='"+s+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               if(rs.getBoolean(1))
               {
                   b = true ;
               }
            }
        }
        
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
     return b;
}

 }