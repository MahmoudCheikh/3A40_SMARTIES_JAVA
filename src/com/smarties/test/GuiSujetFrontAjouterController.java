/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Sujet;
import com.smarties.services.SujetService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiSujetFrontAjouterController implements Initializable {

    SujetService sujetService = new SujetService();

    @FXML
    private TextArea txtContenu;
    @FXML
    private Button ajouter;
    @FXML
    private Label txtError;
    @FXML
    private TextField txtTitre;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (txtContenu.getText().isEmpty() || txtTitre.getText().isEmpty()) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if ((!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtContenu.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtTitre.getText())))) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez respecter la forme requise!");
                alert.showAndWait();
            } else {
                String titre = txtTitre.getText();
                String contenu = txtContenu.getText();
                Sujet sujet = new Sujet();
                sujet.setTitre(titre);
                sujet.setContenu(contenu);
                sujet.setUserId(Smarties.user.getId());
                sujet.setNbReponses(0);
                sujet.setNbVues(0);
                sujet.setDate(LocalDate.now());
                sujetService.ajouterSujet(sujet);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiForumFront.fxml"));
                AnchorPane vbox = loader.load();
                ap.getChildren().setAll(vbox);
            }
        }

    }

}
