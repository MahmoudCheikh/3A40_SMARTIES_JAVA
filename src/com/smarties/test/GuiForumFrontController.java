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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiForumFrontController implements Initializable {

    SujetService sujetService = new SujetService();

    @FXML
    private VBox mainVbox;
    @FXML
    private ScrollPane scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Sujet> listSujet = sujetService.afficherSujet();

        if (!listSujet.isEmpty()) {
            for (Sujet sujet : listSujet) {
                mainVbox.getChildren().add(makeSujet(sujet));
            }
        } else {

        }
        scroll.setContent(mainVbox);
    }

    public Parent makeSujet(Sujet sujet) {
        Parent innerContainer = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelSingleSujet.fxml"));
            innerContainer = loader.load();

            //  HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Label) innerContainer.lookup("#txtDate")).setText("Posté le: " + sujet.getDate().toString());
            ((Label) innerContainer.lookup("#txtUser")).setText("User : " + sujet.getUserId());
            ((Label) innerContainer.lookup("#txtTitre")).setText("Titre : " + sujet.getTitre());
            ((Label) innerContainer.lookup("#txtContent")).setText("Contenu : " + sujet.getContenu());

            ((Button) innerContainer.lookup("#afficher")).setOnAction((event) -> {
                try {
                    afficherSujet(sujet);
                } catch (IOException ex) {
                    Logger.getLogger(GuiForumFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            if (Smarties.user.getId() == sujet.getUserId()) {
                ((Button) innerContainer.lookup("#modifier")).setOnAction((event) -> modifierSujet(sujet));
                ((Button) innerContainer.lookup("#supprimer")).setOnAction((event) -> supprimerSujet(sujet));

            } else {
                ((Button) innerContainer.lookup("#modifier")).setVisible(false);
                ((Button) innerContainer.lookup("#supprimer")).setVisible(false);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return innerContainer;

    }

    public void afficherSujet(Sujet sujet) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiSujetFront.fxml"));
        GuiSujetFrontController.sujet = sujet;
        VBox vbox = loader.load();
        mainVbox.getChildren().setAll(vbox);
    }

    public void modifierSujet(Sujet sujet) {
    }

    public void supprimerSujet(Sujet sujet) {
    }

}
