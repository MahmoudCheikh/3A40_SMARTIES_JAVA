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
import javafx.scene.control.DatePicker;
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
import com.smarties.entities.Location;
import com.smarties.services.AbonnementService;
import java.util.Scanner;
import javax.sound.sampled.*;


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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GuiLocationFrontController implements Initializable {
    private AbonnementService as = new AbonnementService();
    private LocationService loc = new LocationService();
    Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML
    private TextField TextHeureLoc;
    @FXML
    private Button AjoutLoc;
    @FXML
    private Button suppLoc;
    @FXML
    private ListView<Location> ListLoc;
    @FXML
    private TextField TextDuree;
    @FXML
    private DatePicker textDatePickLoc;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img8;
    @FXML
    private TextField findLoc;
    @FXML
    private Button findDuree;
    @FXML
    private ImageView loop;
    @FXML
    private Button actuLOC;
    @FXML
    private ImageView refreesh;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Location> items = FXCollections.observableArrayList();
            List<Location> listLOC = loc.afficherLocation();
            for (Location r : listLOC) {
                 // System.out.println(Smarties.user.getId() + " " + r.getId_user_id());
                 for(Abonnement a :as.getidabonnement())
            if (a.getId() == r.getIdAbonnement()) {
                String ch = r.toString();
                items.add(r);
            }
                String ch = r.toString();
                items.add(r);
            }
            ListLoc.setItems(items);
        // TODO
    }    

    @FXML
    private void AjouterLoaction(ActionEvent event) {
         Location loc1 = new Location();
         LocalDate date = textDatePickLoc.getValue();
            loc1.setDate(date);
            loc1.setHeure(TextHeureLoc.getText());
              Float x4 = Float.parseFloat(TextDuree.getText());
               loc1.setDuree(x4);
                loc.ajouterLocation(loc1);
    }

    @FXML
    private void SupprimerLocation(ActionEvent event) {
    }

    @FXML
    private void GetListLoc(MouseEvent event) {
    }

    @FXML
    private void ChercherDuree(ActionEvent event) {
    }

    @FXML
    private void ActualiserLoc(ActionEvent event) {
    }

    @FXML
    private void generatePDF(ActionEvent event) {
    }
    
}
