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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    UsersService us = new UsersService();

    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtpass;
    @FXML
    private Button btnLogin;
    @FXML
    private Text txterror;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnConfirmer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        String mail = txtemail.getText();
        String pass = txtpass.getText();
        Users user = new Users();
        user.setEmail(mail);
        user.setPassword(pass);
        int test = us.login(user);
        if (test == 1) {

            user = us.getOne(mail);
            Smarties.user = user;
            if (Smarties.user.getId() == 45) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiBack.fxml"));
                try {
                    Parent root = loader.load();
                    txtemail.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiFront.fxml"));
                try {
                    Parent root = loader.load();
                    txtemail.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } else {
            txterror.setText("Verifiez les champs");
        }
    }

    @FXML
    private void resetpass(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resetpass.fxml"));
        try {
            Parent root = loader.load();
            txtemail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void register(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        try {
            Parent root = loader.load();
            txtemail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void confirmer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmer.fxml"));
        try {
            Parent root = loader.load();
            txtemail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void resetpasscode(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resetpasscode.fxml"));
        try {
            Parent root = loader.load();
            txtemail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
