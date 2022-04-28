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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiUsersController implements Initializable {

    UsersService us = new UsersService();

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
        listusers.stream().map((r) -> {
            String ch = r.toString();
            return r;
        }).forEachOrdered((r) -> {
            items.add(r);
        });
        tableuserslist.setItems(items);
    }

    private void actualiser() {
        ObservableList<Users> items = FXCollections.observableArrayList();
        List<Users> listusers = us.afficherUser();
        listusers.stream().map((r) -> {
            String ch = r.toString();
            return r;
        }).forEachOrdered((r) -> {
            items.add(r);
        });
        tableuserslist.setItems(items);
    }

    @FXML
    private void getDataUsers(MouseEvent event) {
        tableuserslist.setOnMouseClicked((event1) -> {
            int selectedId = tableuserslist.getSelectionModel().getSelectedItem().getId();
            reuser.setText(String.valueOf(selectedId));
        });

    }

    @FXML
    private void ban(ActionEvent event) {
        Users user = new Users();
        user.setId(Integer.parseInt(reuser.getText()));
        us.ban(user);
        actualiser();
    }

    @FXML
    private void unban(ActionEvent event) {
        Users user = new Users();
        user.setId(Integer.parseInt(reuser.getText()));
        us.unban(user);
        actualiser();
    }

}
