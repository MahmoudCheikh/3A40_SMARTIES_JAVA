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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiMonProfilController implements Initializable {

    @FXML
    private Label txtemail;
    @FXML
    private Label txtnom;
    @FXML
    private Label txtprenom;
    @FXML
    private Label txtadresse;
    @FXML
    private Button modifier;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtemail.setText(Smarties.user.getEmail());
        txtprenom.setText(Smarties.user.getPrenom());
        txtnom.setText(Smarties.user.getNom());
        txtadresse.setText(Smarties.user.getAdresse());
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiProfilUpdate.fxml"));
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }

    @FXML
    private void password(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiProfilPasswordUpdate.fxml"));
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }
    
}
