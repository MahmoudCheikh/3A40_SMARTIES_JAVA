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
public class Stock {

    int id;
    String libelle;
    int prix;
    int quantite;
    int IdProduit;
    String disponibilite;

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", quantite=" + quantite + ", IdProduit=" + IdProduit + ", disponibilite=" + disponibilite + '}';
    }

    public int getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(int IdProduit) {
        this.IdProduit = IdProduit;
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Stock(int id, String libelle, int prix, int quantite, int IdProduit, String disponibilite) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.IdProduit = IdProduit;
        this.disponibilite = disponibilite;
    }

    public Stock(String libelle, int prix, int quantite, int IdProduit, String disponibilite) {
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.IdProduit = IdProduit;
        this.disponibilite = disponibilite;
    }

    public Stock() {
    }

}
