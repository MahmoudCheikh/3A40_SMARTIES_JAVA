/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

/**
 *
 * @author PC
 */
public class Produit {

    int id;
    String libelle;
    String image;
    String description;
    float prix;
    String type;
    
    
    public String toString() {
        return "" + "Libelle         =     "+ libelle + "\nImage         =     "+ image + "\nDescription =     "+ description + "\nPrix             =     "+ prix + "\nType           =     "+ type + "";
    }
    


    //getters & setters
    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //constructeurs 
    public Produit(int id, String libelle, String image, String description, float prix, String type) {
        this.id = id;
        this.libelle = libelle;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

    public Produit(String libelle, String image, String description, float prix, String type) {
        this.libelle = libelle;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }


    public Produit() {
    }
}
