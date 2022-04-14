/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Achat;
import com.smarties.entities.Commande;
import com.smarties.entities.Emplacement;
import com.smarties.entities.Evenement;
import com.smarties.entities.Message;
import com.smarties.services.CommandeService;
import com.smarties.entities.Produit;
import com.smarties.entities.Stock;
import com.smarties.entities.Sujet;
import com.smarties.entities.Users;
import com.smarties.services.AchatService;
import com.smarties.services.EmplacementService;
import com.smarties.services.EvenementService;
import com.smarties.services.MessageService;
import com.smarties.services.ProduitService;
import com.smarties.services.StockService;
import com.smarties.services.SujetService;
import com.smarties.services.UsersService;
import com.smarties.entities.Abonnement;
import com.smarties.entities.Activite;
import com.smarties.entities.Favoris;
import com.smarties.entities.Location;
import com.smarties.entities.Maintenance;
import com.smarties.entities.Reclamation;
import com.smarties.services.AbonnementService;
import com.smarties.services.ActiviteService;
import com.smarties.services.FavorisService;
import com.smarties.services.LocationService;
import com.smarties.services.MaintenanceService;
import com.smarties.services.ReclamationService;
import com.smarties.tools.MaConnexion;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Smarties extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {

        MaConnexion connexion = MaConnexion.getInstance();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Smarties");
        showMainView();
    }

    public void showMainView() throws IOException {
        BorderPane mainLayout = FXMLLoader.load(getClass().getResource("GuiBack.fxml"));
        Scene scene = new Scene(mainLayout, 1366, 768);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void showEventView() throws IOException {
        //   BorderPane eventpane = FXMLLoader.load(getClass().getResource("GuiEvenement.fxml"));
        //   mainLayout.setCenter(eventpane);

        //MaConnexion connexion = MaConnexion.getInstance();
         try {
        Parent root = FXMLLoader.load(getClass().getResource("GuiMaintenance.fxml"));
                    Scene scene = new Scene(root, 1366, 768);
                    primaryStage.setTitle("GClaim");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
