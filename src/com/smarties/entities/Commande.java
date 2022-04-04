/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. l
 */
package com.smarties.entities;

/**
 *
 * @author user
 */
public class Commande {

    int id;
    int id_user;
    int id_produit;
    int nb_produits;
    

    public Commande() {
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

    public void setNbProduits(int nb_produits) {
        this.nb_produits = nb_produits;
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

    public int getNbProduits() {
        return nb_produits;
    }

}
