/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Message;
import com.smarties.entities.Sujet;
import com.smarties.services.MessageService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiMessageFrontAjouterController implements Initializable {

    static Sujet sujet;
    MessageService messageService = new MessageService();
    
    @FXML
    private TextArea txtContenu;
    @FXML
    private Label txtError;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Message message = new Message();
        message.setContenu(txtContenu.getText());
        message.setIdUser(Smarties.user.getId());
        message.setIdSujet(this.sujet.getId());
        message.setDate(LocalDate.now());
        
        messageService.ajouterMessage(message);
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiSujetFront.fxml"));
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }
    
}
