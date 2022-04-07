/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. l
 */
package com.smarties.entities;

import java.time.LocalDate;

/**
 *
 * @author user
 */
public class Achat {

    int id;
    int id_user;
    int id_produit;
    LocalDate date;
    String nom_client; 
    int numero_client; 
    

    public Achat() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    public void setIdProduit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setNomClient(String nom_client) {
        this.nom_client = nom_client;
    }
    
      public void setNumeroClient(int numero_client) {
        this.numero_client = numero_client;
    }


    public int getId() {
        return id;
    }

    public int getIdUser() {
        return id_user;
    }

    public int getIdProduit() {
        return id_produit;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNomClient() {
        return nom_client;
    }
    
    public int getNumeroClient(){
    
    return numero_client; 
        }
}