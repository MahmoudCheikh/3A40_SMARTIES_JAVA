/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Emplacement;
import com.smarties.entities.Produit;
import com.smarties.entities.Stock;
import com.smarties.services.EmplacementService;
import com.smarties.services.FavorisService;
import com.smarties.services.StockService;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class GuiSitesController implements Initializable {

    @FXML
    private AnchorPane a1;
    @FXML
    private Text topText;
    @FXML
    private VBox vboxSite;

    public Emplacement currentemp;
    EmplacementService em = new EmplacementService();
    StockService st = new StockService();
    Emplacement emp = new Emplacement();
    Stock sto = new Stock();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // EmplacementService st = new EmplacementService();
        List<Emplacement> listEmp = em.afficherEmplacement();
        Collections.reverse(listEmp);
        if (!listEmp.isEmpty()) {
            for (Emplacement emp : listEmp) {
                try {
                    vboxSite.getChildren().add(make(emp));
                } catch (SQLException ex) {
                    Logger.getLogger(GuiSitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            vboxSite.getChildren().add(stackPane);
        }
    }

    public Parent make(Emplacement emp) throws SQLException {
        Parent parent = null;
        Stock s = st.GetStockbyid(emp.getStock());
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SitesModel.fxml")));
            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            //emplacement
            ((Text) innerContainer.lookup("#lieuSite")).setText("Lieu : " + emp.getLieu());
            ((Text) innerContainer.lookup("#capaciteSite")).setText("Capacité du Site: " + emp.getCapacite());
            //stock
            ((Text) innerContainer.lookup("#QuantiteStock")).setText("Quantite disponible: " + s.getQuantite());
            ((Text) innerContainer.lookup("#prixStock")).setText("Prix en Gros: " + s.getPrix());
            //button Map
            /*((Button) innerContainer.lookup("#generateMap")).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        generateMap(emp);
                    } catch (IOException ex) {
                        Logger.getLogger(GuiSitesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });*/
            //fin button QrCode

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

   /* private void generateMap(Emplacement emp) throws IOException {
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("MapController.fxml"));
        a1.getChildren().setAll(xx);

    }*/
}
