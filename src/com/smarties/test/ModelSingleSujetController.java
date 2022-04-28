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
 * @author user
 */
public class ModelSingleSujetController implements Initializable {

    @FXML
    private Label txtTitre;
    @FXML
    private Label txtContent;
    @FXML
    private Label txtDate;
    @FXML
    private Label txtUser;
    @FXML
    private Button afficher;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void afficher(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

}
