/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.services.SujetService;
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
 * @author user
 */
public class GuiSujetController implements Initializable {

    SujetService sujetsservice = new SujetService();
    
    @FXML
    private TextField txtid;
    @FXML
    private Button btndelete;
    @FXML
    private ListView<?> listsujetgui;
    @FXML
    private Button btnupdate;
    @FXML
    private TextField txttitre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btndelete(ActionEvent event) {
        int id = Integer.parseInt(txtid.getText());
        sujetsservice.supprimerSujet(id);
    }

    @FXML
    private void update(ActionEvent event) {
    }
    
}
