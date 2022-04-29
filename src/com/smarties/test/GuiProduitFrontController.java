/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Evenement;
import com.smarties.entities.Produit;
import com.smarties.services.EvenementService;
import com.smarties.services.FavorisService;
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
import javafx.scene.control.Alert;
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

    public Produit currentproduit;
    FavorisService fa = new FavorisService();
    ProduitService pr = new ProduitService();
    Produit prod = new Produit();
    @FXML
    private AnchorPane a1;
    @FXML
    private VBox vboxProd;
    @FXML
    private Text topText;

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
            ((Text) innerContainer.lookup("#libellePF")).setText("Nom du Produit: " + prod.getLibelle());
            ((Text) innerContainer.lookup("#prixPF")).setText("Prix en Dt: " + prod.getPrix() + " .DT");
            Image img = new Image(new FileInputStream(prod.getImage()));
            ((ImageView) innerContainer.lookup("#imagePF")).setImage(img);
            ((Text) innerContainer.lookup("#typePF")).setText("Type du Produit: " + prod.getType());
            ((Text) innerContainer.lookup("#descPF")).setText("Description: " + prod.getDescription());
            //button QRcode 
            ((Button) innerContainer.lookup("#generateQr")).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        QrCodeController.prod = prod;
                        String AllInfo = " Libelle: " + prod.getLibelle() + "\n Prix: " + prod.getPrix() + "\n Type: " + prod.getType() + "\n Description:  " + prod.getDescription() + prod.getId() + "";
                        generateQr(prod);

                    } catch (IOException ex) {
                        Logger.getLogger(QrCodeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            //fin button QrCode
            //button favoris
            ((Button) innerContainer.lookup("#favorisFront")).setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {

                    System.out.println(prod.getId());
                    String id = null;
                    id = String.valueOf(prod.getId());
                    if (fa.getIgnoreRepetetion(id) == false) {
                        fa.ajouterFavoris(prod.getId());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setContentText("Favoris Ajouté avec succés!");
                        alert.show();
                        new animatefx.animation.Bounce(vboxProd).play();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("le produit deja favoris !");
                        alert.showAndWait();
                        new animatefx.animation.Shake(vboxProd).play();
                    }
                }
            });
            //fin favoris
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    private void generateQr(Produit prod) throws IOException {
        currentproduit = prod;
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("QrCode.fxml"));
        a1.getChildren().setAll(xx);
        new animatefx.animation.Pulse(a1).play();

    }

}
