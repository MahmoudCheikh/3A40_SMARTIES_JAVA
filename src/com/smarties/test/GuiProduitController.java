/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.itextpdf.text.DocumentException;
import com.smarties.entities.Emplacement;
import com.smarties.entities.Favoris;
import com.smarties.entities.Produit;
import com.smarties.entities.Stock;
import com.smarties.services.EmplacementService;
import com.smarties.services.FavorisService;
import com.smarties.services.ProduitService;
import com.smarties.services.StockService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Font;
import javafx.scene.chart.XYChart;
import com.lowagie.text.Element;
import com.smarties.services.UsersService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class GuiProduitController implements Initializable {

    private ProduitService pr = new ProduitService();
    private StockService st = new StockService();
    private EmplacementService em = new EmplacementService();
    private FavorisService fa = new FavorisService();
    private UsersService us = new UsersService();

    Alert alert = new Alert(Alert.AlertType.NONE);

    private int a;
    Produit produit = null;
    Stock stock = null;
    Emplacement emplacement = null;
    private boolean verification;
    private File Current_file;
    private String file_image;
    @FXML
    private TextField prixProd;
    @FXML
    private TextField descProd;
    @FXML
    private TextField imageProd;
    @FXML
    private TextField libelleProd;
    @FXML
    private Button btnAjoutProd;
    @FXML
    private Button btnModifierProd;
    @FXML
    private Button btnSupprimerProd;
    @FXML
    private ListView<Produit> listeProduit;
    @FXML
    private TextField libS;
    @FXML
    private ComboBox<String> dispoS;
    @FXML
    private TextField quantiteS;
    @FXML
    private TextField prixS;
    @FXML
    private Button btnAjoutS;
    @FXML
    private Button btnModifierS;
    @FXML
    private Button btnSupprimerS;
    @FXML
    private ListView<Stock> listeS;
    private TextField idEmpalcement;
    @FXML
    private TextField capaciteE;
    @FXML
    private ComboBox<String> lieuE;
    @FXML
    private Button btnAjoutEmp;
    @FXML
    private Button btnModifierEmp;
    @FXML
    private Button btnSupprimerEmp;
    private TextField idEmp;
    @FXML
    private ListView<Emplacement> listeEmp;
    @FXML
    private Button btnSupprimerFav;
    @FXML
    private ListView<Favoris> listefav;
    @FXML
    private TextField searchSto;
    @FXML
    private TextField searchEmp;
    private ImageView img;
    @FXML
    private Button btnTRI;
    @FXML
    private Button btnTRIType;
    @FXML
    private TextField searchProLib;
    @FXML
    private TextField searchProType;
    @FXML
    private Button upload;
    @FXML
    private Button triQ;
    @FXML
    private Button TriD;
    @FXML
    private Label cds3;
    @FXML
    private Button triE;
    @FXML
    private Button triC;
    @FXML
    private ComboBox<String> comboType;
    private String[] typeProduit = {"Velo", "Piece de Rechange", "Accesssoire"};
    private String[] disponibilite = {"Disponible", "Indisponible"};
    private String[] lieuEmplacement = {"Ariana", "Béja", "Ben+Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
    @FXML
    private TextField typeProd;
    @FXML
    private Button stat;
    @FXML
    private ComboBox<String> idProdSCombo;
    @FXML
    private ComboBox<String> idSECombo;
    @FXML
    private PieChart pieChart;
    @FXML
    private Button statProd;
    @FXML
    private PieChart pieChartProd;
    @FXML
    private ImageView veloIm;
    @FXML
    private ImageView StockIm;
    @FXML
    private ImageView stockImS;
    @FXML
    private ImageView veloImS;
    @FXML
    private ImageView empIm;
    @FXML
    private ImageView favIm;
    @FXML
    private Button pdfStock;

    /**
     * Initializes the controller class.
     */
    /**
     * ******************************************************
     * AFFICHAGE / REFRESH / COMBO BOX / IMAGES / PIE CHART
     * *******************************************************
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList al = (ArrayList) pr.afficherProduit();
        listeProduit.getItems().addAll(al);

        ArrayList a2 = (ArrayList) st.afficherStock();
        listeS.getItems().addAll(a2);

        ArrayList a3 = (ArrayList) em.afficherEmplacement();
        listeEmp.getItems().addAll(a3);

        ArrayList a4 = (ArrayList) fa.afficherFavoris();
        listefav.getItems().addAll(a4);

        refresh();
        refresh1();
        refresh2();
        refresh3();

        comboType.getItems().addAll(typeProduit);
        dispoS.getItems().addAll(disponibilite);
        lieuE.getItems().addAll(lieuEmplacement);
        idProdSCombo.setItems(FXCollections.observableArrayList(st.getCombo()));
        idSECombo.setItems(FXCollections.observableArrayList(em.getCombo()));

        new animatefx.animation.RollIn(veloIm).play();
        new animatefx.animation.FadeIn(listeProduit).play();

    }

    /**
     * ******************************************************
     * REFRESH *******************************************************
     */
    private void refresh() {
        List<Produit> prod = pr.afficherProduit();
        listeProduit.getItems().clear();
        listeProduit.getItems().addAll(prod);
    }

    private void refresh1() {
        List<Stock> sto = st.afficherStock();
        listeS.getItems().clear();
        listeS.getItems().addAll(sto);
    }

    private void refresh2() {
        List<Emplacement> emp = em.afficherEmplacement();
        listeEmp.getItems().clear();
        listeEmp.getItems().addAll(emp);
    }

    private void refresh3() {
        List<Favoris> fav = fa.afficherFavoris();
        listefav.getItems().clear();
        listefav.getItems().addAll(fav);
    }

    /**
     * ***************************************************
     * GESTION DE PRODUIT ****************************************************
     */
    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String Filename = f.getAbsolutePath();
        imageProd.setText(Filename);
    }

    @FXML
    private void AjoutProd(ActionEvent event) throws IOException {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment ajouter ce PRODUIT !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (libelleProd.getText().equals("") || imageProd.getText().equals("") || descProd.getText().equals("") || prixProd.getText().equals("") || comboType.getValue().contentEquals("Type")) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (libelleProd.getText().length() < 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le libelle doit au moin contenir 4 caractéres");
                alert.showAndWait();

            } else if (descProd.getText().length() < 10) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("la description doit au moin contenir 10 caractéres");
                alert.showAndWait();

            } else if (!(Pattern.matches("[0.-9.]*", prixProd.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le prix doit etre de type Float/Int !");
                alert.showAndWait();
            } else if (pr.getIgnoreRepetetion(libelleProd.getText()) == false) {
                Produit pro = new Produit();

                pro.setLibelle(libelleProd.getText());
                pro.setImage(imageProd.getText());
                pro.setDescription(descProd.getText());
                Float prix = Float.parseFloat(prixProd.getText());
                //pro.setType(typeProd.getText());
                pro.setType(comboType.getValue());

                pro.setPrix(prix);

                pr.ajouterProduit(pro);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Produit Ajouté", ButtonType.OK);
                alert.show();

                alert.setTitle("Success");
                alert.setContentText("PRODUIT ajoutée!");
                alert.show();
                refresh();
                new animatefx.animation.Bounce(veloIm).play();
                new animatefx.animation.FadeIn(listeProduit).play();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le produit deja existant !");
                alert.showAndWait();
            }

        } else {
            refresh();
        }
    }

    @FXML
    private void ModifierProd(ActionEvent event) {

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment modifier ce PRODUIT !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (libelleProd.getText().equals("") || imageProd.getText().equals("") || descProd.getText().equals("") || prixProd.getText().equals("") || comboType.getValue().equals("Type")) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (libelleProd.getText().length() < 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le libelle doit au moin contenir 4 caractéres");
                alert.showAndWait();

            } else if (descProd.getText().length() < 10) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("la description doit au moin contenir 10 caractéres");
                alert.showAndWait();

            } else if (!(Pattern.matches("[0.-9.]*", prixProd.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le prix doit etre de type Float/Int !");
                alert.showAndWait();
            } else if (pr.getIgnoreRepetetion(libelleProd.getText()) == false) {
                Produit pro = new Produit();
                Produit proID = listeProduit.getSelectionModel().getSelectedItem();

                pro.setLibelle(libelleProd.getText());
                pro.setImage(imageProd.getText());
                pro.setDescription(descProd.getText());
                Float prix = Float.parseFloat(prixProd.getText());
                pro.setType(comboType.getValue());
                pro.setPrix(prix);

                pro.setId(proID.getId());

                pr.modifierProduit(pro);

                refresh();
                libelleProd.setText("");
                imageProd.setText("");
                descProd.setText("");
                prixProd.setText("");
                comboType.setValue("Type");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("modification effectuée!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le produit deja existant !");
                alert.showAndWait();
            }
        } else {
            refresh();
        }
    }

    @FXML
    private void SupprimerProd(ActionEvent event
    ) {

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer ce PRODUIT !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            Produit pro = new Produit();

            pro.setId(listeProduit.getSelectionModel().getSelectedItem().getId());
            pr.supprimerProduit(pro.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Produit supprimé!");
            alert.show();
new animatefx.animation.Pulse(listeProduit).play();
            refresh();
        } else {
            refresh();

        }
    }

    @FXML
    private void searchProduitLibelle(ActionEvent event
    ) {
        List<Produit> libelle = pr.RechercheLibelle(searchProLib.getText());
        listeProduit.getItems().clear();
        listeProduit.getItems().removeAll(produit);
        listeProduit.getItems().addAll(libelle);
    }

    @FXML
    private void searchProduitType(ActionEvent event
    ) {
        List<Produit> type = pr.RechercheType(searchProType.getText());
        listeProduit.getItems().clear();
        listeProduit.getItems().removeAll(produit);
        listeProduit.getItems().addAll(type);
    }

    @FXML
    private void TrierProduitPrix(ActionEvent event
    ) {
        List<Produit> trie = pr.TriPrix();
        listeProduit.getItems().clear();
        listeProduit.getItems().addAll(trie);
    }

    @FXML
    private void TrierProduitType(ActionEvent event
    ) {
        List<Produit> trie11 = pr.TriType();
        listeProduit.getItems().clear();
        listeProduit.getItems().addAll(trie11);
    }

    /**
     * ******************************************
     * GESTION DE STOCK *******************************************
     */
    @FXML
    private void AjoutStock(ActionEvent event
    ) throws SQLException, Exception {

        Stock sto = new Stock();

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment ajouter ce stock");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (libS.getText().equals("") || prixS.getText().equals("") || quantiteS.getText().equals("") || dispoS.getValue().equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0.-9.]*", prixS.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le prix doit etre de type Float/Int !");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0-9]*", quantiteS.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("la quantité doit etre de type Int !");
                alert.showAndWait();
            } else if (libS.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le libelle doit au moin contenir 5 caractéres");
                alert.showAndWait();

            } else if (st.getIgnoreRepetetion(libS.getText()) == false) {
                sto.setLibelle(libS.getText());
                int prix1 = Integer.parseInt(prixS.getText());
                int q = Integer.parseInt(quantiteS.getText());
                sto.setDisponibilite(dispoS.getValue());
                ///////////////////////////////////////combo id

                sto.setIdProduit(pr.searchByLib(idProdSCombo.getValue()));

                sto.setPrix(prix1);
                sto.setQuantite(q);
                // sto.setIdProduit(idPS);

                st.ajouterStock(sto);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Ajout effectuée avec succé!" );
                alert.show();
                if(sto.getQuantite()<=50){
               st.sendMail(Smarties.user.getEmail());
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("le stock est épuisé ! un mail sera vous envoyé !");
                alert1.showAndWait();
                }

                refresh1();
                new animatefx.animation.FadeIn(listeS).play();
                new animatefx.animation.FadeIn(StockIm).play();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le stock deja existant !");
                alert.showAndWait();
            }
        } else {
            refresh1();
        }
    }

    @FXML
    private void ModifierStock(ActionEvent event) throws SQLException {

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment modifier ce stock");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (libS.getText().equals("") || prixS.getText().equals("") || quantiteS.getText().equals("") || dispoS.getValue().equals("") || idProdSCombo.getValue().equals("Produit")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0.-9.]*", prixS.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le prix doit etre de type Float/Int !");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0-9]*", quantiteS.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("la quantité doit etre de type Int !");
                alert.showAndWait();
            } else if (libS.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le libelle doit au moin contenir 5 caractéres");
                alert.showAndWait();

            } else if (st.getIgnoreRepetetion(libS.getText()) == false) {
                Stock sto = new Stock();
                Stock stoID = listeS.getSelectionModel().getSelectedItem();
                sto.setLibelle(libS.getText());
                int prix1 = Integer.parseInt(prixS.getText());
                int q = Integer.parseInt(quantiteS.getText());
                sto.setDisponibilite(dispoS.getValue());

                sto.setIdProduit(pr.searchByLib(idProdSCombo.getValue()));
                sto.setPrix(prix1);
                sto.setQuantite(q);

                sto.setId(stoID.getId());

                st.modifierStock(sto);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Modification effectuée!");
                alert.show();

                libS.setText("");
                prixS.setText("");
                quantiteS.setText("");
                dispoS.setValue("Disponibilité");
                idProdSCombo.setValue("Produit");
                refresh1();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le stock deja existant !");
                alert.showAndWait();
            }
        } else {
            refresh1();
        }
    }

    @FXML
    private void SupprimerStock(ActionEvent event) {

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer ce STOCK !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            Stock sto = new Stock();

            sto.setId(listeS.getSelectionModel().getSelectedItem().getId());
            st.supprimerStock(sto.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Stock supprimé!");
            alert.show();
new animatefx.animation.Pulse(StockIm).play();
            refresh1();
        } else {
            refresh1();

        }
    }

    @FXML
    private void searchStock(ActionEvent event
    ) {
        List<Stock> lib = st.RechercheLibelle(searchSto.getText());
        listeS.getItems().clear();
        listeS.getItems().removeAll(stock);
        listeS.getItems().addAll(lib);
    }

    @FXML
    private void trierStockQuantite(ActionEvent event
    ) {
        List<Stock> trieQ = st.TriQuantite();
        listeS.getItems().clear();
        listeS.getItems().addAll(trieQ);
    }

    @FXML
    private void TrierStockDisponibilite(ActionEvent event
    ) {
        List<Stock> trieD = st.TriDisponibilite();
        listeS.getItems().clear();
        listeS.getItems().addAll(trieD);
    }

    /**
     * **********************************************
     * GESTION D'EMPLACEMENT ***********************************************
     */
    @FXML
    private void AjouterEmplacement(ActionEvent event
    ) throws SQLException {
        Emplacement emp = new Emplacement();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment ajouter cet emplacement");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (lieuE.getValue().equals("") || capaciteE.getText().equals("") || idSECombo.getValue().equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0-9]*", capaciteE.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("la capacité doit etre de type Int !");
                alert.showAndWait();
            } else {
                emp.setLieu(lieuE.getValue());
                int cap = Integer.parseInt(capaciteE.getText());

                ///////////////////////////////////////combo id
                emp.setStock(st.searchByLibS(idSECombo.getValue()));

                emp.setCapacite(cap);

                em.ajouterEmplacement(emp);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("ajout effectué avec succé!");
                alert.show();
                new animatefx.animation.Swing(empIm).play();
                refresh2();
                

            }
        } else {
            refresh2();
        }
    }

    @FXML
    private void ModifierEmplacement(ActionEvent event
    ) throws SQLException {
        Emplacement emp = new Emplacement();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment modifier cet Emplacement");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (lieuE.getValue().equals("") || capaciteE.getText().equals("") || idSECombo.getValue().equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else if (!(Pattern.matches("[0-9]*", capaciteE.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("la capacité doit etre de type Int !");
                alert.showAndWait();
            } else {
                emp.setLieu(lieuE.getValue());
                int cap = Integer.parseInt(capaciteE.getText());
                ///////////////////////////////////////combo id

                emp.setStock(st.searchByLibS(idSECombo.getValue()));

                emp.setCapacite(cap);

                Emplacement empID = listeEmp.getSelectionModel().getSelectedItem();
                emp.setId(empID.getId());

                em.modifierEmplacement(emp);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("modification effectuée avec succée!");
                alert.show();

                refresh2();

                lieuE.setValue("Lieu");
                capaciteE.setText("");
                idSECombo.setValue("Stock");

            }
        } else {
            refresh2();
        }
    }

    @FXML
    private void SupprimerEmplacement(ActionEvent event
    ) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer cet emplacement");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            Emplacement emp = new Emplacement();

            emp.setId(listeEmp.getSelectionModel().getSelectedItem().getId());
            em.supprimerEmplacement(emp.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("suppression effectuée avec succée!");
            alert.show();
new animatefx.animation.Pulse(empIm).play();
            refresh2();
        } else {
            refresh2();

        }
    }

    @FXML
    private void searchEmplacement(ActionEvent event
    ) {
        List<Emplacement> lieu = em.RechercheLieu(searchEmp.getText());
        listeEmp.getItems().clear();
        listeEmp.getItems().removeAll(emplacement);
        listeEmp.getItems().addAll(lieu);
    }

    @FXML
    private void TrierEmpLieu(ActionEvent event
    ) {
        List<Emplacement> trieL = em.TriLieu();
        listeEmp.getItems().clear();
        listeEmp.getItems().addAll(trieL);
    }

    @FXML
    private void TrierEmpCapacite(ActionEvent event
    ) {
        List<Emplacement> trieC = em.TriCapacite();
        listeEmp.getItems().clear();
        listeEmp.getItems().addAll(trieC);
    }

    /**
     * ***********************************
     * GESTION DES FAVORIS ************************************
     */
   /* @FXML
    private void AjouterFavoris(ActionEvent event) throws SQLException {

        Favoris fav = new Favoris();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment ajouter ce Produit aux Favoris");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (produitF.getValue().equals("") || userF.getValue().equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("champs manquants !");
                alert.showAndWait();
            } else {
                fav.setIdProduit(pr.searchByLib(produitF.getValue()));
                fav.setIdUser(us.searchByNom(userF.getValue()));

                fa.ajouterFavoris(fav);
                refresh3();
                new animatefx.animation.RubberBand(favIm).play();
            }
        } else {
            
            refresh3();
        }
    }*/

    @FXML
    private void SupprimerFavoris(ActionEvent even) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("vous-etes sur de supprimer cet Element Favoris !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            Favoris fav = new Favoris();

            fav.setId(listefav.getSelectionModel().getSelectedItem().getId());
            fa.supprimerFavoris(fav.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Favoris supprimé!");
            alert.show();
            refresh3();
            new animatefx.animation.Jello(favIm).play();
        } else {
            refresh3();

        }
        
    }

    /**
     * *********************************************
     * SET DATA FROM LISTVIEW **********************************************
     */
    @FXML
    private void getDataProduit(MouseEvent event) {
        Produit pro = new Produit();
        listeProduit.setOnMouseClicked((event1) -> {
            String selectedDes = listeProduit.getSelectionModel().getSelectedItem().getDescription();
            String selectedlib = listeProduit.getSelectionModel().getSelectedItem().getLibelle();
            String selectedIm = listeProduit.getSelectionModel().getSelectedItem().getImage();
            float selectedPrix = listeProduit.getSelectionModel().getSelectedItem().getPrix();
            String selectedType = listeProduit.getSelectionModel().getSelectedItem().getType();

            libelleProd.setText(selectedlib);
            imageProd.setText(selectedIm);
            descProd.setText(selectedDes);
            prixProd.setText(String.valueOf(selectedPrix));
            comboType.setValue(String.valueOf(selectedType));
            new animatefx.animation.Pulse(veloIm).play();
        });
    }

    @FXML
    private void getDataStock(MouseEvent event) {

        Stock pro = new Stock();
        listeS.setOnMouseClicked((event2) -> {
            String selectedLibS = listeS.getSelectionModel().getSelectedItem().getLibelle();
            String selectedDispo = listeS.getSelectionModel().getSelectedItem().getDisponibilite();
            int selectedQ = listeS.getSelectionModel().getSelectedItem().getQuantite();
            int selectedPrix = listeS.getSelectionModel().getSelectedItem().getPrix();
            int selectedIdP = listeS.getSelectionModel().getSelectedItem().getIdProduit();

            libS.setText(selectedLibS);
            dispoS.setValue(selectedDispo);
            quantiteS.setText(String.valueOf(selectedQ));
            prixS.setText(String.valueOf(selectedPrix));
            idProdSCombo.setValue(String.valueOf(selectedIdP));
            new animatefx.animation.Pulse(StockIm).play();
        });
    }

    @FXML
    private void getDataEmp(MouseEvent event) {
        Emplacement pro = new Emplacement();
        listeEmp.setOnMouseClicked((event3) -> {
            String selectedLieu = listeEmp.getSelectionModel().getSelectedItem().getLieu();
            int selectedStockE = listeEmp.getSelectionModel().getSelectedItem().getStock();
            int selectedCap = listeEmp.getSelectionModel().getSelectedItem().getCapacite();

            lieuE.setValue(selectedLieu);
            capaciteE.setText(String.valueOf(selectedCap));
            idSECombo.setValue(String.valueOf(selectedStockE));
            new animatefx.animation.Pulse(empIm).play();
        });
    }

