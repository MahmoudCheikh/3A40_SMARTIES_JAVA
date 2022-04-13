/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Evenement;
import com.smarties.services.EvenementService;
import com.smarties.entities.Activite;
import com.smarties.services.ActiviteService;
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
    private ActiviteService act = new ActiviteService();
    
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
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtimage;
    @FXML
    private TextField txtidevent;
    @FXML
    private Button btnajouterA;
    @FXML
    private ListView<?> listactivite;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtID;
    @FXML
    private Button btnmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ArrayList al = (ArrayList) es.afficherEvenement();
        listevent.getItems().addAll(al);
        ArrayList a2 = (ArrayList) act.afficherActivite();
        listactivite.getItems().addAll(a2);
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
    //********activite*************

    @FXML
    private void ajouterAct(ActionEvent event) {
          Activite av = new Activite();
        
        av.setNom(txtname.getText());
        av.setDescription(txtdescription.getText());
        av.setImage(txtimage.getText());
        int x3 =  Integer.parseInt(txtidevent.getText());
        av.setId_event(x3);
     
        act.ajouterActivite(av);
    }

    @FXML
    private void deleteAct(ActionEvent event) {
           int id = Integer.parseInt(txtID.getText());
        act.supprimerActivite(id);
    }

    @FXML
    private void updateAct(ActionEvent event) {
      
        Activite actt = new Activite();
        
        actt.setNom(txtname.getText());
        actt.setDescription(txtdescription.getText());
        actt.setImage(txtimage.getText());
    
        int x1 =  Integer.parseInt(txtidevent.getText());
       

        actt.setId_event(x1);
     
        
         int id = Integer.parseInt(txtID.getText());
         actt.setId(id);
         
         act.modifierActivite(actt);
    
    
    
    }
     
   
}
