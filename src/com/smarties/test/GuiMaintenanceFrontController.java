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
public class GuiMaintenanceFrontController implements Initializable {

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
    public void refreshTable() {
        List<Maintenance> maint = ms.afficherMaintenance();
        listmaintenance.getItems().clear();
        listmaintenance.getItems().addAll(maint);

    }

    @FXML
    
   
    private void autofill(MouseEvent event) {
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
    private void chercher(ActionEvent event) {
        MaintenanceService a = new MaintenanceService();
        List<Maintenance> R = a.Chercher(textsupr.getText());
        ObservableList<Maintenance> datalist = FXCollections.observableArrayList(R);
        this.listmaintenance.setItems(datalist);
    }

}
