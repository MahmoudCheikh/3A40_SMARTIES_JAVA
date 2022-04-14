/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Reclamation;
import com.smarties.services.ReclamationService;
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
public class GuiReclamationController implements Initializable {

    ReclamationService rs = new ReclamationService();

    @FXML
    private Button btnAjouter;
    @FXML
    private ListView<?> listreclamationid;
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
    private TextField txtdat;
    @FXML
    private TextField textId;
    @FXML
    private TextField textiduser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        Reclamation rec = new Reclamation();

        rec.setObjet(txtobjet.getText());
        rec.setDescription(txtdesc.getText());
        rec.setDate(txtdat.getText());

        int id = Integer.parseInt(textId.getText());
        int idu = Integer.parseInt(textiduser.getText());

        rec.setId(id);
        rec.setId_user_id(idu);

        rs.ajouterReclamation(rec);
    }

    @FXML
    private void Modifier(ActionEvent event) {

        Reclamation rec = new Reclamation();

        rec.setObjet(txtobjet.getText());
        rec.setDescription(txtdesc.getText());
        rec.setDate(txtdat.getText());

        int id1 = Integer.parseInt(textId.getText());
        int idu1 = Integer.parseInt(textiduser.getText());

        rec.setId(id1);
        rec.setId_user_id(idu1);

        rs.modifierReclamation(rec);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int id = Integer.parseInt(txtdeleteid.getText());
        rs.supprimerReclamation(id);
    }
}
