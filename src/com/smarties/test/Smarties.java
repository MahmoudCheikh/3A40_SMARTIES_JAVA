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
import com.smarties.services.AbonnementService;
import com.smarties.services.ActiviteService;
import com.smarties.services.FavorisService;
import com.smarties.services.LocationService;
import com.smarties.tools.MaConnexion;
import java.time.LocalDate;
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
        us.afficherUser().forEach((_item) -> {
            System.out.println(user.toString());
        });
        us.modifierUser(user);
        // us.supprimerUser(44);

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

        ss.afficherSujet().forEach((sujets) -> {
            System.out.println(sujets);
        });

        /**
         * ** Message **
         */
        System.out.println("-------------------------------------------------MESSAGE");

        Message m1 = new Message();
        Message m2 = new Message();

        m1.setIdSujet(40);
        m1.setIdUser(45);
        m1.setDate(LocalDate.parse("2020-11-11"));
        m1.setContenu("test");

        MessageService ms = new MessageService();
        ms.ajouterMessage(m1);

        ms.afficherMessage().forEach((message) -> {
            System.out.println(message);
        });
        //*******************************Commande**********************************
        System.out.println("----------------------------------------Commande");
        Commande Commande = new Commande();
        Commande.setId(44);
        Commande.setIdProduit(14);
        Commande.setIdUser(45);
        Commande.setNbProduits(14);
        CommandeService cm = new CommandeService();
        cm.ajouterCommande(Commande);
        for (Commande commande : cm.afficherCommande()) {
            System.out.println(commande.toString());
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
        s.setIdProduit(14);
        s.setLibelle("b");
        s.setPrix(200);
        s.setQuantite(500);
        s.setDisponibilite("dispo");
        s.setEmplacement(1);

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
        emp.setStock(1);

        EmplacementService empl = new EmplacementService();
        empl.ajouterEmplacement(emp);
        for (Emplacement emplacemement : empl.afficherEmplacement()) {
            System.out.println(emplacemement.getId() + emplacemement.getLieu() + emplacemement.getCapacite());
        }
        empl.modifierEmplacement(emp);
        empl.supprimerEmplacement(48);

        /**
         * FAVORIS
         */
        System.out.println("----------------------------------------FAVORIS");
        Favoris f = new Favoris();
        f.setId(79);
        f.setIdProduit(14);
        f.setIdUser(45);

        FavorisService fav = new FavorisService();
        fav.ajouterFavoris(f);
        for (Favoris favoris : fav.afficherFavoris()) {
            System.out.println(favoris.getId() + favoris.getIdProduit() + favoris.getIdUser());
        }
        fav.modifierFavoris(f);
        fav.supprimerFavoris(77);

        //***************************************Achat******************************
        System.out.println("----------------------------------------ACHAT");
        Achat a = new Achat();
        a.setId(5);
        a.setIdUser(45);
        a.setIdProduit(14);
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

        //********************************Abonnement********************//
        System.out.println("----------------------------------------Abonnement");
        Abonnement ab = new Abonnement();
        ab.setId(100);
        ab.setId_user_id(45);
        ab.setType("VIP");
        ab.setDateD("1999-5-9");
        ab.setDateF("2000-8-6");
        ab.setPrix(50);
        AbonnementService ar = new AbonnementService();
        ar.ajouterAbonnement(ab);
        ar.ajouterAbonnement(ab);

        for (Abonnement abonnement : ar.afficherAbonnement()) {
            System.out.println(abonnement.getId() + abonnement.getId_user_id() + abonnement.getType() + abonnement.getDateD() + abonnement.getDateF() + abonnement.getPrix());

        }
        ar.modifierAbonnement(ab);
        ar.supprimerAbonnement(100);

        //******************************** Location ********************//
        System.out.println("----------------------------------------location");
        Location l = new Location();
        l.setId(26);
        l.setIdUser(45);
        l.setIdAbonnement(1);
        l.setHeure("5:3:0");
        l.setDate(LocalDate.parse("2022-12-23"));
        l.setDuree(45);
        LocationService lc = new LocationService();
        lc.ajouterLocation(l);
        for (Location location : lc.afficherLocation()) {
            System.out.println(location.getHeure() + location.getDuree() + location.getId() + location.getIdUser() + location.getIdAbonnement() + location.getDate());

        }
        lc.modifierLocation(l);
        lc.supprimerLocation(15);

        System.out.println("----------------------------------------ACTIVITE");
        /**
         * ********************* Activite ********************************
         */
        Activite activite = new Activite();
        activite.setId(1);
        activite.setNom("b");
        activite.setDescription("b");
        activite.setImage("a");
        activite.setId_event(9);
        ActiviteService as = new ActiviteService();
        as.ajouterActivite(activite);
        for (Activite act : as.afficherActivite()) {
            System.out.println(act.getId() + act.getNom() + act.getDescription() + act.getImage() + act.getId_event());
        }
        as.modifierActivite(activite);
        as.supprimerActivite(33);
        //********************************Evenement********************//
        System.out.println("----------------------------------------Evenement");
        Evenement e = new Evenement();
        e.setId(150);
        e.setNom("coursee velo");
        e.setType("VIP");
        e.setDate_d("1999-5-9");
        e.setDate_f("2000-8-6");
        e.setLieu("carthage");
        e.setNb_participants(10);
        e.setNb_places(10);
        EvenementService event = new EvenementService();
        event.ajouterEvenement(e);
        for (Evenement ev : event.afficherEvenement()) {
            System.out.println(ev.getId() + ev.getNom() + ev.getType() + ev.getLieu() + ev.getDate_d() + ev.getDate_f() + ev.getNb_participants() + ev.getNb_places());

        }
        event.modifierEvenement(e);
        event.supprimerEvenement(150);

        launch(args);
    }


    /*launch */
}
