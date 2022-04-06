/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Achat;
import com.smarties.entities.Commande;
import com.smarties.entities.Emplacement;
import com.smarties.services.CommandeService;
import com.smarties.entities.Produit;
import com.smarties.entities.Stock;
import com.smarties.entities.Sujet;
import com.smarties.entities.Users;
import com.smarties.services.AchatService;
import com.smarties.services.EmplacementService;
import com.smarties.services.ProduitService;
import com.smarties.services.StockService;
import com.smarties.services.SujetService;
import com.smarties.services.UsersService;
import com.smarties.tools.MaConnexion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.event.ActionEvent;
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

        System.out.println("----------------------------------------USER");
        /**
         * ********************* USER ********************************
         */
        Users user = new Users();
        user.setId(1);
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
        System.out.println("----------------------------------------Commande");
        Commande Commande = new Commande();
        Commande.setId(44);
        Commande.setIdProduit(5);
        Commande.setIdUser(1);
        Commande.setNbProduits(14);
        CommandeService cm = new CommandeService();
        cm.ajouterCommande(Commande);
        for (Commande commande : cm.afficherCommande()) {
            System.out.println(Commande.getId() + Commande.getNbProduits());
        }
        cm.modifierCommande(Commande);
        cm.supprimerCommande(44);

        /**
         * PRODUIT
         */
        System.out.println("----------------------------------------PRODUIT");
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
        
        /**
         * STOCK
         */
        System.out.println("----------------------------------------Stock");
        Stock s = new Stock();
        s.setId(49);
        s.setIdProduit(101);
        s.setLibelle("b");
        s.setPrix(200);
        s.setQuantite(500);
        s.setDisponibilite("dispo");
        s.setEmplacement(50);

        StockService st = new StockService();
        st.ajouterStock(s);
        for (Stock stock : st.afficherStock()) {
            System.out.println(stock.getId() + stock.getLibelle() + stock.getDisponibilite());
        }
        st.modifierStock(s);
        st.supprimerStock(48);
       
       
        /**
         * EMPLACEMENT
         */
        System.out.println("----------------------------------------Emplacement");
        Emplacement emp = new Emplacement();
        emp.setId(45);
        emp.setLieu("b");
        emp.setCapacite(200);
        emp.setStock(52);


        EmplacementService empl = new EmplacementService();
        empl.ajouterEmplacement(emp);
        for (Emplacement emplacemement : empl.afficherEmplacement()) {
            System.out.println(emplacemement.getId() + emplacemement.getLieu()+ emplacemement.getCapacite());
        }
        empl.modifierEmplacement(emp);
        empl.supprimerEmplacement(48);
       
        //***************************************Achat******************************
        System.out.println("----------------------------------------ACHAT");
        Achat a = new Achat();
        a.setId(5);
        a.setIdUser(1);
        a.setIdProduit(4);
        a.setDate(LocalDate.parse("2020-12-12"));
        a.setNomClient("Abcde");
        a.setNumeroClient(147);

        AchatService ac = new AchatService();
        ac.ajouterAchat(a);
        for (Achat achat : ac.afficherAchat()) {
            System.out.println(achat.getId() + achat.getIdProduit() + achat.getNomClient() + achat.getDate());
        }
        ac.modifierAchat(a);
        ac.supprimerAchat(5);

        /* Sujet */
        System.out.println("----------------------------------------SUJET");
        SujetService ss = new SujetService();

        Sujet sujet = new Sujet();
        sujet.setContenu("test");
        sujet.setTitre("test");
        sujet.setNbReponses(10);
        sujet.setNbVues(10);
        sujet.setDate(LocalDate.parse("2020-12-12"));
        sujet.setUserId(45);

        Sujet sujet2 = new Sujet();
        sujet2.setContenu("test2");
        sujet2.setTitre("test2");
        sujet2.setNbReponses(102);
        sujet2.setNbVues(102);
        sujet2.setDate(LocalDate.parse("2020-11-11"));
        sujet2.setUserId(45);

        ss.ajouterSujet(sujet);
        ss.ajouterSujet(sujet2);

        /*launch */
        launch(args);
    }

}
