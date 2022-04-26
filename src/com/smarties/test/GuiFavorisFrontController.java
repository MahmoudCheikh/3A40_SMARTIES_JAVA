/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Favoris;
import com.smarties.entities.Produit;
import com.smarties.services.FavorisService;
import com.smarties.services.ProduitService;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class GuiFavorisFrontController implements Initializable {

    ProduitService produitService = new ProduitService();
    
    @FXML
    private AnchorPane a1;
    @FXML
    private Text topText;
    @FXML
    private VBox vboxFavoris;

        public Produit currentproduit;
    FavorisService fa = new FavorisService();
    Favoris fav = new Favoris();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         FavorisService pr = new FavorisService();
        List<Favoris> listfav = fa.afficherFavoris();
        Collections.reverse(listfav);
        if (!listfav.isEmpty()) {
            for (Favoris favo : listfav) {
                System.out.println(favo);
                try {
                    vboxFavoris.getChildren().add(make(favo));
                } catch (SQLException ex) {
                    Logger.getLogger(GuiFavorisFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            vboxFavoris.getChildren().add(stackPane);
        }

    }
public Parent make(Favoris fav) throws SQLException {
        Parent parent = null;
        
        Produit p = produitService.GetProdbyid(fav.getIdProduit());
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FavorisModel.fxml")));
            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#prodFav")).setText("Nom du Produit: " + p.getLibelle());
            ((Text) innerContainer.lookup("#prixFav")).setText("Prix en Dt: " + p.getPrix()+ " .DT");
            Image img = new Image(new FileInputStream(p.getImage()));
            ((ImageView) innerContainer.lookup("#imageFav")).setImage(img);
            ((Text) innerContainer.lookup("#descFav")).setText("Description: " + p.getDescription());
                        //button QRcode 
            ((Button) innerContainer.lookup("#supprimerFav")).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    fa.supprimerFavoris(fav.getId());
                    //refresh();
                }
            });
            //fin button QrCode

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

   /* private void refresh() {
        List<Favoris> fav = fa.afficherFavoris();
    }*/
    
    
}
    