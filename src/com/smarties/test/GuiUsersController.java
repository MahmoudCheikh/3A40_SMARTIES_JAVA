/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Users;
import com.smarties.services.UsersService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiUsersController implements Initializable {

    UsersService us = new UsersService();

    @FXML
    private TextField txtbackusermail;
    @FXML
    private TextField txtbackuserpassword;
    @FXML
    private TextField txtbackusernom;
    @FXML
    private TextField txtbackuserprenom;
    @FXML
    private TextField txtbackuseradresse;
    @FXML
    private TextField txtbackusertoher1;
    @FXML
    private TextField txtbackuserother2;
    @FXML
    private TextField txtbackuserid;
    @FXML
    private ListView<Users> tableuserslist;
    @FXML
    private TextField reuser;
    @FXML
    private Button search;
    @FXML
    private Button actu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList al = (ArrayList) us.afficherUser();
        tableuserslist.getItems().addAll(al);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if ((txtbackusernom.getText().equals("")) || (txtbackuserprenom.getText().equals("")) || (txtbackusermail.getText().equals("")) || (txtbackuseradresse.getText().equals("")) || (txtbackuserpassword.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackusernom.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nom de l'user doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackuserprenom.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("pernom doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackuseradresse.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Adresse de l'user doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackuserpassword.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password Invalid !");
            alert.showAndWait();

        } else {
            Users user = new Users();
            user.setNom(txtbackusernom.getText());
            user.setPrenom(txtbackuserprenom.getText());
            user.setEmail(txtbackusermail.getText());
            user.setAdresse(txtbackuseradresse.getText());
            user.setPassword(txtbackuserpassword.getText());
            user.setRole(txtbackusertoher1.getText());
            user.setImage(txtbackuserother2.getText());

            us.ajouterUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("User ajouté!");
            alert.show();
            ObservableList<Users> items = FXCollections.observableArrayList();
            List<Users> listuser = us.afficherUser();
            for (Users r : listuser) {
                String ch = r.toString();
                items.add(r);
            }

            tableuserslist.setItems(items);
        }

    }

    @FXML
    private void modifier(ActionEvent event) {
        if ((txtbackusernom.getText().equals("")) || (txtbackuserprenom.getText().equals("")) || (txtbackusermail.getText().equals("")) || (txtbackuseradresse.getText().equals("")) || (txtbackuserpassword.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackusernom.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nom de l'user doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackuserprenom.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("pernom doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackuseradresse.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Adresse de l'user doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[a-z,A-Z]*", txtbackuserpassword.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password Invalid !");
            alert.showAndWait();

        } else {
            Users user = new Users();
            user.setNom(txtbackusernom.getText());
            user.setPrenom(txtbackuserprenom.getText());
            user.setEmail(txtbackusermail.getText());
            user.setAdresse(txtbackuseradresse.getText());
            user.setPassword(txtbackuserpassword.getText());
            user.setRole(txtbackusertoher1.getText());
            user.setImage(txtbackuserother2.getText());
            user.setId(Integer.parseInt(txtbackuserid.getText()));
            us.modifierUser(user);
            ObservableList<Users> items = FXCollections.observableArrayList();
            List<Users> listuser = us.afficherUser();
            for (Users r : listuser) {
                String ch = r.toString();
                items.add(r);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        int id = Integer.parseInt(txtbackuserid.getText());
        us.supprimerUser(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("User supprimé!");
        alert.show();
        ObservableList<Users> items = FXCollections.observableArrayList();
        List<Users> listuser = us.afficherUser();
        for (Users r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        tableuserslist.setItems(items);
    }

    @FXML
    private void searchuser(ActionEvent event) {
        UsersService n = new UsersService();
        List<Users> R = n.searchuser(reuser.getText());

        ObservableList<Users> datalist = FXCollections.observableArrayList(R);

        tableuserslist.setItems(datalist);

    }

    @FXML
    private void actuser(ActionEvent event) {
        ObservableList<Users> items = FXCollections.observableArrayList();
        List<Users> listusers = us.afficherUser();
        for (Users r : listusers) {
            String ch = r.toString();
            items.add(r);
        }
        tableuserslist.setItems(items);
    }

}
