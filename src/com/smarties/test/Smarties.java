/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Achat;
import com.smarties.entities.Commande;
import com.smarties.services.CommandeService;
import com.smarties.entities.Produit;
import com.smarties.entities.Users;
import com.smarties.services.AchatService;
import com.smarties.services.ProduitService;
import com.smarties.services.UsersService;
import com.smarties.tools.MaConnexion;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Smarties extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });

        MaConnexion connexion = MaConnexion.getInstance();

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * ********************* USER ********************************
         */
        Users user = new Users();
        user.setId(44);
        user.setNom("b");
        user.setPrenom("b");
        user.setEmail("a");
        user.setImage("a");
        user.setRole("non");
        user.setAdresse("aaa");
        UsersService us = new UsersService();
        us.ajouterUser(user);
        for (Users users : us.afficherUser()) {
            System.out.println(users.getId() + users.getNom() + users.getPrenom());
        }
        us.modifierUser(user);
        us.supprimerUser(44);
 //*******************************Commande**********************************
   Commande Commande = new Commande();
        Commande.setId(44);
        Commande.setIdProduit(4);
        Commande.setIdUser(10);
        Commande.setNbProduits(14);
        CommandeService cm = new CommandeService();
        cm.ajouterCommande(Commande);
        for (Commande commande : cm.afficherCommande()) {
            System.out.println(Commande.getId() + Commande.getNbProduits());
        }
        cm.modifierCommande(Commande);
        cm.supprimerCommande(44);

        /**
         * ********************* PRODUIT ******************************************
         */
        Produit p = new Produit();
        p.setId(141);
        p.setLibelle("b");
        p.setImage("a");
        p.setDescription("b");
        p.setPrix(123);
        p.setType("velo");

        ProduitService pr = new ProduitService();
        pr.ajouterProduit(p);
        for (Produit produit : pr.afficherProduit()) {
            System.out.println(produit.getId() + produit.getLibelle() + produit.getDescription());
        }
        pr.modifierProduit(p);
        pr.supprimerProduit(139);
        
        
        
        launch(args);

 //***************************************Achat******************************
         Achat a = new Achat();
        a.setId(141);
        a.setIdUser(10);
        a.setIdProduit(4);
        a.setDate("b");
        a.setNomClient("Abcde");
        a.setNumeroClient(147);

        AchatService ac = new AchatService();
        ac.ajouterAchat(a);
        for (Achat achat : ac.afficherAchat()) {
            System.out.println(achat.getId() + achat.getIdProduit() + achat.getNomClient() + achat.getDate());
        }
        ac.modifierAchat(a);
        ac.supprimerAchat(14);
    
    
    }

}
