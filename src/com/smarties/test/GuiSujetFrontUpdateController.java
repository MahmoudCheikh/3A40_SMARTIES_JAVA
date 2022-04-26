/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Sujet;
import com.smarties.services.SujetService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiSujetFrontUpdateController implements Initializable {

    SujetService sujetService = new SujetService();
    static Sujet sujet; 
    
    @FXML
    private AnchorPane ap;
    @FXML
    private Label txtError;
    @FXML
    private TextArea txtContenu;
    @FXML
    private TextField txtTitre;
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtContenu.setText(sujet.getContenu());
        txtTitre.setText(sujet.getTitre());
    }    

    @FXML
    private void update(ActionEvent event) throws IOException {
        String titre = txtTitre.getText();
        String contenu = txtContenu.getText();
        Sujet s = new Sujet();
        s.setTitre(titre);
        s.setContenu(contenu);
        System.out.println(s.getTitre() + s.getContenu());
        s.setUserId(Smarties.user.getId());
        s.setNbReponses(0);
        s.setNbVues(0);
        s.setDate(LocalDate.now());
        s.setId(this.sujet.getId());
        sujetService.modifierSujet(s);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiForumFront.fxml"));
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);

    }
    
}
