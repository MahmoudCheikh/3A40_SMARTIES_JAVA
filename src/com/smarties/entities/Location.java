/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

/**
 *
 * @author ASUS
 */
public class Location {
    
    private int id;
     private String Date;
     private String Heure;
    private  float duree;
    private int idAbonnement;
    private  int idUser;

    public int getId() {
        return id;
    }

    public String getDate() {
        return Date;
    }

    public String getHeure() {
        return Heure;
    }

    public float getDuree() {
        return duree;
    }

    public int getIdAbonnement() {
        return idAbonnement;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setHeure(String Heure) {
        this.Heure = Heure;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public void setIdAbonnement(int idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Location(int id, String Date, String Heure, float duree, int idAbonnement, int idUser) {
        this.id = id;
        this.Date = Date;
        this.Heure = Heure;
        this.duree = duree;
        this.idAbonnement = idAbonnement;
        this.idUser = idUser;
    }

    public Location() {
    }
    
    
    
}
