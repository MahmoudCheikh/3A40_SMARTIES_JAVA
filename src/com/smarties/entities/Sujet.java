/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

import java.time.LocalDate;

/**
 *
 * @author user
 */
// LocalDate localDate = LocalDate.parse("2016-08-16");
// java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
// LocalDate localDate = sqlDate.toLocalDate();
public class Sujet {

    int id;
    int userId;
    int nbReponses;
    int nbVues;
    LocalDate date;
    String titre;
    String contenu;

    public Sujet() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getNbReponses() {
        return nbReponses;
    }

    public int getNbVues() {
        return nbVues;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNbReponses(int nbReponses) {
        this.nbReponses = nbReponses;
    }

    public void setNbVues(int nbVues) {
        this.nbVues = nbVues;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

}
