/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Achat;
import com.smarties.entities.Commande;
import com.smarties.entities.Sujet;
import com.smarties.services.AchatService;
import com.smarties.services.CommandeService;
import com.smarties.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.runtime.UserAccessorProperty;

/**
 * FXML Controller class
 *
 * @author Ahmed Elmoez
 */
public class MesCommandesController implements Initializable {

    private CommandeService cs = new CommandeService();
    private AchatService sa = new AchatService();
    ProduitService pr = new ProduitService();
    Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML
    private Button modifercomm;
    @FXML
    private Button supprcomm;
    private ListView<Commande> Mycommandeclient;
    @FXML
    private TextField idcommande;
    @FXML
    private ComboBox<String> BoxCommProd;
    @FXML
    private TextField nbrprd;
    @FXML
    private ListView<Achat> myachatclient;
    @FXML
    private VBox contenu;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button Act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //My Commande Client
       /* ArrayList al = (ArrayList) cs.afficherCommande();
        Mycommandeclient.getItems().addAll(al);
        BoxCommProd.setItems(FXCollections.observableArrayList(cs.comboCommProd()));

        //My Achat Client
        ArrayList a2 = (ArrayList) sa.afficherAchat();
        myachatclient.getItems().addAll(al);*/

        List<Commande> listCom = cs.afficherCommande();

        if (!listCom.isEmpty()) {
            for (Commande commande : listCom) {
                
                System.out.println(commande);
                contenu.getChildren().add(makeCommande(commande));
            }
        } else {

        }
        scroll.setContent(contenu);

    }

    public Parent makeCommande(Commande commande) {
        Parent innerContainer = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelCommande.fxml"));
            innerContainer = loader.load();
      if(commande.getIdUser()==Smarties.user.getId()){
            //  HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Label) innerContainer.lookup("#txtIdComm")).setText("Numero de commande: " + commande.getId());
             ((Label) innerContainer.lookup("#txtNbrProd")).setText("Nombre Produit : " + commande.getNbProduits());                                       
            ((Label) innerContainer.lookup("#txtProd")).setText("Produit : " + commande.getIdProduit());
            ((Label) innerContainer.lookup("#txtIdUser")).setText("Id Client : " + Smarties.user.getId());}
      
      else{((Label) innerContainer.lookup("#txtIdComm")).setText("");
             ((Label) innerContainer.lookup("#txtNbrProd")).setText("");                                             
            ((Label) innerContainer.lookup("#txtProd")).setText("");
            ((Label) innerContainer.lookup("#txtIdUser")).setText("");
            innerContainer.lookup("#img").setVisible(false);
      }

           //((Label) innerContainer.lookup("#txtPrixProd")).setText("Titre : " + commande.get);
           //((Button) innerContainer.lookup("#supprimer")).setOnAction((event) -> afficherSujet(sujet));
         //   ((Button) innerContainer.lookup("#modifier")).setOnAction((event) -> supprimerSujet(sujet));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return innerContainer;

    }

    @FXML
    private void updatecomm(ActionEvent event) throws SQLException {
        if ((BoxCommProd.getValue().equals("")) || (nbrprd.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[0-9]*", nbrprd.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nombre de produit doit etre Superieur a 1 Mr Le Client Merci !");
            alert.showAndWait();
        } else {
            Commande c = new Commande();

            int qtnprod = Integer.parseInt(nbrprd.getText());
            c.setIdProduit(pr.searchByLib(BoxCommProd.getValue()));

            c.setNbProduits(qtnprod);

            int id = Integer.parseInt(idcommande.getText());
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
    private void deletecomm(ActionEvent event) {
        int id = Integer.parseInt(idcommande.getText());
        cs.supprimerCommande(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Commande supprimé Mr Le Client !");
        alert.show();
        ObservableList<Commande> items = FXCollections.observableArrayList();
        List<Commande> listcomm = cs.afficherCommande();
        for (Commande r : listcomm) {
            String ch = r.toString();
            items.add(r);
        }
        Mycommandeclient.setItems(items);

    }

//Achat 

    @FXML
    private void act(ActionEvent event) {
         ObservableList<Commande> items = FXCollections.observableArrayList();
        List<Commande> listcomm = cs.afficherCommande();
        for (Commande r : listcomm) {
            String ch = r.toString();
            items.add(r);
        }
        Mycommandeclient.setItems(items);
    }
}
