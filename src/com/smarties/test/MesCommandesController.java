/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.itextpdf.text.DocumentException;
import com.smarties.entities.Achat;
import com.smarties.entities.Commande;
import com.smarties.services.AchatService;
import com.smarties.services.CommandeService;
import com.smarties.services.MessageService;
import com.smarties.services.ProduitService;
import com.smarties.services.UsersService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Ahmed Elmoez
 */
public class MesCommandesController implements Initializable {

    private CommandeService cs = new CommandeService();
    private AchatService sa = new AchatService();
    UsersService us = new UsersService();
    MessageService ms = new MessageService();
    ProduitService pr = new ProduitService();

    Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML
    private TextField txtnbrproduitfront;
    @FXML
    private ListView<Commande> listcommandefront;
    @FXML
    private Button btnajouterc;
    @FXML
    private Button btnupdatec;
    @FXML
    private Button btndeletec;
    @FXML
    private TextField txtidfront;
    @FXML
    private Button recherche;
    @FXML
    private TextField rechcfront;
    @FXML
    private Button triid;
    @FXML
    private Button actc;
    @FXML
    private Button pdfgen;
    @FXML
    private ComboBox<String> comboCommProdfront;
    @FXML
    private Tab btndeleteachat;
    @FXML
    private ListView<Achat> listachatfront;
    @FXML
    private Button actac;
    @FXML
    private Button trierachat;
    @FXML
    private Button versachat;
    @FXML
    private TextField txtidcom;
    @FXML
    private TextField txtidprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList al = (ArrayList) cs.afficherCommande();
        listcommandefront.getItems().addAll(al);

        ArrayList a2 = (ArrayList) sa.afficherAchat();
        listachatfront.getItems().addAll(a2);
        // TODO
        comboCommProdfront.setItems(FXCollections.observableArrayList(cs.comboCommProd()));
         //Pour L'affichge

        
    }
    
    

    
    @FXML
    private void getDataCommande(MouseEvent event) {
        Commande com = new Commande();
        listcommandefront.setOnMouseClicked((event1) -> {

            int selectedNbProd = listcommandefront.getSelectionModel().getSelectedItem().getNbProduits();
             int selectedidcom = listcommandefront.getSelectionModel().getSelectedItem().getId();
              int selectedidprod = listcommandefront.getSelectionModel().getSelectedItem().getIdProduit();
              int selectedcommande = listcommandefront.getSelectionModel().getSelectedItem().getId();
              //int selectdcombo = listcommandefront.getSelectionModel().getSelectedItem().getIdProduit();

                      

            txtnbrproduitfront.setText(String.valueOf(selectedNbProd));
            txtidcom.setText(String.valueOf(selectedidcom));
            txtidprod.setText(String.valueOf(selectedidprod));
            txtidfront.setText(String.valueOf(selectedcommande));
           // selectdcombo.setText(String.valueOf(comboCommProdfront));

        });
    }

    @FXML
    private void ajouterc(ActionEvent event) throws SQLException, Exception {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment ajouter cet commande Monsieur/Mme");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (txtnbrproduitfront.getText().equals("") || comboCommProdfront.getValue().equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0-9]*", txtnbrproduitfront.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le quantite de porduit doit etre de type Int !");
                alert.showAndWait();
            } else {
                Commande c = new Commande();
                    
                int x2 = Integer.parseInt(txtnbrproduitfront.getText());
 
        
                //c.setIdUser(us.searchByMail(comboComm.getValue()));
                c.setIdProduit(pr.searchByLib(comboCommProdfront.getValue()));

                c.setNbProduits(x2);
                c.setIdUser(Smarties.user.getId());
                cs.ajouterCommande(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("ajout effectué avec succé , un mail sera envoyé!");
                alert.show();
                cs.sendMail("hazem.rjeibi@esprit.tn");


            }
        }
    
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        if ((txtnbrproduitfront.getText().equals("")) || (comboCommProdfront.getValue().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", txtnbrproduitfront.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nombre de produit doit etre de type Int !");
            alert.showAndWait();
        } else {
            Commande c = new Commande();

            int x2 = Integer.parseInt(txtnbrproduitfront.getText());
            //c.setIdUser(us.searchByMail(comboComm.getValue()));
            c.setIdProduit(pr.searchByLib(comboCommProdfront.getValue()));

            c.setNbProduits(x2);

            int id = Integer.parseInt(txtidfront.getText());
            c.setId(id);
            c.setIdUser(Smarties.user.getId());

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
        int id = Integer.parseInt(txtidfront.getText());
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
        listcommandefront.setItems(items);
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        CommandeService n = new CommandeService();
        List<Commande> R = n.Rechercher(rechcfront.getText());

        ObservableList<Commande> datalist = FXCollections.observableArrayList(R);

        listcommandefront.setItems(datalist);
    }

    @FXML
    private void triercommande(ActionEvent event) {
        CommandeService n = new CommandeService();
        // int price = Integer.parseInt(findAb.getText());
        List< Commande> R = n.triercommande();

        ObservableList< Commande> datalist = FXCollections.observableArrayList(R);

        listcommandefront.setItems(datalist);
    }

    @FXML
    private void actcommande(ActionEvent event) {
          ObservableList<Commande> items = FXCollections.observableArrayList();
        List<Commande> listcomm = cs.afficherCommande();
        for (Commande r : listcomm) {
            String ch = r.toString();
            items.add(r);
        }
        listcommandefront.setItems(items);
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
    private void getDataAchat(MouseEvent event) {
        ObservableList<Achat> items = FXCollections.observableArrayList();
        List<Achat> listachh = sa.afficherAchat();
        for (Achat r : listachh) {
            String ch = r.toString();
            items.add(r);
        }
        listachatfront.setItems(items);
    }

    @FXML
    private void actachat(ActionEvent event) {
    }

    @FXML
    private void trierachatid(ActionEvent event) {
        AchatService n = new AchatService();
        // int price = Integer.parseInt(findAb.getText());
        List< Achat> R = n.trierachatid();

        ObservableList< Achat> datalist = FXCollections.observableArrayList(R);

        listachatfront.setItems(datalist);
    }

    @FXML
    private void commachat(ActionEvent event) throws Exception {
        int id = Integer.parseInt(txtidcom.getText());
        int idprod = Integer.parseInt(txtidprod.getText());
        sa.ajouterAchat(id , idprod);
        System.out.println(" Achat Effecute Un Mail Sera Envoye Soon !");
      Notifications.create().title("Bonne Nouvelle").text("Votre Achat a été passé avec succes félicitation").darkStyle().position(Pos.BOTTOM_CENTER).showWarning();
                      
    

    }

}
