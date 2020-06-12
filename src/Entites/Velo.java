/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;

/**
 *
 * @author amalb
 */
public class Velo {
    
    int idz;
    int zone; 
    String nom;
    Date debutservice;
    String type;
    Boolean disponible;
    Double prix;
   
  

    public Velo() {
       
    }

    public Velo(int idz, int zone, String nom, Date debutservice, String type, Boolean disponible, Double prix) {
        this.idz = idz;
        this.zone = zone;
        this.nom = nom;
        this.debutservice = debutservice;
        this.type = type;
        this.disponible = disponible;
        this.prix = prix;
    }

    public Velo(int zone, String nom, Date debutservice, String type, Boolean disponible, Double prix) {
        this.zone = zone;
        this.nom = nom;
        this.debutservice = debutservice;
        this.type = type;
        this.disponible = disponible;
        this.prix = prix;
    }

    public int getIdz() {
        return idz;
    }

    public int getZone() {
        return zone;
    }

    public String getNom() {
        return nom;
    }

    public Date getDebutservice() {
        return debutservice;
    }

    public String getType() {
        return type;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public Double getPrix() {
        return prix;
    }

    public void setIdz(int idz) {
        this.idz = idz;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDebutservice(Date debutservice) {
        this.debutservice = debutservice;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Velo{" + "idz=" + idz + ", zone=" + zone + ", nom=" + nom + ", debutservice=" + debutservice + ", type=" + type + ", disponible=" + disponible + ", prix=" + prix + '}';
    }

  
}
