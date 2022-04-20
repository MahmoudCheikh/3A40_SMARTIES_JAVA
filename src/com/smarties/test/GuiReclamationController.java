/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Reclamation;
import com.smarties.services.ReclamationService;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GuiReclamationController implements Initializable {

    ReclamationService rs = new ReclamationService();

    @FXML
    private Button btnAjouter;
    @FXML
    private ListView<Reclamation> listreclamationid;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnmodify;
    @FXML
    private TextField txtdeleteid;
    @FXML
    private TextField txtobjet;
    @FXML
    private TextField txtdesc;
    @FXML
    private DatePicker txtdat;
    @FXML
    private TextField textId;
    @FXML
    private TextField textiduser;
    @FXML
    private Button rechercherec;
    @FXML
    private Button refreshrec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList ar = (ArrayList) rs.afficherReclamation();
        listreclamationid.getItems().addAll(ar);
        // TODO
    }

    @FXML
    private void Ajouter(ActionEvent event) {

        Reclamation rec = new Reclamation();

        if ((txtobjet.getText().equals("")) || (txtdesc.getText().equals("")) || (txtdat.getValue().equals("")) || (textId.getText().equals("")) || (textiduser.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", textId.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("ID doit etre de type Int !");
            alert.showAndWait();
        }

        rec.setObjet(txtobjet.getText());
        rec.setDescription(txtdesc.getText());
        LocalDate date1 = txtdat.getValue();
        rec.setDate(date1);
        int id = Integer.parseInt(textId.getText());
        int idu = Integer.parseInt(textiduser.getText());

        rec.setId(id);
        rec.setId_user_id(idu);

        rs.ajouterReclamation(rec);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Reclamation ajouté!");
        alert.show();
        ObservableList<Reclamation> items = FXCollections.observableArrayList();
        List<Reclamation> listreclamationid = rs.afficherReclamation();
        for (Reclamation r : listreclamationid) {
            String ch = r.toString();
            items.add(r);
        }

        this.listreclamationid.setItems(items);

    }

    @FXML
    private void Modifier(ActionEvent event) {

        Reclamation rec = new Reclamation();

        rec.setObjet(txtobjet.getText());
        rec.setDescription(txtdesc.getText());
        LocalDate date1 = txtdat.getValue();
        rec.setDate(date1);
        int id1 = Integer.parseInt(textId.getText());
        int idu1 = Integer.parseInt(textiduser.getText());

        rec.setId(id1);
        rec.setId_user_id(idu1);

        rs.modifierReclamation(rec);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Reclamation Modifié!");
        alert.show();
        ObservableList<Reclamation> items = FXCollections.observableArrayList();
        List<Reclamation> listreclamationid = rs.afficherReclamation();
        for (Reclamation r : listreclamationid) {
            String ch = r.toString();
            items.add(r);
        }

        this.listreclamationid.setItems(items);

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int id = Integer.parseInt(txtdeleteid.getText());
        rs.supprimerReclamation(id);
        ObservableList<Reclamation> items = FXCollections.observableArrayList();
        List<Reclamation> listreclamationid = rs.afficherReclamation();
        for (Reclamation r : listreclamationid) {
            String ch = r.toString();
            items.add(r);
        }

        this.listreclamationid.setItems(items);

    }

    @FXML
    private void chercher(ActionEvent event) {
        ReclamationService a = new ReclamationService();
        List<Reclamation> R = a.Chercher(txtdeleteid.getText());
        ObservableList<Reclamation> datalist = FXCollections.observableArrayList(R);
        this.listreclamationid.setItems(datalist);
        //refresh();

    }

    @FXML
    public void refreshTable() {
        List<Reclamation> rect = rs.afficherReclamation();
        listreclamationid.getItems().clear();
        listreclamationid.getItems().addAll(rect);

    }

    @FXML
    private void autofillrec(MouseEvent event) {
        Reclamation reclam = new Reclamation();
        listreclamationid.setOnMouseClicked((event1) -> {
            String selectedDes1 = listreclamationid.getSelectionModel().getSelectedItem().getDescription();
            Integer selectedId1 = listreclamationid.getSelectionModel().getSelectedItem().getId();
            Integer selectedIduser = listreclamationid.getSelectionModel().getSelectedItem().getId_user_id();
            LocalDate selectedDate = listreclamationid.getSelectionModel().getSelectedItem().getDate();
            String selectedobjet = listreclamationid.getSelectionModel().getSelectedItem().getObjet();

            txtdesc.setText(selectedDes1);
            txtobjet.setText(selectedobjet);
            textId.setText(String.valueOf(selectedId1));
            textiduser.setText(String.valueOf(selectedIduser));
            txtdat.setValue(selectedDate);
        });
    }
}
