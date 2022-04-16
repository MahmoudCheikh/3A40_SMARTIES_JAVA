/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;


import java.time.LocalDate;


/**
 *
 * @author Lenovo
 */
public class Evenement {

    int id;
    String nom;
   LocalDate Date_d;
   LocalDate Date_f;
    String lieu;
    String type;
    int nb_participants;
    int nb_places;
//****************les getters and setters*****************

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", Date_d=" + Date_d + ", Date_f=" + Date_f + ", lieu=" + lieu + ", type=" + type + ", nb_participants=" + nb_participants + ", nb_places=" + nb_places + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate_d() {
        return Date_d;
    }

    public void setDate_d(LocalDate Date_d) {
        this.Date_d = Date_d;
    }

    public LocalDate getDate_f() {
        return Date_f;
    }

    public void setDate_f(LocalDate Date_f) {
        this.Date_f = Date_f;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }
    //***********les constructeurs****************

    public Evenement(int id, String nom,LocalDate Date_d, LocalDate Date_f, String lieu, String type, int nb_participants, int nb_places) {
        this.id = id;
        this.nom = nom;
        this.Date_d = Date_d;
        this.Date_f = Date_f;
        this.lieu = lieu;
        this.type = type;
        this.nb_participants = nb_participants;
        this.nb_places = nb_places;
    }

    public Evenement() {
    }

  

}
