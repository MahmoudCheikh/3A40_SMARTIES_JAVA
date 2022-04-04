/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Users;
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
        launch(args);
    }

}
