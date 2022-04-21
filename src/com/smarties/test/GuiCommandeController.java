/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.itextpdf.text.DocumentException;

import com.smarties.entities.Commande;
import com.smarties.services.AchatService;
import com.smarties.entities.Achat;
import com.smarties.services.CommandeService;
import com.smarties.services.MessageService;
import com.smarties.services.ProduitService;
import com.smarties.services.UsersService;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ahmed Elmoez
 */
public class GuiCommandeController implements Initializable {

    private CommandeService cs = new CommandeService();
    private AchatService sa = new AchatService();
    UsersService us = new UsersService();
    MessageService ms = new MessageService();
    ProduitService pr = new ProduitService();

    @FXML
    private TextField txtnbrproduit;
    @FXML
    private ListView<Commande> listcommande;
    @FXML
    private Button btnajouterc;
    @FXML
    private Button btnupdatec;
    @FXML
    private Button btndeletec;
    private TextField txtiduser;
    @FXML
    private TextField txtid;
    @FXML
    private Tab btndeleteachat;
    @FXML
    private Button btnajouterachat;
    private TextField txtidUser;
    @FXML
    private TextField txtnomclient;
    @FXML
    private TextField txtnumclient;
    @FXML
    private Button btnupdateachat;
    private TextField txtidproduit;
    @FXML
    private ListView<Achat> listachat;
    @FXML
    private TextField idachat;
    @FXML
    private Button btndeleteachatx;
    @FXML
    private DatePicker dateachatpik;
    @FXML
    private Button recherche;
    @FXML
    private TextField rechc;
    @FXML
    private Button triid;
    @FXML
    private Button actc;
    @FXML
    private Button actac;
    @FXML
    private Button trierachat;
    @FXML
    private Button pdfgen;
    @FXML
    private ComboBox<String> comboComm;
    @FXML
    private ComboBox<String> comboCommProd;
    @FXML
    private ComboBox<String> comboAchat;
    @FXML
    private ComboBox<String> comboAchatProd;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList al = (ArrayList) cs.afficherCommande();
        listcommande.getItems().addAll(al);

        ArrayList a2 = (ArrayList) sa.afficherAchat();
        listachat.getItems().addAll(a2);
        
        
        comboComm.setItems(FXCollections.observableArrayList(cs.comboComm()));
        comboCommProd.setItems(FXCollections.observableArrayList(cs.comboCommProd()));
        
