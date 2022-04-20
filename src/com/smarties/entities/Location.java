/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

// LocalDate localDate = LocalDate.parse("2016-08-16");
// java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
// LocalDate localDate = sqlDate.toLocalDate();
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class Location {

    private int id;
    private LocalDate Date;
    private String Heure;
    private float duree;
    private int idAbonnement;
    private int idUser;

      public String toString() {
        return "" + "\n id=" + id + "\n id user=" + idUser + " \n Date=" + Date + " \n Heure=" + Heure + " \n Duree=" + duree + " \n id Abonnement=" + idAbonnement ;
    }
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
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

    public void setDate(LocalDate Date) {
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

    public Location(int id, LocalDate Date, String Heure, float duree, int idAbonnement, int idUser) {
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
