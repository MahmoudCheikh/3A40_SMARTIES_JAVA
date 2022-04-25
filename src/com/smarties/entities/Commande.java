/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. l
 */
package com.smarties.entities;

import com.smarties.services.ProduitService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Commande {

    ProduitService produitService = new ProduitService();

    int id;
    int id_user;
    int id_produit;
    int nb_produits;

    @Override
    public String toString() {
        Produit p;
        try {
            p = produitService.GetProdbyid(this.id_produit);
            return "Commande" + "\n Numero De Commande          :         " + id + "\n Identifiant D'Utilisateur        :        " + id_user + "\n Identifiant Du Produit        :        " + id_produit + "\n Quantite Du Produit       :        " + nb_produits +         "\nNom Du Produit          :              "+  p.getLibelle() +         "\nPrix Du ¨Produit             :       "  +    p.getPrix();

        } catch (SQLException ex) {
            Logger.getLogger(Commande.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

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
