/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.entities;

/**
 *
 * @author Lenovo
 */
public class Participation {
    int id; 
    int id_user; 
    int id_event; 
    // les getter and setter 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }
    //les constructeuuurs 

    public Participation() {
    }

    public Participation(int id, int id_user, int id_event) {
        this.id = id;
        this.id_user = id_user;
        this.id_event = id_event;
    }
    //tostring

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", id_user=" + id_user + ", id_event=" + id_event + '}';
    }
    
}
