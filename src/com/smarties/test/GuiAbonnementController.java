/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.itextpdf.text.DocumentException;
import com.smarties.entities.Abonnement;

import com.smarties.services.AbonnementService;
import com.smarties.entities.Location;

import com.smarties.services.LocationService;
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
public class GuiAbonnementController implements Initializable {

    private AbonnementService as = new AbonnementService();
    private LocationService loc = new LocationService();
    Alert alert = new Alert(Alert.AlertType.NONE);
    @FXML
    private TextField textIDAbonnement;
    @FXML
    private TextField textIDUserA;
    private TextField TextType;

    @FXML
    private TextField textPrixAb;
    @FXML
    private Button btnAjouterAb;
    @FXML
    private Button btnSupprimerAb;
    @FXML
    private Button btnModifierAb;
    @FXML
    private ListView<Abonnement> listAb;
    @FXML
    private TextField textIdLocation;

    @FXML
    private TextField TextHeureLoc;
    @FXML
    private TextField TextIdAbonne;
    @FXML
    private TextField TextIdUserLoc;
    @FXML
    private Button AjoutLoc;
    @FXML
    private Button suppLoc;
    @FXML
    private Button modifLoc;
    @FXML
    private ListView<Location> ListLoc;
    @FXML
    private TextField TextDuree;
    @FXML
    private DatePicker textDatePickLoc;
    @FXML

    private DatePicker datepickdeb;
    @FXML
    private DatePicker datepickfin;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private Label wrong;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private TextField findAb;
    @FXML
    private Button findType;
    @FXML
    private Button findPrix;
    @FXML
    private TextField findLoc;
    @FXML
    private Button findDuree;
    @FXML
    private Button findID;
    @FXML
    private Button triPrix;
    @FXML
    private Button triIDUSER;
    @FXML
    private Button triDuree;
    @FXML
    private Button triIdAb;
    @FXML
    private ImageView loop;
    @FXML
    private ImageView tri;
    @FXML
    private ImageView trii;
    @FXML
    private Button actuAB;
    @FXML
    private ImageView refresh;
    @FXML
    private Button actuLOC;
    @FXML
    private ImageView refreesh;
    @FXML
    private Button excel;
    @FXML
    private Button pdf;
    @FXML
    private ImageView imgg;
    @FXML
    private ImageView IMG;
    @FXML
    private ComboBox<String> TypeAbonCombo;
    private String[] typeAbonnement = {"VIP", "Gold", "Silver"};
    @FXML
    private PieChart pieChartt;
    @FXML
    private Button sttat;


    /*  Image myImage= new Image(getClass().getResourceAsStream("img1.jpg"));
   public void displayImage() {
  img1.setImage(myImage);
 }*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /* ArrayList al = (ArrayList) as.afficherAbonnement();
        listAb.getItems().addAll(al);*/

