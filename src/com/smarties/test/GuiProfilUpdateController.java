/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.services.UsersService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiProfilUpdateController implements Initializable {

    UsersService usersService = new UsersService();

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button modifier;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNom.setText(Smarties.user.getNom());
        txtPrenom.setText(Smarties.user.getPrenom());
        txtAdresse.setText(Smarties.user.getAdresse());

    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtAdresse.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if ((!(Pattern.matches("[A-Za-z0-9]*", txtNom.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9]*", txtPrenom.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9]*", txtAdresse.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9]*", txtPassword.getText())))) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez respecter la forme requise!");
                alert.showAndWait();
            } else {
                if (txtPassword.getText().equals(Smarties.user.getPassword())) {
                    Smarties.user.setAdresse(txtAdresse.getText());
                    Smarties.user.setNom(txtNom.getText());
                    Smarties.user.setPrenom(txtPrenom.getText());

                    usersService.updateData(Smarties.user);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiMonProfil.fxml"));
                AnchorPane vbox = loader.load();
                ap.getChildren().setAll(vbox);
            }
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiMonProfil.fxml"));
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }

}
