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
public class Abonnement {
    private int id;
    private int id_user_id;
    private String type;
    private String dateD;
     private String dateF;
     private int prix;

    public int getId() {
        return id;
    }

    public int getId_user_id() {
        return id_user_id;
    }

    public String getType() {
        return type;
    }

    public String getDateD() {
        return dateD;
    }

    public String getDateF() {
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

    public void setDateD(String dateD) {
        this.dateD = dateD;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Abonnement(int id, int id_user_id, String type, String dateD, String dateF, int prix) {
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