 /* ArrayList l = (ArrayList) loc.afficherLocation();
        ListLoc.getItems().addAll(l);*/
        TypeAbonCombo.getItems().addAll(typeAbonnement);
    }

    @FXML
    private void AjouterAbonnement(ActionEvent event) throws Exception {

        LocalDate today = LocalDate.now();

        if (textIDUserA.getText().isEmpty() || textPrixAb.getText().isEmpty() || TypeAbonCombo.getValue().isEmpty() || (datepickdeb.getValue().equals("")) || (datepickfin.getValue().equals(""))) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs  !");
            alert.showAndWait();

        }  else if (!(Pattern.matches("[0-9]*", textPrixAb.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le prix de l'abonnement  doit etre un entier  !");
            alert.showAndWait();
        } else if ((datepickdeb.getValue().getYear() > datepickfin.getValue().getYear()) || (datepickdeb.getValue().getMonthValue() > datepickfin.getValue().getMonthValue()) || (datepickdeb.getValue().getDayOfMonth() > datepickfin.getValue().getDayOfMonth())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre inferieur a la date fin !");
            alert.showAndWait();
        } else if (datepickdeb.getValue().isBefore(today)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date début doit etre supérieure à celle d'aujourd'hui ! ");
            alert.showAndWait();
        } else {
            Abonnement ab = new Abonnement();
            LocalDate datedeb = datepickdeb.getValue();
            LocalDate datefin = datepickfin.getValue();

            // int x1 = Integer.parseInt(textIDAbonnement.getText());
            int x2 = Integer.parseInt(textIDUserA.getText());
            int x3 = Integer.parseInt(textPrixAb.getText());

            //ab.setId(x1);
            ab.setId_user_id(x2);
            ab.setPrix(x3);
            ab.setDateD(datedeb);
            ab.setDateF(datefin);
            ab.setType(TypeAbonCombo.getValue());

            as.ajouterAbonnement(ab);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Abonnement ajouté avec succés!");
            alert.show();
            as.sendMail("fadwa.berrich@esprit.tn");

            ObservableList<Abonnement> items = FXCollections.observableArrayList();
            List<Abonnement> listabb = as.afficherAbonnement();
            for (Abonnement r : listabb) {
                String ch = r.toString();
                items.add(r);
            }
            listAb.setItems(items);
            /* ArrayList al = (ArrayList) as.afficherAbonnement();
        listAb.getItems().addAll(al);*/

            //textIDUserA.setText(" ");
            // textPrixAb.setText(" ");
            // TextType.setText(" ");
        }

    }

    @FXML
    private void SupprimerAbonnement(ActionEvent event) {
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
            textIDAbonnement.setText(" ");

            ObservableList<Abonnement> items = FXCollections.observableArrayList();
            List<Abonnement> listabb = as.afficherAbonnement();
            for (Abonnement r : listabb) {
                String ch = r.toString();
                items.add(r);
            }
            listAb.setItems(items);
        } else {
            ObservableList<Abonnement> items = FXCollections.observableArrayList();
            List<Abonnement> listabb = as.afficherAbonnement();
            for (Abonnement r : listabb) {
                String ch = r.toString();
                items.add(r);
            }
            listAb.setItems(items);
        }
    }

    @FXML
    private void ModifierAbonnement(ActionEvent event) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment modifier cet abonnement ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Abonnement ab = new Abonnement();
            LocalDate datedeb = datepickdeb.getValue();
            LocalDate datefin = datepickfin.getValue();
            ab.setDateD(datedeb);
            ab.setDateF(datefin);
            ab.setType(TypeAbonCombo.getValue());

            int x1 = Integer.parseInt(textIDAbonnement.getText());
            int x2 = Integer.parseInt(textIDUserA.getText());
            int x3 = Integer.parseInt(textPrixAb.getText());

            ab.setId(x1);
            ab.setId_user_id(x2);
            ab.setPrix(x3);

            as.modifierAbonnement(ab);
            ObservableList<Abonnement> items = FXCollections.observableArrayList();
            List<Abonnement> listabb = as.afficherAbonnement();
            for (Abonnement r : listabb) {
                String ch = r.toString();
                items.add(r);
            }
            listAb.setItems(items);
        } else {
            ObservableList<Abonnement> items = FXCollections.observableArrayList();
            List<Abonnement> listabb = as.afficherAbonnement();
            for (Abonnement r : listabb) {
                String ch = r.toString();
                items.add(r);
            }
            listAb.setItems(items);
        }

    }

    @FXML
    private void ChercherType(ActionEvent event) {

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
            findAb.setText(" ");
        }

    }

    @FXML
    private void ChercherPrix(ActionEvent event) {
        AbonnementService n = new AbonnementService();
        int price = Integer.parseInt(findAb.getText());
        List< Abonnement> R = n.ChercherPrix(price);
        if (R.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("  Desole !!  l'abonnement que vous cherchez ayant ce prix   n'existe pas!");
            alert.show();
        } else {

            ObservableList< Abonnement> datalist = FXCollections.observableArrayList(R);

            listAb.setItems(datalist);
            findAb.setText(" ");
        }

    }

    @FXML
    private void TrierPrix(ActionEvent event) {

        AbonnementService n = new AbonnementService();
        // int price = Integer.parseInt(findAb.getText());
        List< Abonnement> R = n.trierPrix();

        ObservableList< Abonnement> datalist = FXCollections.observableArrayList(R);

        listAb.setItems(datalist);
        // findAb.setText(" ");
    }

    @FXML
    private void trierIDUSER(ActionEvent event) {

        AbonnementService n = new AbonnementService();
        // int price = Integer.parseInt(findAb.getText());
        List< Abonnement> R = n.trierIDUSER();

        ObservableList< Abonnement> datalist = FXCollections.observableArrayList(R);

        listAb.setItems(datalist);

    }

    @FXML
    private void ActualiserAb(ActionEvent event) {

        ObservableList<Abonnement> items = FXCollections.observableArrayList();
        List<Abonnement> listabb = as.afficherAbonnement();
        for (Abonnement r : listabb) {
            String ch = r.toString();
            items.add(r);
        }
        listAb.setItems(items);
    }

