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
public class GuiSujetFrontUpdateController implements Initializable {

    static Sujet sujet;
    SujetService sujetService = new SujetService();

    @FXML
    private AnchorPane ap;
    @FXML
    private Label txtError;
    @FXML
    private TextArea txtContenu;
    @FXML
    private TextField txtTitre;
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtContenu.setText(sujet.getContenu());
        txtTitre.setText(sujet.getTitre());
    }

    @FXML
    private void update(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (txtContenu.getText().isEmpty() || txtTitre.getText().isEmpty()) {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if ((!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtTitre.getText())))
                    || (!(Pattern.matches("[A-Za-z0-9 _.,!?]*", txtContenu.getText())))) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez respecter la forme requise!");
                alert.showAndWait();
            } else {
                String titre = txtTitre.getText();
                String contenu = txtContenu.getText();
                Sujet s = new Sujet();
                s.setTitre(titre);
                s.setContenu(contenu);
                s.setUserId(Smarties.user.getId());
                s.setNbReponses(0);
                s.setNbVues(0);
                s.setDate(LocalDate.now());
                s.setId(this.sujet.getId());
                sujetService.modifierSujet(s);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiForumFront.fxml"));
                AnchorPane vbox = loader.load();
                ap.getChildren().setAll(vbox);
            }
        }

    }

}
