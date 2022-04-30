/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Message;
import com.smarties.entities.Sujet;
import com.smarties.entities.Users;
import com.smarties.services.MessageService;
import com.smarties.services.UsersService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiSujetFrontController implements Initializable {

    static Sujet sujet;
    MessageService messageService = new MessageService();
    UsersService usersService = new UsersService();

    @FXML
    private Label txtUser;
    @FXML
    private Text txtContenu;
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
    @FXML
    private Button retour;
    @FXML
    private VBox mainVbox;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Users user = new Users();

        try {
            user = usersService.getById(this.sujet.getUserId());
        } catch (SQLException ex) {
        }

        txtUser.setText("Posté par : " + user.getNom() + " " + user.getPrenom());
        txtDate.setText("Posté le " + sujet.getDate().toString());
        txtTitre.setText("Titre : " + sujet.getTitre());
        txtContenu.setWrappingWidth(550);
        txtContenu.setText(sujet.getContenu());

        List<Message> listMessage = messageService.afficherMessage();

        if (!listMessage.isEmpty()) {
            listMessage.stream().filter((message) -> (message.getIdSujet() == GuiSujetFrontController.sujet.getId())).forEachOrdered((message) -> {
                try {
                    paneMessage.getChildren().add(makeMessage(message));
                } catch (SQLException ex) {
                }
            });
        } else {

        }
        paneScroll.setContent(paneMessage);

    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public Parent makeMessage(Message message) throws SQLException {
        Parent innerContainer = null;

        try {

            Users user = new Users();

            user = usersService.getById(message.getIdUser());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelMessage.fxml"));
            innerContainer = loader.load();

            ((Label) innerContainer.lookup("#txtMsgDate")).setText("Posté le: " + message.getDate().toString());

            ((Label) innerContainer.lookup("#txtMsgUser")).setText("Posté par : " + user.getNom() + " " + user.getPrenom());

            ((Text) innerContainer.lookup("#txtMsgContent")).setText("Contenu : " + message.getContenu());

            if (Smarties.user.getId() == message.getIdUser()) {
                if (Smarties.user.getImage().equals("ban")) {
                    ajouter.setText("banni");
                    ajouter.setOnAction((event) -> {
                    });
                    ((Button) innerContainer.lookup("#btnMsgModifier")).setText("banni");
                    ((Button) innerContainer.lookup("#btnMsgSupp")).setText("banni");
                } else {
                    ((Button) innerContainer.lookup("#btnMsgModifier")).setOnAction((event) -> {
                        try {
                            modifierMessage(message);
                        } catch (IOException ex) {

                        }
                    });
                    ((Button) innerContainer.lookup("#btnMsgSupp")).setOnAction((event) -> {
                        try {
                            supprimerMessage(message);
                        } catch (IOException ex) {

                        }
                    });
                }

            } else {
                ((Button) innerContainer.lookup("#btnMsgModifier")).setVisible(false);
                ((Button) innerContainer.lookup("#btnMsgSupp")).setVisible(false);
            }

        } catch (IOException ex) {
        }
        return innerContainer;

    }

    public void modifierMessage(Message message) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiMessageFrontUpdate.fxml"));
            GuiMessageFrontUpdateController.sujet = this.sujet;
            GuiMessageFrontUpdateController.message = message;
            AnchorPane vbox = loader.load();
            ap.getChildren().setAll(vbox);
        }
    }

    public void supprimerMessage(Message message) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre action");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            messageService.supprimerMessage(message.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiSujetFront.fxml"));
            AnchorPane vbox = loader.load();
            ap.getChildren().setAll(vbox);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiForumFront.fxml"));
        GuiSujetFrontController.sujet = sujet;
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiMessageFrontAjouter.fxml"));
        GuiMessageFrontAjouterController.sujet = this.sujet;
        AnchorPane vbox = loader.load();
        ap.getChildren().setAll(vbox);
    }

}
