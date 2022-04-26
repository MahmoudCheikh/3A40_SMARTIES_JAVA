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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
    @FXML
    private Button retour;
    @FXML
    private VBox mainVbox;
    @FXML
    private AnchorPane ap;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelMessage.fxml"));
            innerContainer = loader.load();

            //  HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Label) innerContainer.lookup("#txtMsgDate")).setText("Posté le: " + message.getDate().toString());
            ((Label) innerContainer.lookup("#txtMsgUser")).setText("User : " + message.getIdUser());
            ((Label) innerContainer.lookup("#txtMsgContent")).setText("Contenu : " + message.getContenu());

            if (Smarties.user.getId() == message.getIdUser()) {
                ((Button) innerContainer.lookup("#btnMsgModifier")).setOnAction((event) -> modifierMessage(message));
                ((Button) innerContainer.lookup("#btnMsgSupp")).setOnAction((event) -> {
                    try {
                        supprimerMessage(message);
                    } catch (IOException ex) {
                        Logger.getLogger(GuiSujetFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

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

    public void supprimerMessage(Message message) throws IOException {
        messageService.supprimerMessage(message.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiSujetFront.fxml"));
        ScrollPane vbox = loader.load();
        mainVbox.getChildren().setAll(vbox);

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiForumFront.fxml"));
        GuiSujetFrontController.sujet = sujet;
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }

}
