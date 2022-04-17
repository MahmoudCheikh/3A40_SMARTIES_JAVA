/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.input.TransferMode;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

    Alert alert = new Alert(Alert.AlertType.NONE);

    private int a;
    Produit produit = null;
    Stock stock = null;
    Emplacement emplacement = null;
    private boolean verification;
    private File Current_file;
    private String file_image;
    @FXML
    private TextField typeProd;
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
    private TextField idProdS;
    @FXML
    private TextField dispoS;
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
    private TextField idSE;
    @FXML
    private TextField capaciteE;
    @FXML
    private TextField lieuE;
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
    private TextField userF;
    @FXML
    private TextField produitF;
    @FXML
    private Button btnAjoutFav;
    @FXML
    private Button btnSupprimerFav;
    @FXML
    private TextField idfav;
    @FXML
    private ListView<Favoris> listefav;
    @FXML
    private TextField searchSto;
    @FXML
    private TextField searchEmp;
    @FXML
    private TextField searchFav;
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

    /**
     * Initializes the controller class.
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

    }

    /**
     * ****************************************************** POUR TT LES
     * ENTITES ****************************************************
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
     * ****************************************************** Produit
     * ****************************************************
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

        if (libelleProd.getText().equals("") || imageProd.getText().equals("") || descProd.getText().equals("") || prixProd.getText().equals("") || typeProd.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", libelleProd.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("le libelle doit etre de type String !");
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
        } else {
            Produit pro = new Produit();

            pro.setLibelle(libelleProd.getText());
            pro.setImage(imageProd.getText());
            pro.setDescription(descProd.getText());
            Float prix = Float.parseFloat(prixProd.getText());
            pro.setType(typeProd.getText());

            pro.setPrix(prix);

            pr.ajouterProduit(pro);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("PRODUIT ajoutée!");
            alert.show();
            refresh();
        }

    }

    @FXML
    private void ModifierProd(ActionEvent event) {
        if (libelleProd.getText().equals("") || imageProd.getText().equals("") || descProd.getText().equals("") || prixProd.getText().equals("") || typeProd.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else if (!(Pattern.matches("[a-z,A-Z]*", libelleProd.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("le libelle doit etre de type String !");
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
        } else {
            Produit pro = new Produit();
            Produit proID = listeProduit.getSelectionModel().getSelectedItem();

            pro.setLibelle(libelleProd.getText());
            pro.setImage(imageProd.getText());
            pro.setDescription(descProd.getText());
            Float prix = Float.parseFloat(prixProd.getText());
            pro.setType(typeProd.getText());
            pro.setPrix(prix);

            pro.setId(proID.getId());

            pr.modifierProduit(pro);

            refresh();
            libelleProd.setText("");
            imageProd.setText("");
            descProd.setText("");
            prixProd.setText("");
            typeProd.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();
        }
    }

    @FXML
    private void SupprimerProd(ActionEvent event) {

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("vous-etes sur de supprimer ce PRODUIT !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            Produit pro = new Produit();

            pro.setId(listeProduit.getSelectionModel().getSelectedItem().getId());
            pr.supprimerProduit(pro.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Produit supprimé!");
            alert.show();
            refresh();
        } else {
            refresh();

        }
    }

    @FXML
    private void searchProduitLibelle(ActionEvent event) {
        List<Produit> libelle = pr.RechercheLibelle(searchProLib.getText());
        listeProduit.getItems().clear();
        listeProduit.getItems().removeAll(produit);
        listeProduit.getItems().addAll(libelle);
    }

    @FXML
    private void searchProduitType(ActionEvent event) {
        List<Produit> type = pr.RechercheLibelle(searchProLib.getText());
        listeProduit.getItems().clear();
        listeProduit.getItems().removeAll(produit);
        listeProduit.getItems().addAll(type);
    }

    @FXML
    private void TrierProduitPrix(ActionEvent event) {
        List<Produit> trie = pr.TriPrix();
        listeProduit.getItems().clear();
        listeProduit.getItems().addAll(trie);
    }

    @FXML
    private void TrierProduitType(ActionEvent event) {
        List<Produit> trie11 = pr.TriType();
        listeProduit.getItems().clear();
        listeProduit.getItems().addAll(trie11);
    }

    /**
     * ************************************************************************STOCK**************************************************************************************
     */
    @FXML
    private void AjoutStock(ActionEvent event) {

        Stock sto = new Stock();
        if (libS.getText().equals("") || prixS.getText().equals("") || quantiteS.getText().equals("") || dispoS.getText().equals("") || idProdS.getText().equals("")) {
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

        } else {
            sto.setLibelle(libS.getText());
            int prix1 = Integer.parseInt(prixS.getText());
            int q = Integer.parseInt(quantiteS.getText());
            sto.setDisponibilite(dispoS.getText());
            int idPS = Integer.parseInt(idProdS.getText());

            sto.setPrix(prix1);
            sto.setQuantite(q);
            sto.setIdProduit(idPS);

            st.ajouterStock(sto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Stock ajoutée!");
            alert.show();
            refresh1();
        }
    }

    @FXML
    private void ModifierStock(ActionEvent event) {
        if (libS.getText().equals("") || prixS.getText().equals("") || quantiteS.getText().equals("") || dispoS.getText().equals("") || idProdS.getText().equals("")) {
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

        } else {
            Stock sto = new Stock();
            Stock stoID = listeS.getSelectionModel().getSelectedItem();
            sto.setLibelle(libS.getText());
            int prix1 = Integer.parseInt(prixS.getText());
            int q = Integer.parseInt(quantiteS.getText());
            sto.setDisponibilite(dispoS.getText());
            int idPS = Integer.parseInt(idProdS.getText());

            sto.setPrix(prix1);
            sto.setPrix(q);
            sto.setIdProduit(idPS);

            sto.setId(stoID.getId());

            st.modifierStock(sto);
            refresh1();
            libS.setText("");
            prixS.setText("");
            quantiteS.setText("");
            dispoS.setText("");
            idProdS.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();
        }
    }

    @FXML
    private void SupprimerStock(ActionEvent event) {

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("vous-etes sur de supprimer cet STOCK !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            Stock sto = new Stock();

            sto.setId(listeS.getSelectionModel().getSelectedItem().getId());
            st.supprimerStock(sto.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Stock supprimé!");
            alert.show();
            refresh1();
        } else {
            refresh1();

        }
    }

    @FXML
    private void searchStock(ActionEvent event) {
        List<Stock> lib = st.RechercheLibelle(searchSto.getText());
        listeS.getItems().clear();
        listeS.getItems().removeAll(stock);
        listeS.getItems().addAll(lib);
    }

    @FXML
    private void trierStockQuantite(ActionEvent event) {
        List<Stock> trieQ = st.TriQuantite();
        listeS.getItems().clear();
        listeS.getItems().addAll(trieQ);
    }

    @FXML
    private void TrierStockDisponibilite(ActionEvent event) {
        List<Stock> trieD = st.TriDisponibilite();
        listeS.getItems().clear();
        listeS.getItems().addAll(trieD);
    }

    /**
     * ************************************************************************EMPLACEMENT**************************************************************************************
     */
    @FXML
    private void AjouterEmplacement(ActionEvent event) {
        Emplacement emp = new Emplacement();

        if (lieuE.getText().equals("") || capaciteE.getText().equals("") || idSE.getText().equals("")) {
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
            emp.setLieu(lieuE.getText());
            int cap = Integer.parseInt(capaciteE.getText());
            int idstock = Integer.parseInt(idSE.getText());

            emp.setCapacite(cap);
            emp.setStock(idstock);

            em.ajouterEmplacement(emp);
            refresh2();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("EMPLACEMENT ajoutée!");
            alert.show();
        }
    }

    @FXML
    private void ModifierEmplacement(ActionEvent event) {
        Emplacement emp = new Emplacement();
        if (lieuE.getText().equals("") || capaciteE.getText().equals("") || idSE.getText().equals("")) {
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
            emp.setLieu(lieuE.getText());
            int cap = Integer.parseInt(capaciteE.getText());
            int idstock = Integer.parseInt(idSE.getText());

            emp.setCapacite(cap);
            emp.setStock(idstock);

            int id = Integer.parseInt(idEmp.getText());
            emp.setId(id);

            em.modifierEmplacement(emp);
            refresh2();

            lieuE.setText("");
            capaciteE.setText("");
            idSE.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();
        }
    }

    @FXML
    private void SupprimerEmplacement(ActionEvent event) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("vous-etes sur de supprimer cet STOCK !");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            Emplacement emp = new Emplacement();

            emp.setId(listeEmp.getSelectionModel().getSelectedItem().getId());
            em.supprimerEmplacement(emp.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Stock supprimé!");
            alert.show();
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
     * ************************************************************************FAVORIS**************************************************************************************
     */
    @FXML
    private void AjouterFavoris(ActionEvent event
    ) {

        Favoris fav = new Favoris();
        if (produitF.getText().equals("") || userF.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
        } else {
            int produitFav = Integer.parseInt(produitF.getText());
            int userFav = Integer.parseInt(userF.getText());

            fav.setIdProduit(produitFav);
            fav.setIdUser(userFav);

            fa.ajouterFavoris(fav);
        }
    }

    @FXML
    private void SupprimerFavoris(ActionEvent event
    ) {
        int id = Integer.parseInt(idfav.getText());
        fa.supprimerFavoris(id);
    }

    @FXML
    private void searchFavoris(ActionEvent event
    ) {
    }

}
