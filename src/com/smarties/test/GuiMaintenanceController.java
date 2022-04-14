/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Maintenance;
import com.smarties.services.MaintenanceService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private TextField textdatedeb;
    @FXML
    private TextField textdatefi;
    @FXML
    private TextField textadres;
    @FXML
    private TextField textdescri;
    @FXML
    private TextField textetat;
    @FXML
    private TextField textsupr;
    @FXML
    private ListView<?> listmaintenance;
    @FXML
    private Button btnajutermaint;
    @FXML
    private Button btnmodifmaint;
    @FXML
    private Button btnsuppermaint;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Ajouter(ActionEvent event) {

        Maintenance mai = new Maintenance();

        int id = Integer.parseInt(txtid.getText());
        int idp = Integer.parseInt(txtidprod.getText());
        int idr = Integer.parseInt(txtrelation.getText());
        int idre = Integer.parseInt(txtrecid.getText());

        mai.setId(id);
        mai.setId_produit_id(idp);
        mai.setRelation_id(idr);
        mai.setReclamation_id(idre);

        mai.setDate_debut(textdatedeb.getText());
        mai.setDate_fin(textdatefi.getText());
        mai.setAdresse(textadres.getText());
        mai.setDescription(textdescri.getText());
        mai.setEtat(textetat.getText());

        ms.ajouterMaintenance(mai);
    }

    @FXML
    private void Modifier1(ActionEvent event) {
        Maintenance mai = new Maintenance();

        int id1 = Integer.parseInt(txtid.getText());
        int idp1 = Integer.parseInt(txtidprod.getText());
        int idr1 = Integer.parseInt(txtrelation.getText());
        int idre1 = Integer.parseInt(txtrecid.getText());

        mai.setId(id1);
        mai.setId_produit_id(idp1);
        mai.setRelation_id(idr1);
        mai.setReclamation_id(idre1);

        mai.setDate_debut(textdatedeb.getText());
        mai.setDate_fin(textdatefi.getText());
        mai.setAdresse(textadres.getText());
        mai.setDescription(textdescri.getText());
        mai.setEtat(textetat.getText());

        ms.modifierMaintenance(mai);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int id = Integer.parseInt(textsupr.getText());
        ms.supprimerMaintenance(id);
    }

}
