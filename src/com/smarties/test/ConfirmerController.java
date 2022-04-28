/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.services.UsersService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ConfirmerController implements Initializable {

    UsersService usersService = new UsersService();

    @FXML
    private TextField txtmail;
    @FXML
    private TextField txtcode;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void confirmer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (txtmail.getText().isEmpty() || txtcode.getText().isEmpty()) {
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (!(Pattern.matches("^(.+)@(.+)$", txtmail.getText()))) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez respecter la forme requise!");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0-9]*", txtcode.getText()))) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez respecter la forme requise!");
                alert.showAndWait();
            } else {
                usersService.confirmer(txtmail.getText(), Integer.parseInt(txtcode.getText()));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                try {
                    Parent root = loader.load();
                    txtcode.getScene().setRoot(root);
                } catch (IOException ex) {
                }
            }
        }

    }

    @FXML
    private void retour(ActionEvent event
    ) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            txtcode.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

}
