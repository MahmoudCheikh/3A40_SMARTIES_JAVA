/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Message;
import com.smarties.entities.Sujet;
import com.smarties.services.MessageService;
import com.smarties.services.SujetService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiSujetController implements Initializable {

    SujetService sujetsservice = new SujetService();
    MessageService messageService = new MessageService();

    private TextField txtid;
    @FXML
    private ListView<Sujet> tableSujetList;
    @FXML
    private TextField txtSujetIdUser;
    @FXML
    private TextField txtSujetTitre;
    @FXML
    private TextArea txtSujetContenu;
    @FXML
    private DatePicker txtSujetDate;
    @FXML
    private Button btnSujetAdd;
    @FXML
    private Button btnSujetUpdate;
    @FXML
    private Button btnSujetDelete;
    @FXML
    private Button btnMessageAdd;
    @FXML
    private Button btnMessageUpdate;
    @FXML
    private Button btnMessageDelete;
    @FXML
    private TextField txtMessageIdUser;
    @FXML
    private TextField txtMessageIdSujet;
    @FXML
    private TextArea txtMessageContenu;
    @FXML
    private DatePicker txtMessageDate;
    @FXML
    private TextField txtMessageId;
    @FXML
    private ListView<Message> tableMessagelist;
    @FXML
    private TextField txtSujetId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList arrayListSujet = (ArrayList) sujetsservice.afficherSujet();
        ArrayList arrayListMessage = (ArrayList) messageService.afficherMessage();
        tableMessagelist.getItems().addAll(arrayListMessage);
        tableSujetList.getItems().addAll(arrayListSujet);
    }

    @FXML
    private void SujetAdd(ActionEvent event) {
        Sujet sujet = new Sujet();

        sujet.setContenu(txtSujetContenu.getText());
        sujet.setTitre(txtSujetTitre.getText());
        sujet.setUserId(Integer.parseInt(txtSujetIdUser.getText()));
        sujet.setDate(txtSujetDate.getValue());

        sujetsservice.ajouterSujet(sujet);
    }

    @FXML
    private void SujetUpdate(ActionEvent event) {

        Sujet sujet = new Sujet();
        sujet.setId(Integer.parseInt(txtSujetId.getText()));
        sujet.setContenu(txtSujetContenu.getText());
        sujet.setTitre(txtSujetTitre.getText());
        sujet.setUserId(Integer.parseInt(txtSujetIdUser.getText()));
        sujet.setDate(txtSujetDate.getValue());

        sujetsservice.modifierSujet(sujet);
    }

    @FXML
    private void SujetDelete(ActionEvent event) {
        int id = Integer.parseInt(txtSujetId.getText());
        sujetsservice.supprimerSujet(id);
    }

    @FXML
    private void MessageUpdate(ActionEvent event) {

        Message message = new Message();

        message.setContenu(txtMessageContenu.getText());
        message.setDate(txtMessageDate.getValue());
        message.setIdSujet(Integer.parseInt(txtMessageIdSujet.getText()));
        message.setIdUser(Integer.parseInt(txtMessageIdUser.getText()));
        message.setId(Integer.parseInt(txtMessageId.getText()));

        
        System.out.println(message);
        messageService.modifierMessage(message);

    }

    @FXML
    private void MessageDelete(ActionEvent event) {
        messageService.supprimerMessage(Integer.parseInt(txtMessageId.getText()));
    }

    @FXML
    private void MessageAdd(ActionEvent event) {
        Message message = new Message();

        message.setContenu(txtMessageContenu.getText());
        message.setDate(txtMessageDate.getValue());
        message.setIdSujet(Integer.parseInt(txtMessageIdSujet.getText()));
        message.setIdUser(Integer.parseInt(txtMessageIdUser.getText()));
        messageService.ajouterMessage(message);
    }
          

   


}
