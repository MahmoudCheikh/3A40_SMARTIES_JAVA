/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Maintenance {
    
     int id;
     int id_produit_id;
     int relation_id;
     int reclamation_id;
     LocalDate date_debut ;
     LocalDate date_fin ;  
     String adresse;
     String etat;
     String description;

    @Override
    public String toString() {
        return "" + "\nId Client           =     " + id + "\nId Produit        =     " + id_produit_id + "\nId Relation       =     " + relation_id + "\nId Reclamation         =     " + reclamation_id + "\nDate Debut       =     " + date_debut + "\nDate Fin         =     " + date_fin + "\nAdresse         =     " + adresse + "\nEtat         =     " + etat + "\nDescription      =     " + description ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit_id() {
        return id_produit_id;
    }

    public void setId_produit_id(int id_produit_id) {
        this.id_produit_id = id_produit_id;
    }

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public Maintenance(int id, int id_produit_id, int relation_id, int reclamation_id, LocalDate date_debut, LocalDate date_fin, String adresse, String etat, String description) {
        this.id = id;
        this.id_produit_id = id_produit_id;
        this.relation_id = relation_id;
        this.reclamation_id = reclamation_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.adresse = adresse;
        this.etat = etat;
        this.description = description;

    }

    public Maintenance(){
        
    }

}
