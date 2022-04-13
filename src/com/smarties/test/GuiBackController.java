/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiBackController implements Initializable {

    @FXML
    private AnchorPane an1;
    @FXML       
    private AnchorPane an2;
    @FXML
    private BorderPane border1;
    @FXML
    private Button btnproduits;
    @FXML
    private Button btnlocation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
         AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("GuiEvenement.fxml"));
        an2.getChildren().setAll(xx);
    }

    @FXML
    private void sujet(ActionEvent event) throws IOException {
         AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("GuiSujet.fxml"));
        an2.getChildren().setAll(xx);
        
       
    }

    @FXML
    private void produit(ActionEvent event) {
    }

    @FXML
    private void locations(ActionEvent event) {
    }

}
