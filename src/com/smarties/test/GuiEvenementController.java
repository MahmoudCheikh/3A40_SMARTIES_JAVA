/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Evenement;
import com.smarties.services.EvenementService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
 * @author user
 */
public class GuiEvenementController implements Initializable {

    private EvenementService es = new EvenementService();
    
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdated;
    @FXML
    private TextField txtdatef;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtlieu;
    @FXML
    private TextField txtnbpart;
    @FXML
    private TextField txtnbplaces;
    @FXML
    private Button btnajouter;
    @FXML
    private ListView<?> listevent;
    @FXML
    private TextField txtid;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ArrayList al = (ArrayList) es.afficherEvenement();
        listevent.getItems().addAll(al);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        Evenement ev = new Evenement();
        
        ev.setNom(txtnom.getText());
        ev.setDate_d(txtdated.getText());
        ev.setDate_f(txtdatef.getText());
        ev.setType(txttype.getText());
        ev.setLieu(txtlieu.getText());
        int x1 =  Integer.parseInt(txtnbpart.getText());
        int x2 =  Integer.parseInt(txtnbplaces.getText());

        ev.setNb_participants(x2);
        ev.setNb_places(x1);
        
        
        
        es.ajouterEvenement(ev);
        
    }

    @FXML
    private void delete(ActionEvent event) {
        int id = Integer.parseInt(txtid.getText());
        es.supprimerEvenement(id);
        
    }

    @FXML
    private void update(ActionEvent event) {
        
        Evenement ev = new Evenement();
        
        ev.setNom(txtnom.getText());
        ev.setDate_d(txtdated.getText());
        ev.setDate_f(txtdatef.getText());
        ev.setType(txttype.getText());
        ev.setLieu(txtlieu.getText());
        int x1 =  Integer.parseInt(txtnbpart.getText());
        int x2 =  Integer.parseInt(txtnbplaces.getText());

        ev.setNb_participants(x2);
        ev.setNb_places(x1);
        
         int id = Integer.parseInt(txtid.getText());
         ev.setId(id);
         
         es.modifierEvenement(ev);
    }
    
}
