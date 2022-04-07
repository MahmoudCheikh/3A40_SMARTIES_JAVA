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
public class Message {

    int id;
    int idUser;
    int idSujet;
    LocalDate date;
    String contenu;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", idUser=" + idUser + ", idSujet=" + idSujet + ", date=" + date + ", contenu=" + contenu + '}';
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdSujet() {
        return idSujet;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdSujet(int idSujet) {
        this.idSujet = idSujet;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

}