//    private void genererPDF(ActionEvent event) {
//                 OutputStream file = null;
//        try {
//            file = new FileOutputStream(new File("Stock_çaRoule.pdf"));
// 
//            // Create a new Document object
//          Document document=new Document();
// 
//            // You need PdfWriter to generate PDF document
//            PdfWriter.getInstance(document, file);
// 
//            // Opening document for writing PDF
//            document.open();
//            Stock sto=new Stock();
//   //int mm= Integer.parseInt(idProdS.getText());
//            // Writing content
//            
////List<News> lr= tt.afficherId(id1);
//           List<Stock> stock = st.afficherStock();
//            
//             // System.out.println("hhhh");
//             //ajouter titre
//             Paragraph p=new Paragraph(); 
//
//             p.setAlignment(Element.ALIGN_CENTER);
//              Font f=new Font();
//             f.setStyle(Font.BOLD);
//             f.setSize(20);
//             f.setColor(BaseColor.RED);
//                        p.setFont(f);
//               p.add(sto.getLibelle());
//            document.add(p);
//            
//            //ajouter desc
//            document.add(new Paragraph("*******************************************************************************************************"));
//            document.add(new Paragraph(sto.getQuantite()));
//            document.add(new Paragraph("*******************************************************************************************************"));
//             
//              Paragraph p2=new Paragraph(); 
//             f.setStyle(Font.BOLDITALIC);
//             f.setSize(13);
//             f.setColor(BaseColor.DARK_GRAY);
//                        p2.setFont(f);
//           p2.add("ajouter par: "+sto.getLibelle()+"  le "+sto.getDisponibilite());
//            document.add(p2);
//          //  Image img=Image.getInstance("");
//// document.add(Image.getInstance("C:\\Users\\Admin\\Desktop\\valorantESport(front)\\src\\images.logo.png"));
//                   // Add meta data information to PDF file
//            document.addCreationDate();
//            document.addAuthor("Javarevisited");
//            document.addTitle("How to create PDF document in Java");
//            document.addCreator("Thanks to iText, writing into PDF is easy");
// 
//  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Success");
//            alert.setContentText("voulez vous vraiment télecharger un pdf?!");
//            alert.show();
//            // close the document
//            document.close();
// 
//            System.out.println("Your PDF File is succesfully created");
// 
//        } catch (Exception e) {
//            e.printStackTrace();
// 
//        } finally {
// 
//            // closing FileOutputStream
//            try {
//                if (file != null) {
//                    file.close();
//                }
//            } catch (IOException io) {/*Failed to close*/
// 
//            }
// 
//        }   
//    }
    @FXML
    private void generateStat(ActionEvent event) throws IOException {
new animatefx.animation.JackInTheBox(pieChart).play();
        Stock sto = new Stock();
        pieChart.setTitle("Quantite");
        pieChart.getData().setAll(new PieChart.Data("Quantite <20", st.Recherche3()), new PieChart.Data("Quantite [20-50]", st.Recherche1()),
                new PieChart.Data("Quantite [50-70]", st.Recherche2()), new PieChart.Data("Quantite [70-100]", st.Recherche4()));
        
                

        new animatefx.animation.FadeOutDown(stockImS).play();
        new animatefx.animation.Jello(stockImS).play();
        
    }

    @FXML
    private void generateStatProd(ActionEvent event) {
        new animatefx.animation.RubberBand(pieChartProd).play();
        pieChartProd.setTitle("Type");
        pieChartProd.getData().setAll(new PieChart.Data("Velo", pr.SearchVelo()), new PieChart.Data("Piece de Rechange", pr.SearchPDR()),
                new PieChart.Data("Accesssoire", pr.SearchAcc()));
        new animatefx.animation.FadeOutUp(veloImS).play();
    }

    @FXML
    private void GenererPdfStock(ActionEvent event) throws DocumentException {
                st.GeneratePDFStock();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Votre Stock est Prete En Pdf !");
        alert.showAndWait();
    }

}
