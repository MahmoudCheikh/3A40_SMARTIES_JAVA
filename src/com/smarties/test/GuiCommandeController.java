/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Commande;
import com.smarties.services.AchatService;
import com.smarties.entities.Achat;
import com.smarties.services.CommandeService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ahmed Elmoez
 */
public class GuiCommandeController implements Initializable {

    private CommandeService cs = new CommandeService();
    private AchatService sa = new AchatService();

    @FXML
    private TextField txtProduit;
    @FXML
    private TextField txtnbrproduit;
    @FXML
    private ListView<?> listcommande;
    @FXML
    private Button btnajouterc;
    @FXML
    private Button btnupdatec;
    @FXML
    private Button btndeletec;
    @FXML
    private TextField txtiduser;
    @FXML
    private TextField txtid;
    @FXML
    private Tab btndeleteachat;
    @FXML
    private Button btnajouterachat;
    @FXML
    private TextField txtidUser;
    @FXML
    private TextField txtDateAchat;
    @FXML
    private TextField txtnomclient;
    @FXML
    private TextField txtnumclient;
    @FXML
    private Button btnupdateachat;
    @FXML
    private TextField txtidproduit;
    @FXML
    private ListView<?> listachat;
    @FXML
    private TextField idachat;
    @FXML
    private Button btndeleteachatx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList al = (ArrayList) cs.afficherCommande();
        listcommande.getItems().addAll(al);

        ArrayList a2 = (ArrayList) sa.afficherAchat();
        listachat.getItems().addAll(a2);
// TODO
    }

    @FXML
    private void ajouterc(ActionEvent event) {
        Commande c = new Commande();

        int x1 = Integer.parseInt(txtProduit.getText());
        int x2 = Integer.parseInt(txtnbrproduit.getText());
        int x3 = Integer.parseInt(txtiduser.getText());

        c.setIdProduit(x1);
        c.setNbProduits(x2);
        c.setIdUser(x3);

        cs.ajouterCommande(c);

    }

    @FXML
    private void update(ActionEvent event) {

        Commande c = new Commande();

        int x1 = Integer.parseInt(txtProduit.getText());

        int x2 = Integer.parseInt(txtnbrproduit.getText());

        int x3 = Integer.parseInt(txtiduser.getText());

        c.setIdProduit(x1);
        c.setNbProduits(x2);
        c.setIdUser(x3);

        int id = Integer.parseInt(txtid.getText());
        c.setId(id);

        System.out.println(c);

        cs.modifierCommande(c);
    }

    @FXML
    private void deletec(ActionEvent event) {
        int id = Integer.parseInt(txtid.getText());
        cs.supprimerCommande(id);
    }
/*---------------------Achat-----------------------------*/
    @FXML
    private void ajoutachat(ActionEvent event) {
    }

    @FXML
    private void updateachat(ActionEvent event) {
    }

    @FXML
    private void deleteachat(ActionEvent event) {
          int id = Integer.parseInt(idachat.getText());
        sa.supprimerAchat(id);
    }

}
