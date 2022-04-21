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
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    private TextField txtid;
    @FXML
    private ListView<Sujet> tableSujetList;
    private TextField txtSujetIdUser;
    @FXML
    private TextField txtSujetTitre;
    @FXML
    private TextArea txtSujetContenu;
    @FXML
    private DatePicker txtSujetDate;
    @FXML
    private Button btnSujetAdd;
    @FXML
    private Button btnSujetUpdate;
    @FXML
    private Button btnSujetDelete;
    @FXML
    private Button btnMessageAdd;
    @FXML
    private Button btnMessageUpdate;
    @FXML
    private Button btnMessageDelete;
    @FXML
    private TextArea txtMessageContenu;
    @FXML
    private DatePicker txtMessageDate;
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
    private void SujetAdd(ActionEvent event) throws SQLException {
        LocalDate now = LocalDate.now();
        if ((txtSujetContenu.getText().equals("")) || (txtSujetDate.getValue().equals("")) || (txtSujetTitre.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtSujetContenu.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Conetnu doit etre de type String !");
            alert.showAndWait();
        } else if (txtSujetDate.getValue().isBefore(now)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre supérieur a celle d'aujourd'hui ! ");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtSujetTitre.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Titre doit etre de type String !");
            alert.showAndWait();
        } else {

            Sujet sujet = new Sujet();

            int id = usersService.searchByMail(comboSujetUser.getValue());
            sujet.setUserId(id);
            sujet.setContenu(txtSujetContenu.getText());
            sujet.setTitre(txtSujetTitre.getText());
            sujet.setDate(txtSujetDate.getValue());

            sujetsservice.ajouterSujet(sujet);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Sujet ajouté!");
            alert.show();
            ObservableList<Sujet> items = FXCollections.observableArrayList();
            List<Sujet> lists = sujetsservice.afficherSujet();
            for (Sujet r : lists) {
                String ch = r.toString();
                items.add(r);
            }

            tableSujetList.setItems(items);

        }
    }

    @FXML
    private void SujetUpdate(ActionEvent event) {

        LocalDate now = LocalDate.now();
        if ((txtSujetContenu.getText().equals("")) || (txtSujetTitre.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtSujetContenu.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Contenu doit etre de type String !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtSujetTitre.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Titre doit etre de type String !");
            alert.showAndWait();
        } else {
            Sujet sujet = new Sujet();
            sujet.setContenu(txtSujetContenu.getText());
            sujet.setTitre(txtSujetTitre.getText());
            sujet.setId(comboSujetId.getValue());
            sujetsservice.modifierSujet(sujet);
            ObservableList<Sujet> items = FXCollections.observableArrayList();
            List<Sujet> lists = sujetsservice.afficherSujet();
            for (Sujet r : lists) {
                String ch = r.toString();
                items.add(r);
            }
            tableSujetList.setItems(items);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();

        }
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
        for (Sujet r : lists) {
            String ch = r.toString();
            items.add(r);
        }
        tableSujetList.setItems(items);
    }

    @FXML
    private void actuas(ActionEvent event) {
        ObservableList<Sujet> items = FXCollections.observableArrayList();
        List<Sujet> lists = sujetsservice.afficherSujet();
        for (Sujet r : lists) {
            String ch = r.toString();
            items.add(r);
        }
        tableSujetList.setItems(items);
    }

    @FXML
    private void getDataSujet(MouseEvent event) {
        tableSujetList.setOnMouseClicked((event1) -> {
            int selectedId = tableSujetList.getSelectionModel().getSelectedItem().getId();
            String selectedContenu = tableSujetList.getSelectionModel().getSelectedItem().getContenu();
            String selectedTitre = tableSujetList.getSelectionModel().getSelectedItem().getTitre();
            LocalDate selectedDate = tableSujetList.getSelectionModel().getSelectedItem().getDate();

            comboSujetId.setValue(selectedId);
            //dateachatpik.setValue(String.valueOf(selectedDate));
            txtSujetContenu.setText(selectedContenu);
            txtSujetTitre.setText(selectedTitre);

        });
    }

///////////////////////////////Message///////////////////////
    @FXML
    private void MessageUpdate(ActionEvent event) throws SQLException {

        Message message = new Message();

        message.setContenu(txtMessageContenu.getText());
        message.setId(messageComboId.getValue());

        System.out.println(message);
        messageService.modifierMessage(message);
        this.refresh();

    }

    @FXML
    private void MessageDelete(ActionEvent event) {
        messageService.supprimerMessage(messageComboId.getValue());
        this.refresh();
    }

    @FXML
    private void MessageAdd(ActionEvent event) throws SQLException {
        Message message = new Message();

        message.setContenu(txtMessageContenu.getText());
        message.setDate(txtMessageDate.getValue());
        message.setIdSujet(messageComboSujetId.getValue());
        message.setIdUser(usersService.searchByMail(messageBox.getValue()));
        messageService.ajouterMessage(message);
        this.refresh();
    }

    @FXML
    private void getDataMessage(MouseEvent event) {
        tableMessagelist.setOnMouseClicked((event1) -> {
            int selectedId = tableMessagelist.getSelectionModel().getSelectedItem().getId();
            String selectedContenu = tableMessagelist.getSelectionModel().getSelectedItem().getContenu();

            messageComboId.setValue(selectedId);
            txtMessageContenu.setText(selectedContenu);

        });
    }

}
