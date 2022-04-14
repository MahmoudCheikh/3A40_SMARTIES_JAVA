/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiUsersController implements Initializable {

    @FXML
    private TextField txtbackusermail;
    @FXML
    private TextField txtbackuserpassword;
    @FXML
    private TextField txtbackusernom;
    @FXML
    private TextField txtbackuserprenom;
    @FXML
    private TextField txtbackuseradresse;
    @FXML
    private TextField txtbackusertoher1;
    @FXML
    private TextField txtbackuserother2;
    @FXML
    private TextField txtbackuserid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }
    
}
