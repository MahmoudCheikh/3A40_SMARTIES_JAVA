/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Users;
import com.smarties.services.UsersService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RegisterController implements Initializable {

    UsersService us = new UsersService();

    @FXML
    private TextField txtRegisterCaptcha;
    @FXML
    private TextField txtRegisterMail;
    @FXML
    private TextField txtRegisterPass;
    @FXML
    private TextField txtRegisterNom;
    @FXML
    private TextField txtRegisterPrenom;
    @FXML
    private TextField txtRegisterAdresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void register(ActionEvent event) {
        Users user = new Users();
        user.setNom(txtRegisterNom.getText());
        user.setPrenom(txtRegisterPrenom.getText());
        user.setEmail(txtRegisterMail.getText());
        user.setPassword(txtRegisterPass.getText());
        user.setAdresse(txtRegisterAdresse.getText());
        us.register(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            txtRegisterAdresse.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            txtRegisterAdresse.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
