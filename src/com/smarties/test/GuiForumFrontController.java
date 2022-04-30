/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Sujet;
import com.smarties.entities.Users;
import com.smarties.services.SujetService;
import com.smarties.services.UsersService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiForumFrontController implements Initializable {

    SujetService sujetService = new SujetService();
    UsersService usersService = new UsersService();

    @FXML
    private VBox mainVbox;
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Sujet> listSujet = sujetService.afficherSujet();

        if (Smarties.user.getImage().equals("ban")) {
            btnajouter.setText("vous etes banni");
            btnajouter.setOnAction((event) -> {
            });
        }

        if (!listSujet.isEmpty()) {
            listSujet.forEach((sujet) -> {
                try {
                    mainVbox.getChildren().add(makeSujet(sujet));
                } catch (SQLException ex) {
                    Logger.getLogger(GuiForumFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } else {

        }
        scroll.setContent(mainVbox);
    }

    public Parent makeSujet(Sujet sujet) throws SQLException {
        Parent innerContainer = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelSingleSujet.fxml"));
            innerContainer = loader.load();

            Users user = usersService.getById(sujet.getUserId());

            ((Label) innerContainer.lookup("#txtDate")).setText("Posté le: " + sujet.getDate().toString());
            ((Label) innerContainer.lookup("#txtUser")).setText("User : " + user.getNom() + " " + user.getPrenom());
            ((Label) innerContainer.lookup("#txtTitre")).setText("Titre : " + sujet.getTitre());
            
           // ((Text) innerContainer.lookup("#txtVues")).setText("Titre : " + sujet.getTitre());
          //  ((Text) innerContainer.lookup("#txtTitre")).setText("Titre : " + sujet.getTitre());

            if (sujet.getContenu().length() > 20) {
                ((Label) innerContainer.lookup("#txtContent")).setText("Contenu : " + sujet.getContenu().substring(0, 20) + "...");
            } else {
                ((Label) innerContainer.lookup("#txtContent")).setText("Contenu : " + sujet.getContenu());
            }

            ((Button) innerContainer.lookup("#afficher")).setOnAction((event) -> {
                try {
                    afficherSujet(sujet);
                } catch (IOException ex) {
                }
            });
            if (Smarties.user.getId() == sujet.getUserId()) {
                if (Smarties.user.getImage().equals("ban")) {
                    ((Button) innerContainer.lookup("#modifier")).setText("banni");
                    ((Button) innerContainer.lookup("#supprimer")).setText("banni");

                } else {
                    ((Button) innerContainer.lookup("#modifier")).setOnAction((event) -> {
                        try {
                            modifierSujet(sujet);
                        } catch (IOException ex) {
                        }
                    });
                    ((Button) innerContainer.lookup("#supprimer")).setOnAction((event) -> {
                        try {
                            supprimerSujet(sujet);
                        } catch (IOException ex) {
                        }
                    });
                }

            } else {
                ((Button) innerContainer.lookup("#modifier")).setVisible(false);
                ((Button) innerContainer.lookup("#supprimer")).setVisible(false);
            }

        } catch (IOException ex) {
        }
        return innerContainer;

    }

    public void afficherSujet(Sujet sujet) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiSujetFront.fxml"));
        GuiSujetFrontController.sujet = sujet;
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }

    public void modifierSujet(Sujet sujet) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            GuiSujetFrontUpdateController.sujet = sujet;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiSujetFrontUpdate.fxml"));
            AnchorPane vbox = loader.load();
            ap.getChildren().setAll(vbox);
        }

    }

    public void supprimerSujet(Sujet sujet) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            sujetService.supprimerSujet(sujet.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiForumFront.fxml"));
            AnchorPane vbox = loader.load();
            ap.getChildren().setAll(vbox);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiSujetFrontAjouter.fxml"));
            AnchorPane vbox = loader.load();
            ap.getChildren().setAll(vbox);
        }
    }

}
