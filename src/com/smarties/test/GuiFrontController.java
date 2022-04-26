/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiFrontController implements Initializable {

    @FXML
    private Button btnlogout;
    @FXML
    private Button evenement;
    @FXML
    private Button btnproduitsF;
    @FXML
    private AnchorPane a1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void logout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            btnlogout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("AfficherEvent.fxml"));
        a1.getChildren().setAll(xx);
    }

    @FXML
    private void forum(ActionEvent event) throws IOException {
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("GuiForumFront.fxml"));
        a1.getChildren().setAll(xx);
    }

    @FXML
    private void GestionProduit(ActionEvent event) throws IOException {
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("GuiProduitFront.fxml"));
        a1.getChildren().setAll(xx);
    }

    @FXML
    private void GestionSites(ActionEvent event) {
    }

    @FXML
    private void GestionProfil(ActionEvent event) {
    }

    @FXML
    private void GestionFavoris(ActionEvent event) throws IOException {
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("ModelFavoris.fxml"));
        a1.getChildren().setAll(xx);
    }

    @FXML
    private void FrontCommandes(ActionEvent event) throws IOException {
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("MesCommandes.fxml"));
        a1.getChildren().setAll(xx);
    }

    @FXML
    private void FrontAbonnement(ActionEvent event) {
    }

    @FXML
    private void FrontLocation(ActionEvent event) {
    }

    @FXML
    private void FrontReclamation(ActionEvent event) {
    }

    @FXML
    private void FrontMaintenance(ActionEvent event) {
    }

}
