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
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiSujetFrontController implements Initializable {

    static Sujet sujet;
    @FXML
    private Label txtUser;
    @FXML
    private Label txtContenu;
    @FXML
    private Label txtDate;
    @FXML
    private Label txtTitre;
    @FXML
    private AnchorPane paneSujet;
    @FXML
    private ScrollPane paneScroll;
    @FXML
    private VBox paneMessage;

    MessageService messageService = new MessageService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUser.setText(Integer.toString(sujet.getId()));
        txtDate.setText(sujet.getDate().toString());
        txtTitre.setText(sujet.getTitre());
        txtContenu.setText(sujet.getContenu());

        List<Message> listMessage = messageService.afficherMessage();

        if (!listMessage.isEmpty()) {
            for (Message message : listMessage) {
                if (message.getIdSujet() == GuiSujetFrontController.sujet.getId()) {
                    paneMessage.getChildren().add(makeMessage(message));
                }
            }
        } else {

        }
        paneScroll.setContent(paneMessage); 

    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public Parent makeMessage(Message message) {
        Parent innerContainer = null;
        try {
            System.out.println("test");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelMessage.fxml"));
            innerContainer = loader.load();
            System.out.println("test2");

            //  HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Label) innerContainer.lookup("#txtMsgDate")).setText("Posté le: " + message.getDate().toString());
            ((Label) innerContainer.lookup("#txtMsgUser")).setText("User : " + message.getIdUser());
            ((Label) innerContainer.lookup("#txtMsgContent")).setText("Contenu : " + message.getContenu());

            if (Smarties.user.getId() == message.getIdUser()) {
                ((Button) innerContainer.lookup("#btnMsgModifier")).setOnAction((event) -> modifierMessage(message));
                ((Button) innerContainer.lookup("#btnMsgSupp")).setOnAction((event) -> supprimerMessage(message));

            } else {
                ((Button) innerContainer.lookup("#btnMsgModifier")).setVisible(false);
                ((Button) innerContainer.lookup("#btnMsgSupp")).setVisible(false);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return innerContainer;

    }

    public void modifierMessage(Message message) {

    }

    public void supprimerMessage(Message message) {

    }

}