         comboAchat.setItems(FXCollections.observableArrayList(sa.comboAchat()));
        comboAchatProd.setItems(FXCollections.observableArrayList(sa.comboAchatProd()));

        

// TODO
    }
    

    @FXML
    private void ajouterc(ActionEvent event) throws SQLException {
        if ((comboComm.getValue().contentEquals("id_user_id")) || (txtnbrproduit.getText().equals(""))|| (comboCommProd.getValue().contentEquals("libelle"))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        
        }  else if (!(Pattern.matches("[0-9]*", txtnbrproduit.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nombre de produit doit etre de type Int !");
            alert.showAndWait();
        } 
        else {
            Commande c = new Commande();

            int x2 = Integer.parseInt(txtnbrproduit.getText());
            
            c.setIdUser(us.searchByMail(comboComm.getValue()));
            c.setIdProduit(pr.searchByLib(comboCommProd.getValue()));

            c.setNbProduits(x2);

            cs.ajouterCommande(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("commande ajoutée!");
            alert.show();
            ObservableList<Commande> items = FXCollections.observableArrayList();
            List<Commande> listcomm = cs.afficherCommande();
            for (Commande r : listcomm) {
                String ch = r.toString();
                items.add(r);
            }

            listcommande.setItems(items);
        }

    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        if ( (comboComm.getValue().equals("")) || (txtnbrproduit.getText().equals("")) || (comboCommProd.getValue().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", txtnbrproduit.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nombre de produit doit etre de type Int !");
            alert.showAndWait();
        } else {
            Commande c = new Commande();


            int x2 = Integer.parseInt(txtnbrproduit.getText());
            c.setIdUser(us.searchByMail(comboComm.getValue()));
            c.setIdProduit(pr.searchByLib(comboCommProd.getValue()));

            c.setNbProduits(x2);

            int id = Integer.parseInt(txtid.getText());
            c.setId(id);

            System.out.println(c);

            cs.modifierCommande(c);
            ObservableList<Commande> items = FXCollections.observableArrayList();
            List<Commande> listcomm = cs.afficherCommande();
            for (Commande r : listcomm) {
                String ch = r.toString();
                items.add(r);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();
        }
    }

    @FXML
    private void deletec(ActionEvent event) {
        int id = Integer.parseInt(txtid.getText());
        cs.supprimerCommande(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Commande supprimé!");
        alert.show();
        ObservableList<Commande> items = FXCollections.observableArrayList();
        List<Commande> listcomm = cs.afficherCommande();
        for (Commande r : listcomm) {
            String ch = r.toString();
            items.add(r);
        }
        listcommande.setItems(items);
    }

    @FXML
    private void Rechercher(ActionEvent event) {

        CommandeService n = new CommandeService();
        List<Commande> R = n.Rechercher(rechc.getText());

        ObservableList<Commande> datalist = FXCollections.observableArrayList(R);

        listcommande.setItems(datalist);

    }

    @FXML
    private void triercommande(ActionEvent event) {
        CommandeService n = new CommandeService();
        // int price = Integer.parseInt(findAb.getText());
        List< Commande> R = n.triercommande();

        ObservableList< Commande> datalist = FXCollections.observableArrayList(R);

        listcommande.setItems(datalist);
    }

    @FXML
    private void actcommande(ActionEvent event) {
        ObservableList<Commande> items = FXCollections.observableArrayList();
        List<Commande> listcomm = cs.afficherCommande();
        for (Commande r : listcomm) {
            String ch = r.toString();
            items.add(r);
        }
        listcommande.setItems(items);
    }

    /* LISTVIEW**********************************************************
     */
    @FXML
    private void getDataCommande(MouseEvent event) {

        Commande com = new Commande();
        listcommande.setOnMouseClicked((event1) -> {
            //int selectedIdComm = listcommande.getSelectionModel().getSelectedItem().getId();
            //int selectedIdProd = listcommande.getSelectionModel().getSelectedItem().getIdProduit();
            int selectedNbProd = listcommande.getSelectionModel().getSelectedItem().getNbProduits();
            //int selectedIduser = listcommande.getSelectionModel().getSelectedItem().getIdUser();

            //txtid.setText(String.valueOf(selectedIdComm));
            //txtProduit.setText(String.valueOf(selectedIdProd));
            txtnbrproduit.setText(String.valueOf(selectedNbProd));
            //txtiduser.setText(String.valueOf(selectedIduser));
        });
    }

    @FXML
    private void pdf(ActionEvent event) throws DocumentException {
        cs.Gpdf();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("fichiier importer en pdf   !");
        alert.showAndWait();
    }

    @FXML
    private void test(ActionEvent event) {
        comboComm.setItems(FXCollections.observableArrayList(ms.getCombo()));
    }

    /*---------------------Achat-----------------------------*/
    @FXML
    private void ajoutachat(ActionEvent event) throws SQLException {
        LocalDate now = LocalDate.now();
        if ((comboAchat.getValue().equals("")) || (comboAchatProd.getValue().equals("")) || (dateachatpik.getValue().equals("")) || (txtnumclient.getText().equals("")) || (txtnomclient.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtnomclient.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nom de Client doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[0-9]*", txtnumclient.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("numero de client doit etre de type Int !");
            alert.showAndWait();
        } else if (dateachatpik.getValue().isBefore(now)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre supérieur a celle d'aujourd'hui ! ");
            alert.showAndWait();
        } else {

            Achat a = new Achat();
            //int xx1 = Integer.parseInt(txtidUser.getText());
            //int xx2 = Integer.parseInt(txtidproduit.getText());

            LocalDate date = dateachatpik.getValue();

            int xx3 = Integer.parseInt(txtnumclient.getText());

            a.setNomClient(txtnomclient.getText());
            a.setIdUser(us.searchByMail(comboAchat .getValue()));
            a.setIdProduit(pr.searchByLib(comboAchatProd .getValue()));
            //a.setIdProduit(xx2);
            //a.setIdUser(xx1);
            a.setDate(date);
            a.setNumeroClient(xx3);

            sa.ajouterAchat(a);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Achat ajouté!");
            alert.show();
            ObservableList<Achat> items = FXCollections.observableArrayList();
            List<Achat> listachh = sa.afficherAchat();
            for (Achat r : listachh) {
                String ch = r.toString();
                items.add(r);
            }

            listachat.setItems(items);

        }
    }

    @FXML
    private void updateachat(ActionEvent event) throws SQLException {
        LocalDate now = LocalDate.now();
        if ((comboAchat.getValue().equals("")) || (comboAchatProd.getValue().equals("")) || (dateachatpik.getValue().equals("")) || (txtnumclient.getText().equals("")) || (txtnomclient.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", txtnomclient.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nom de Client doit etre de type String !");
            alert.showAndWait();

        } else if (!(Pattern.matches("[0-9]*", txtnumclient.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("numero de client doit etre de type Int !");
            alert.showAndWait();
        } else if (dateachatpik.getValue().isBefore(now)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre supérieur a celle d'aujourd'hui ! ");
            alert.showAndWait();
        } else {

            Achat a = new Achat();

            
             a.setIdUser(us.searchByMail(comboAchat.getValue()));
            a.setIdProduit(pr.searchByLib(comboAchatProd.getValue()));

            //int xx1 = Integer.parseInt(txtidproduit.getText());

            //int xx2 = Integer.parseInt(txtidUser.getText());

            int xx3 = Integer.parseInt(txtnumclient.getText());

            LocalDate date = dateachatpik.getValue();

            //a.setIdProduit(xx1);
            //a.setIdUser(xx2);
            a.setNumeroClient(xx3);
            a.setDate(date);
            a.setNomClient(txtnomclient.getText());

            int id = Integer.parseInt(idachat.getText());

            a.setId(id);

            System.out.println(a);

            sa.modifierAchat(a);

            ObservableList<Achat> items = FXCollections.observableArrayList();
            List<Achat> listachh = sa.afficherAchat();
            for (Achat r : listachh) {
                String ch = r.toString();
                items.add(r);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();

        }
    }

    @FXML
    private void deleteachat(ActionEvent event) {
        int id = Integer.parseInt(idachat.getText());
        sa.supprimerAchat(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Evenement supprimé!");
        alert.show();
        ObservableList<Achat> items = FXCollections.observableArrayList();
        List<Achat> listachh = sa.afficherAchat();
        for (Achat r : listachh) {
            String ch = r.toString();
            items.add(r);
        }

        listachat.setItems(items);
    }

    @FXML
    private void actachat(ActionEvent event) {
        ObservableList<Achat> items = FXCollections.observableArrayList();
        List<Achat> listachh = sa.afficherAchat();
        for (Achat r : listachh) {
            String ch = r.toString();
            items.add(r);
        }
        listachat.setItems(items);
    }

    @FXML
    private void getDataAchat(MouseEvent event) {
        listachat.setOnMouseClicked((event1) -> {
            //int selectedIdComm = listcommande.getSelectionModel().getSelectedItem().getId();
            //int selectedIdProd = listachat.getSelectionModel().getSelectedItem().getIdProduit();
            int selectedNumCli = listachat.getSelectionModel().getSelectedItem().getNumeroClient();
            //int selectedIduser = listachat.getSelectionModel().getSelectedItem().getIdUser();
            LocalDate selectedDate = listachat.getSelectionModel().getSelectedItem().getDate();
            String selectedNomCli = listachat.getSelectionModel().getSelectedItem().getNomClient();

            //txtid.setText(String.valueOf(selectedIdComm));
            //txtidproduit.setText(String.valueOf(selectedIdProd));
            //txtidUser.setText(String.valueOf(selectedIduser));
            txtnumclient.setText(String.valueOf(selectedNumCli));
            //dateachatpik.setValue(String.valueOf(selectedDate));
            txtnomclient.setText(selectedNomCli);

        });
    }

    @FXML
    private void trierachatid(ActionEvent event) {
        AchatService n = new AchatService();
        // int price = Integer.parseInt(findAb.getText());
        List< Achat> R = n.trierachatid();

        ObservableList< Achat> datalist = FXCollections.observableArrayList(R);

        listachat.setItems(datalist);
    }

}
