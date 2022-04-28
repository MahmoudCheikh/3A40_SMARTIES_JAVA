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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.captcha.Captcha;

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
    @FXML
    private ImageView captchaIV;
    Captcha captcha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        captcha = genCaptcha();
    }

    public Captcha genCaptcha() {
        Captcha captcha = new Captcha.Builder(200, 50)
                .addText()
                .addBackground()
                .addNoise()
                .gimp()
                .addBorder()
                .build();

        Image image = SwingFXUtils.toFXImage(captcha.getImage(), null);

        captchaIV.setImage(image);

        return captcha;
    }

    @FXML
    private void register(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (!captcha.isCorrect(txtRegisterCaptcha.getText())) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Success");
                alert.setContentText("Captcha incorrect !");
                alert.show();
                captcha = genCaptcha();
                txtRegisterCaptcha.setText("");
            } else if (txtRegisterCaptcha.getText().isEmpty()
                    || txtRegisterMail.getText().isEmpty()
                    || txtRegisterPass.getText().isEmpty()
                    || txtRegisterNom.getText().isEmpty()
                    || txtRegisterPrenom.getText().isEmpty()
                    || txtRegisterAdresse.getText().isEmpty()) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if ((!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtRegisterCaptcha.getText())))
                    || (!(Pattern.matches("^(.+)@(.+)$", txtRegisterMail.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtRegisterPass.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtRegisterNom.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtRegisterPrenom.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtRegisterAdresse.getText())))) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez respecter la forme requise!");
                alert.showAndWait();
            } else {
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
            txtRegisterAdresse.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

}
