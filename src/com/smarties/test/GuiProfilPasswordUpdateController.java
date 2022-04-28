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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiProfilPasswordUpdateController implements Initializable {

    UsersService usersService = new UsersService();

    @FXML
    private PasswordField txtOld;
    @FXML
    private PasswordField txtNew;
    @FXML
    private Button modifier;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (txtOld.getText().isEmpty() || txtNew.getText().isEmpty()) {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else {
                if (txtOld.getText().equals(Smarties.user.getPassword())) {
                    usersService.updatePassword(Smarties.user, txtNew.getText());
                    Smarties.user.setPassword(txtNew.getText());
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
