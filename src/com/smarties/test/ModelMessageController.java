/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Message;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModelMessageController implements Initializable {
    
    static Message message;
    
    @FXML
    private Label txtMsgUser;
    @FXML
    private Text txtMsgContent;
    @FXML
    private Label txtMsgDate;
    @FXML
    private Button btnMsgModifier;
    @FXML
    private Button btnMsgSupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMsgContent.setWrappingWidth(300);
    }
    
    @FXML
    private void modifier(ActionEvent event) {
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
    }
    
}
