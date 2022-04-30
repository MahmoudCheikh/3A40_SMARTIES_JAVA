/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Message;
import com.smarties.entities.Sujet;
import com.smarties.services.MessageService;
import com.smarties.services.SujetService;
import com.smarties.services.UsersService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiSujetController implements Initializable {

    SujetService sujetsservice = new SujetService();
    MessageService messageService = new MessageService();
    UsersService usersService = new UsersService();

    @FXML
    private ListView<Sujet> tableSujetList;
    @FXML
    private Button btnSujetDelete;
    @FXML
    private Button btnMessageDelete;
    @FXML
    private ListView<Message> tableMessagelist;
    @FXML
    private ComboBox<String> messageBox;
    @FXML
    private Button acts;
    @FXML
    private ComboBox<String> comboSujetUser;
    @FXML
    private ComboBox<Integer> comboSujetId;
    @FXML
    private ComboBox<Integer> messageComboSujetId;
    @FXML
    private ComboBox<Integer> messageComboId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList arrayListSujet = (ArrayList) sujetsservice.afficherSujet();
        ArrayList arrayListMessage = (ArrayList) messageService.afficherMessage();
        tableMessagelist.getItems().addAll(arrayListMessage);
        tableSujetList.getItems().addAll(arrayListSujet);
        comboSujetUser.setItems(FXCollections.observableArrayList(usersService.getCombo()));
        comboSujetId.setItems(FXCollections.observableArrayList(sujetsservice.getCombo()));
        messageComboId.setItems(FXCollections.observableArrayList(messageService.getCombo()));
        messageComboSujetId.setItems(FXCollections.observableArrayList(sujetsservice.getCombo()));
        messageBox.setItems(FXCollections.observableArrayList(usersService.getCombo()));
/*
        List<Sujet> sujets = sujetsservice.afficherSujet();
        ArrayList<Integer> al = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>();

        for (Sujet sujet : sujets) {
            if (al.contains(sujet.getUserId())) {
                al2.set(0, al2.get)  al2.get(al.indexOf(sujet.getUserId()))
            }
        }*/

    }

    public void refresh() {
        ArrayList arrayListSujet = (ArrayList) sujetsservice.afficherSujet();
        ArrayList arrayListMessage = (ArrayList) messageService.afficherMessage();
        tableMessagelist.getItems().setAll(arrayListMessage);
        tableSujetList.getItems().setAll(arrayListSujet);
        comboSujetUser.setItems(FXCollections.observableArrayList(usersService.getCombo()));
        comboSujetId.setItems(FXCollections.observableArrayList(sujetsservice.getCombo()));
        messageComboId.setItems(FXCollections.observableArrayList(messageService.getCombo()));
        messageComboSujetId.setItems(FXCollections.observableArrayList(sujetsservice.getCombo()));
        messageBox.setItems(FXCollections.observableArrayList(usersService.getCombo()));
    }

    @FXML
    private void SujetDelete(ActionEvent event) {
        int id = comboSujetId.getValue();
        sujetsservice.supprimerSujet(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Evenement supprimé!");
        alert.show();

        ObservableList<Sujet> items = FXCollections.observableArrayList();
        List<Sujet> lists = sujetsservice.afficherSujet();
        lists.stream().map((r) -> {
            String ch = r.toString();
            return r;
        }).forEachOrdered((r) -> {
            items.add(r);
        });
        tableSujetList.setItems(items);
    }

    @FXML
    private void actuas(ActionEvent event) {
        ObservableList<Sujet> items = FXCollections.observableArrayList();
        List<Sujet> lists = sujetsservice.afficherSujet();
        lists.stream().map((r) -> {
            String ch = r.toString();
            return r;
        }).forEachOrdered((r) -> {
            items.add(r);
        });
        tableSujetList.setItems(items);
    }

    @FXML
    private void getDataSujet(MouseEvent event) {
        tableSujetList.setOnMouseClicked((event1) -> {
            int selectedId = tableSujetList.getSelectionModel().getSelectedItem().getId();
            comboSujetId.setValue(selectedId);
        });
    }

    @FXML
    private void MessageDelete(ActionEvent event) {
        messageService.supprimerMessage(messageComboId.getValue());
        this.refresh();
    }

    @FXML
    private void getDataMessage(MouseEvent event) {
        tableMessagelist.setOnMouseClicked((event1) -> {
            int selectedId = tableMessagelist.getSelectionModel().getSelectedItem().getId();
            messageComboId.setValue(selectedId);
        });
    }

    @FXML
    private void searchSujet(ActionEvent event) throws SQLException {
        int id = usersService.searchByMail(comboSujetUser.getValue());
        List<Sujet> list = sujetsservice.searchByUser(id);
        tableSujetList.getItems().setAll(list);
    }

    @FXML
    private void msgSearchUser(ActionEvent event) throws SQLException {
        int id = usersService.searchByMail(messageBox.getValue());
        List<Message> list = messageService.searchByUser(id);
        tableMessagelist.getItems().setAll(list);
    }

    @FXML
    private void msgSearchSujet(ActionEvent event) throws SQLException {
        tableMessagelist.getItems().setAll(messageService.searchBySujet(messageComboSujetId.getValue()));
    }

    @FXML
    private void refreshmsg(ActionEvent event) {
        ArrayList arrayListMessage = (ArrayList) messageService.afficherMessage();
        tableMessagelist.getItems().setAll(arrayListMessage);
    }

}