//*******************************************************LOCATION*****************************************************************//
    @FXML
    private void AjouterLoaction(ActionEvent event) {

        Location loc1 = new Location();
        LocalDate today = LocalDate.now();

        if (TextIdUserLoc.getText().isEmpty() || TextIdAbonne.getText().isEmpty() || TextDuree.getText().isEmpty() || (textDatePickLoc.getValue().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs !");
            alert.showAndWait();
        } else if (textDatePickLoc.getValue().isBefore(today)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date doit etre supérieure à celle d'aujourd'hui ! ");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", TextDuree.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La durée de location  doit etre un entier  !");
            alert.showAndWait();
        } else {
            LocalDate date = textDatePickLoc.getValue();
            loc1.setDate(date);
            loc1.setHeure(TextHeureLoc.getText());

            //  int x1 = Integer.parseInt(textIdLocation.getText());
            int x2 = Integer.parseInt(TextIdUserLoc.getText());
            int x3 = Integer.parseInt(TextIdAbonne.getText());
            Float x4 = Float.parseFloat(TextDuree.getText());
            //loc1.setId(x1);
            loc1.setIdUser(x2);
            loc1.setIdAbonnement(x3);
            loc1.setDuree(x4);
            //  System.out.println(loc.getCaptcha());
            loc.ajouterLocation(loc1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Location ajoutée avec succés!");
            alert.show();
            ObservableList<Location> items = FXCollections.observableArrayList();
            List<Location> listLOC = loc.afficherLocation();
            for (Location r : listLOC) {
                String ch = r.toString();
                items.add(r);
            }
            ListLoc.setItems(items);
        }
    }

    @FXML
    private void SupprimerLocation(ActionEvent event) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer cette location  ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            int id = Integer.parseInt(textIdLocation.getText());
            loc.supprimerLocation(id);
            ObservableList<Location> items = FXCollections.observableArrayList();
            List<Location> listLOC = loc.afficherLocation();
            for (Location r : listLOC) {
                String ch = r.toString();
                items.add(r);
            }
            ListLoc.setItems(items);
        } else {
            ObservableList<Location> items = FXCollections.observableArrayList();
            List<Location> listLOC = loc.afficherLocation();
            for (Location r : listLOC) {
                String ch = r.toString();
                items.add(r);
            }
            ListLoc.setItems(items);
        }

    }

    @FXML
    private void ModifierLocation(ActionEvent event) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment modifier cette location ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            Location loc1 = new Location();

            LocalDate date = textDatePickLoc.getValue();
            loc1.setDate(date);
            loc1.setHeure(TextHeureLoc.getText());

            int x1 = Integer.parseInt(textIdLocation.getText());
            int x2 = Integer.parseInt(TextIdUserLoc.getText());
            int x3 = Integer.parseInt(TextIdAbonne.getText());
            Float x4 = Float.parseFloat(TextDuree.getText());

            loc1.setId(x1);
            loc1.setIdUser(x2);
            loc1.setIdAbonnement(x3);
            loc1.setDuree(x4);
            loc.modifierLocation(loc1);
            ObservableList<Location> items = FXCollections.observableArrayList();
            List<Location> listLOC = loc.afficherLocation();
            for (Location r : listLOC) {
                String ch = r.toString();
                items.add(r);
            }
            ListLoc.setItems(items);
        } else {
            ObservableList<Location> items = FXCollections.observableArrayList();
            List<Location> listLOC = loc.afficherLocation();
            for (Location r : listLOC) {
                String ch = r.toString();
                items.add(r);
            }
            ListLoc.setItems(items);
        }

    }

    @FXML
    private void ChercherDuree(ActionEvent event) {

        LocationService n = new LocationService();
      float Duree = Float.parseFloat(findLoc.getText());
        List< Location> R = n.ChercherDuree(Duree);
        if (R.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("  Desole !!  la location  que vous cherchez ayant cette duree  n'existe pas!");
            alert.show();
        } else {

            ObservableList< Location> datalist = FXCollections.observableArrayList(R);

            ListLoc.setItems(datalist);
            findLoc.setText(" ");
        }
    }

    @FXML
    private void ChercherID(ActionEvent event) {
        LocationService n = new LocationService();
        int abon = Integer.parseInt(findLoc.getText());
        List< Location> R = n.ChercherID(abon);
        if (R.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("  Desole !!  la location  que vous cherchez ayant cet identifiant d'abonnement n'existe pas!");
            alert.show();
        } else {
            ObservableList< Location> datalist = FXCollections.observableArrayList(R);

            ListLoc.setItems(datalist);
            findLoc.setText(" ");
        }
    }

    @FXML
    private void TrierDuree(ActionEvent event) {

        LocationService n = new LocationService();

        List< Location> R = n.TrierDuree();

        ObservableList< Location> datalist = FXCollections.observableArrayList(R);

        ListLoc.setItems(datalist);

    }

    @FXML
    private void TrierIdAB(ActionEvent event) {
        LocationService n = new LocationService();

        List< Location> R = n.TrierIdAB();

        ObservableList< Location> datalist = FXCollections.observableArrayList(R);

        ListLoc.setItems(datalist);
    }

    @FXML
    private void ActualiserLoc(ActionEvent event) {

        ObservableList<Location> items = FXCollections.observableArrayList();
        List<Location> listLOC = loc.afficherLocation();
        for (Location r : listLOC) {
            String ch = r.toString();
            items.add(r);
        }
        ListLoc.setItems(items);
    }

    @FXML
    private void generExcel(ActionEvent event) {

        as.generateExcel();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("fichiier importer   !");
        alert.showAndWait();
      

    }

    @FXML
    private void generatePDF(ActionEvent event) throws DocumentException {
        loc.GeneratePDF();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("fichiier importer en pdf   !");
        alert.showAndWait();
    }

    @FXML
    private void getListeAb(MouseEvent event) {
          Abonnement abo = new Abonnement();
          listAb.setOnMouseClicked((event1) -> {
            int selectedIdUser = listAb.getSelectionModel().getSelectedItem().getId_user_id();
            String selectedType = listAb.getSelectionModel().getSelectedItem().getType();
            LocalDate selectedDated = listAb.getSelectionModel().getSelectedItem().getDateD();
            LocalDate selectedDatef = listAb.getSelectionModel().getSelectedItem().getDateF();
            int selectedPrix = listAb.getSelectionModel().getSelectedItem().getPrix();
            int selectedId = listAb.getSelectionModel().getSelectedItem().getId();

            TypeAbonCombo.setValue(selectedType);
            textIDUserA.setText(String.valueOf(selectedIdUser));
            textIDAbonnement.setText(String.valueOf(selectedId));
            datepickdeb.setValue(selectedDated);
            datepickfin.setValue(selectedDatef);
            textPrixAb.setText(String.valueOf(selectedPrix));
        });
    }

    @FXML
    private void GetListLoc(MouseEvent event) {
           Location loc = new Location();
          ListLoc.setOnMouseClicked((event1) -> {
            int selectedIdUser = ListLoc.getSelectionModel().getSelectedItem().getIdUser();
            String selectedHeure = ListLoc.getSelectionModel().getSelectedItem().getHeure();
            LocalDate selectedDate = ListLoc.getSelectionModel().getSelectedItem().getDate();
             float selectedDuree = ListLoc.getSelectionModel().getSelectedItem().getDuree();
            int selectedIDABON = ListLoc.getSelectionModel().getSelectedItem().getIdAbonnement();
            int selectedId = ListLoc.getSelectionModel().getSelectedItem().getId();

           
            TextIdUserLoc.setText(String.valueOf(selectedIdUser));
            textIdLocation.setText(String.valueOf(selectedId));
             textDatePickLoc.setValue(selectedDate);
             TextDuree.setText(String.valueOf(selectedDuree));
              TextIdAbonne.setText(String.valueOf(selectedIDABON));
              TextHeureLoc.setText(selectedHeure);
              
        });
        
        
    }

    @FXML
    private void StatiistiqueAB(ActionEvent event) {
    }

}
