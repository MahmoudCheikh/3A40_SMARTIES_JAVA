/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Maintenance;
import com.smarties.services.MaintenanceService;
import java.net.URL;
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
import javafx.scene.control.TextField;
import java.util.Locale;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GuiMaintenanceController implements Initializable {

    MaintenanceService ms = new MaintenanceService();

    @FXML
    private TextField txtid;
    @FXML
    private TextField txtidprod;
    @FXML
    private TextField txtrelation;
    @FXML
    private TextField txtrecid;
    @FXML
    private DatePicker textdatedeb;
    @FXML
    private DatePicker textdatefi;
    @FXML
    private TextField textadres;
    @FXML
    private TextField textdescri;
    @FXML
    private TextField textetat;
    @FXML
    private TextField textsupr;
    @FXML
    private ListView<Maintenance> listmaintenance;
    @FXML
    private Button btnajutermaint;
    @FXML
    private Button btnmodifmaint;
    @FXML
    private Button btnsuppermaint;
    @FXML
    private Button rechbtn;
    @FXML
    private Button refre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList am = (ArrayList) ms.afficherMaintenance();
        listmaintenance.getItems().addAll(am);
        refresh();
        // TODO
    }

    private void refresh() {
        List<Maintenance> maint = ms.afficherMaintenance();
        listmaintenance.getItems().clear();
        listmaintenance.getItems().addAll(maint);
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        LocalDate now = LocalDate.now();
        Maintenance mai = new Maintenance();

        if ((txtid.getText().equals("")) || (txtidprod.getText().equals("")) || (txtrelation.getText().equals("")) || (txtrecid.getText().equals("")) || (textdatedeb.getValue().equals("")) || (textdatefi.getValue().equals("")) || (textadres.getText().equals("")) || (textdescri.getText().equals("")) || (textetat.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", txtid.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("ID doit etre de type Int !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", txtidprod.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("ID produit doit etre de type Int !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", txtrelation.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("ID relation doit etre de type Int !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", txtrecid.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("ID reclamation doit etre de type Int !");
            alert.showAndWait();
        } else if ((textdatedeb.getValue().getYear() > textdatefi.getValue().getYear()) || (textdatedeb.getValue().getMonthValue() > textdatefi.getValue().getMonthValue()) || (textdatedeb.getValue().getDayOfMonth() > textdatefi.getValue().getDayOfMonth())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre inferieur a la date fin !");
            alert.showAndWait();
        } else if (textdatedeb.getValue().isBefore(now)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre supérieur a celle d'aujourd'hui ! ");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", textadres.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'adresse doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[a-z,A-Z]*", textdescri.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Description doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[terminé,Terminé,non terminé,Non terminé,non Terminé,Non Terminé]*", textetat.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("l'etat doit etre terminé ou non terminé");
            alert.showAndWait();

        }

        int id = Integer.parseInt(txtid.getText());
        int idp = Integer.parseInt(txtidprod.getText());
        int idr = Integer.parseInt(txtrelation.getText());
        int idre = Integer.parseInt(txtrecid.getText());

        mai.setId(id);
        mai.setId_produit_id(idp);
        mai.setRelation_id(idr);
        mai.setReclamation_id(idre);

        LocalDate dated = textdatedeb.getValue();
        LocalDate datef = textdatefi.getValue();
        mai.setDate_debut(dated);
        mai.setDate_fin(datef);
        mai.setAdresse(textadres.getText());
        mai.setDescription(textdescri.getText());
        mai.setEtat(textetat.getText());

        ms.ajouterMaintenance(mai);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Maintenance ajouté!");
        alert.show();
        // refresh();
        ObservableList<Maintenance> items = FXCollections.observableArrayList();
        List<Maintenance> listmaintenance = ms.afficherMaintenance();
        for (Maintenance r : listmaintenance) {
            String ch = r.toString();
            items.add(r);
        }

        this.listmaintenance.setItems(items);

    }

    @FXML
    private void Modifier1(ActionEvent event) {
        Maintenance mai = new Maintenance();
        Maintenance ma = listmaintenance.getSelectionModel().getSelectedItem();
        int id1 = Integer.parseInt(txtid.getText());
        int idp1 = Integer.parseInt(txtidprod.getText());
        int idr1 = Integer.parseInt(txtrelation.getText());
        int idre1 = Integer.parseInt(txtrecid.getText());

        mai.setId(id1);
        mai.setId_produit_id(idp1);
        mai.setRelation_id(idr1);
        mai.setReclamation_id(idre1);

        LocalDate dated = textdatedeb.getValue();
        LocalDate datef = textdatefi.getValue();
        mai.setDate_debut(dated);
        mai.setDate_fin(datef);
        mai.setAdresse(textadres.getText());
        mai.setDescription(textdescri.getText());
        mai.setEtat(textetat.getText());

        ms.modifierMaintenance(mai);

        ObservableList<Maintenance> items = FXCollections.observableArrayList();
        List<Maintenance> listmaintenance = ms.afficherMaintenance();
        for (Maintenance r : listmaintenance) {
            String ch = r.toString();
            items.add(r);
        }

        this.listmaintenance.setItems(items);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int id = Integer.parseInt(textsupr.getText());
        ms.supprimerMaintenance(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Maintenance supprimé!");
        alert.show();
        ObservableList<Maintenance> items = FXCollections.observableArrayList();
        List<Maintenance> listmaintenance = ms.afficherMaintenance();
        for (Maintenance r : listmaintenance) {
            String ch = r.toString();
            items.add(r);
        }

        this.listmaintenance.setItems(items);

    }

    @FXML
    private void chercher(ActionEvent event) {
        MaintenanceService a = new MaintenanceService();
        List<Maintenance> R = a.Chercher(textsupr.getText());
        ObservableList<Maintenance> datalist = FXCollections.observableArrayList(R);
        this.listmaintenance.setItems(datalist);
        //refresh();

    }

    @FXML
    public void refreshTable() {
        List<Maintenance> maint = ms.afficherMaintenance();
        listmaintenance.getItems().clear();
        listmaintenance.getItems().addAll(maint);

    }

    private void autofilll(MouseEvent event) {
        Maintenance mainte = new Maintenance();
        listmaintenance.setOnMouseClicked((event1) -> {
            String selectedDes = listmaintenance.getSelectionModel().getSelectedItem().getDescription();
            Integer selectedId = listmaintenance.getSelectionModel().getSelectedItem().getId();
            Integer selectedIdprod = listmaintenance.getSelectionModel().getSelectedItem().getId_produit_id();
            Integer selectedRecid = listmaintenance.getSelectionModel().getSelectedItem().getRelation_id();
            Integer selectedReclaid = listmaintenance.getSelectionModel().getSelectedItem().getReclamation_id();
            LocalDate selectedDatd = listmaintenance.getSelectionModel().getSelectedItem().getDate_debut();
            LocalDate selectedDatf = listmaintenance.getSelectionModel().getSelectedItem().getDate_fin();
            String selectedetat = listmaintenance.getSelectionModel().getSelectedItem().getEtat();
            String selectedAdr = listmaintenance.getSelectionModel().getSelectedItem().getAdresse();

            textdescri.setText(selectedDes);
            textetat.setText(selectedetat);
            textadres.setText(selectedAdr);
            txtid.setText(String.valueOf(selectedId));
            txtidprod.setText(String.valueOf(selectedIdprod));
            txtrelation.setText(String.valueOf(selectedRecid));
            txtrecid.setText(String.valueOf(selectedReclaid));
            textdatedeb.setValue(selectedDatd);
            textdatefi.setValue(selectedDatf);

        });
    }

    @FXML
    private void chercher(KeyEvent event) {
    }

    @FXML
    private void autofill(MouseEvent event) {
    }
}
