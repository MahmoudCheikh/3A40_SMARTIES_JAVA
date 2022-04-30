/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.itextpdf.text.DocumentException;
import com.smarties.entities.Reclamation;
import com.smarties.services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GuiReclamationFrontController implements Initializable {

    ReclamationService rs = new ReclamationService();

    @FXML
    private ListView<Reclamation> listreclamationid;
    private TextField txtdeleteid;
    @FXML
    private TextField txtobjet;
    @FXML
    private TextField txtdesc;
    @FXML
    private DatePicker txtdat;
    @FXML
    private Button refreshrec;
    @FXML
    private Button btnAjouter;
    @FXML
    private AnchorPane a1;
    List<Reclamation> rec = rs.afficherReclamation();
    @FXML
    private AnchorPane apchart;

    

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

        rec.setObjet(txtobjet.getText());
        rec.setDescription(txtdesc.getText());
        LocalDate date1 = txtdat.getValue();
        rec.setDate(date1);

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
            LocalDate selectedDate = listreclamationid.getSelectionModel().getSelectedItem().getDate();
            String selectedobjet = listreclamationid.getSelectionModel().getSelectedItem().getObjet();

            txtdesc.setText(selectedDes1);
            txtobjet.setText(selectedobjet);
            txtdat.setValue(selectedDate);
        });
    }

    @FXML
    private void pdf(ActionEvent event) throws DocumentException {
       // rs.Gpdf();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("fichiier importer en pdf   !");
        alert.showAndWait();
    }

}



