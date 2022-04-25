/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Evenement;
import com.smarties.entities.Produit;
import com.smarties.services.EvenementService;
import com.smarties.services.ProduitService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class GuiProduitFrontController implements Initializable {

    @FXML
    private AnchorPane a1;
    @FXML
    private VBox vboxProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService pr = new ProduitService();
        List<Produit> listProd = pr.afficherProduit();
        Collections.reverse(listProd);
        if (!listProd.isEmpty()) {
            for (Produit prod : listProd) {
                vboxProd.getChildren().add(make(prod));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            vboxProd.getChildren().add(stackPane);
        }

    }

    public Parent make(Produit prod) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModelProduit.fxml")));
            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#libellePF")).setText("Nom du Produit:" + prod.getLibelle());
            ((Text) innerContainer.lookup("#prixPF")).setText("Prix en Dt:" + prod.getPrix() + "DT");
            //((Text) innerContainer.lookup("#imagePF")).setText(prod.getImage());
            Image img = new Image(new FileInputStream(prod.getImage()));
            ((ImageView) innerContainer.lookup("#imagePF")).setImage(img);
            ((Text) innerContainer.lookup("#typePF")).setText("Type du Produit:" + prod.getType());
            ((TextArea) innerContainer.lookup("#descPF")).setText("Description:" + prod.getDescription());
            //((Text) innerContainer.lookup("#descPF")).setText("Description:" + prod.getDescription());

            // ((Text) innerContainer.lookup("#createdAtText")).setText("CreatedAt : " + rdv.getCreatedAt());
            // ((Text) innerContainer.lookup("#updatedAtText")).setText("UpdatedAt : " + rdv.getUpdatedAt());
            /* ((Button) innerContainer.lookup("#afficherAct")).setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                     try {
                         AfficherAct(a,e);
                     } catch (IOException ex) {
                         Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });*/
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

}
