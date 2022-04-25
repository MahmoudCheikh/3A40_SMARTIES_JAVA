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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ahmed Elmoez
 */
public class ModelCommandeController implements Initializable {

    @FXML
    private Label txtIdComm;
    @FXML
    private Label txtNbrProd;
    @FXML
    private Label txtPrixProd;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Label txtProd;
    @FXML
    private Button achat;
    @FXML
    private Button act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void suppr(ActionEvent event) {
        
    }

    @FXML
    private void modi(ActionEvent event) {
    }

    @FXML
    private void achat(ActionEvent event) {
    }

    @FXML
    private void actu(ActionEvent event) {
    }
    
}
