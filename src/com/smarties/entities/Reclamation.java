/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

import com.smarties.services.UsersService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Reclamation {
    
    UsersService usersService = new UsersService();
    
     int id;
     int id_user_id ;
     String description ;
     LocalDate date ;
     String objet;

    @Override
    public String toString() {
            Users user = new Users();
        try {
            user = usersService.getById(this.getId_user_id());
        } catch (SQLException ex) {
            Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  "" + "\nId Reclamation        =     " + id + "\nNom Client =     " + user.getNom() + "\nPrenom Client =     " + user.getPrenom()+  "\nDescription         =     " + description + "\nDate         =     " + date + "\nObjet         =     " + objet ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user_id() {
        return id_user_id;
    }

    public void setId_user_id(int id_user_id) {
        this.id_user_id = id_user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Reclamation(int id, int id_user_id, String description, LocalDate date, String objet) {
        this.id = id;
        this.id_user_id = id_user_id;
        this.description = description;
        this.date = date;
        this.objet = objet;
    }

 

    public Reclamation (){
        
    }
}