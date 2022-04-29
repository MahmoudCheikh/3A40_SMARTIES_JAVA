/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;
 

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import javafx.fxml.FXML;
import com.itextpdf.text.DocumentException;
import com.smarties.entities.Abonnement;
import java.util.Scanner;
import javax.sound.sampled.*;

import com.smarties.services.AbonnementService;
import com.smarties.entities.Location;

import com.smarties.services.LocationService;
import com.smarties.services.UsersService;
import com.smarties.tools.MaConnexion;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.captcha.Captcha;
//import captcha.Captcha;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GuiAbonnementFrontController implements Initializable {

    private AbonnementService as = new AbonnementService();
    private LocationService loc = new LocationService();
    Alert alert = new Alert(Alert.AlertType.NONE);
 
   

    private TextField textIDAbonnementFront;
    @FXML
    private Button btnAjouterAbFront;
    @FXML
    private Button btnSupprimerAb;
    @FXML
    private ListView<Abonnement> listAb;

    @FXML
    private DatePicker datepickdebF;
    @FXML
    private DatePicker datepickfinF;
    @FXML
    private TextField findAb;
    @FXML
    private Button findType;
    @FXML
    private ImageView loop;
    @FXML
    private ComboBox<String> TypeAbonComboFront;
    private String[] typeAbonnement = {"VIP", "Gold", "Silver"};
    @FXML
    private Label wrong;
    @FXML
    private TextField mailAbonne;
   
    @FXML
    private ImageView captchaIV;
    @FXML
    private TextField captchaTF;
      Captcha captcha;
    @FXML
    private TextField textIDAbonnement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeAbonComboFront.getItems().addAll(typeAbonnement);
         ObservableList<Abonnement> items = FXCollections.observableArrayList();
        List<Abonnement> listabb = as.afficherAbonnement();
        for (Abonnement r : listabb) {
            System.out.println(Smarties.user.getId() + " " + r.getId_user_id());
            if (Smarties.user.getId() == r.getId_user_id()) {
                String ch = r.toString();
                items.add(r);
            }
            
        captcha = setCaptcha();
        }
        listAb.setItems(items);
        
       
    }
     public Captcha setCaptcha() {
        Captcha captcha = new Captcha.Builder(250, 200)
                .addText()
                .addBackground()
                .addNoise()
                .gimp()
                .addBorder()
                .build();

        System.out.println(captcha.getImage());
        Image image = SwingFXUtils.toFXImage(captcha.getImage(), null);

        captchaIV.setImage(image);

        return captcha;
    }
     

    @FXML
    private void AjouterAbonnementFront(ActionEvent event) throws Exception {
        LocalDate today = LocalDate.now();
        Abonnement ab = new Abonnement();
        LocalDate datedeb = datepickdebF.getValue();
        LocalDate datefin = datepickfinF.getValue();
        ab.setDateD(datedeb);
        ab.setDateF(datefin);
        ab.setType(TypeAbonComboFront.getValue());
        if (!captcha.isCorrect(captchaTF.getText())) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Captcha inncorrect ");
            alert.show();
            captcha = setCaptcha();
            captchaTF.setText("");
          
        }
        else if ((datepickdebF.getValue().getYear() > datepickfinF.getValue().getYear()) || (datepickdebF.getValue().getMonthValue() > datepickfinF.getValue().getMonthValue()) || (datepickdebF.getValue().getDayOfMonth() > datepickfinF.getValue().getDayOfMonth())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre inferieur a la date fin !");
            alert.showAndWait();
        } else if (datepickdebF.getValue().isBefore(today)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date début doit etre supérieure à celle d'aujourd'hui ! ");
            alert.showAndWait();
        }else {
            if (TypeAbonComboFront.getValue().equalsIgnoreCase("VIP"))
            {ab.setPrix(100);}
            else if(TypeAbonComboFront.getValue().equalsIgnoreCase("Gold"))
                 {ab.setPrix(50);}
             else if(TypeAbonComboFront.getValue().equalsIgnoreCase("Silver"))
                 {ab.setPrix(25);}
        as.ajouterAbonnement(ab);
         as.sendMail(mailAbonne.getText());
        ObservableList<Abonnement> items = FXCollections.observableArrayList();
        List<Abonnement> listabb = as.afficherAbonnement();
        for (Abonnement r : listabb) {
            System.out.println(Smarties.user.getId() + " " + r.getId_user_id());
            if (Smarties.user.getId() == r.getId_user_id()) {
                String ch = r.toString();
                items.add(r);
            }
        }
        listAb.setItems(items);
   
        
        }
      


   // Replace these placeholders with your Account Sid and Auth Token
 /* String ACCOUNT_SID = "AC1bfc52d30073068147b27bcfeae02c20";
 String AUTH_TOKEN = "626f9f30ef57e99f0239c2c46bff15e8";

  
       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       Message message;
        message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+17579095719 "),
                new com.twilio.type.PhoneNumber("whatsapp:+21623251728"),
                "Hello from your friendly neighborhood Java application!")
                .create();*/
   


    }

    @FXML
    private void SupprimerAbonnementFront(ActionEvent event) {
         //int selectedId = listAb.getSelectionModel().getSelectedItem().getId();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer cet abonnement ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
        
              int id = Integer.parseInt(textIDAbonnement.getText());
            as.supprimerAbonnement(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Abonnement supprimé avec succés!");
            alert.show();
           

            ObservableList<Abonnement> items = FXCollections.observableArrayList();
        List<Abonnement> listabb = as.afficherAbonnement();
        for (Abonnement r : listabb) {
            System.out.println(Smarties.user.getId() + " " + r.getId_user_id());
            if (Smarties.user.getId() == r.getId_user_id()) {
                String ch = r.toString();
                items.add(r);
            }
        }
        listAb.setItems(items);
        } else {
            ObservableList<Abonnement> items = FXCollections.observableArrayList();
        List<Abonnement> listabb = as.afficherAbonnement();
        for (Abonnement r : listabb) {
            System.out.println(Smarties.user.getId() + " " + r.getId_user_id());
            if (Smarties.user.getId() == r.getId_user_id()) {
                String ch = r.toString();
                items.add(r);
            }
        }
        listAb.setItems(items);
        }
        
    }

    @FXML
    private void getListeAb(MouseEvent event) {
        int selectedId = listAb.getSelectionModel().getSelectedItem().getId();
          textIDAbonnement.setText(String.valueOf(selectedId));
    }


    @FXML
    private void ChercherType(ActionEvent event) {
         if (findAb.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir  le champs  !");
            alert.showAndWait();
            
            
        } else {
            AbonnementService n = new AbonnementService();

            List< Abonnement> R = n.ChercherType(findAb.getText());
            if (R.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("  Desole !!  l'abonnement que vous cherchez ayant ce type   n'existe pas!");
                alert.show();
            } else {

                ObservableList< Abonnement> datalist = FXCollections.observableArrayList(R);

                listAb.setItems(datalist);
                findAb.setText("");
            }

        }
    }


   
   

    
 
     
    

}
