/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class Abonnement {

    private int id;
    private int id_user_id;
    private String type;
     LocalDate dateD;
     LocalDate dateF;
    private int prix;

    
    
     public String toString() {
        return "Abonnement{" + "id=" + id + ", id user=" + id_user_id + ", Date_d=" + dateD + ", Date_f=" + dateF + ", type=" + type + ", prix=" + prix + '}';
    }
    public int getId() {
        return id;
    }

    public int getId_user_id() {
        return id_user_id;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDateD() {
        return dateD;
    }

    public LocalDate getDateF() {
        return dateF;
    }

    public int getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user_id(int id_user_id) {
        this.id_user_id = id_user_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateD(LocalDate dateD) {
        this.dateD = dateD;
    }

    public void setDateF(LocalDate dateF) {
        this.dateF = dateF;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Abonnement(int id, int id_user_id, String type, LocalDate dateD, LocalDate dateF, int prix) {
        this.id = id;
        this.id_user_id = id_user_id;
        this.type = type;
        this.dateD = dateD;
        this.dateF = dateF;
        this.prix = prix;
    }

    public Abonnement() {
    }

}
