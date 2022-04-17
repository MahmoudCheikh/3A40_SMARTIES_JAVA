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
    private TextField idS;
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
    @FXML
    private TextField idEmp;
    @FXML
    private ListView<?> listeEmp;
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
    private ListView<?> listefav;
    private Label cds1;
    private Label cds2;
    @FXML
    private Label cds3;
    private TextField searchPro;
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

    /*private boolean verifChampsajouter() {
        int verif = 0;
        String styleP = " -fx-border-color: red;";
        String styledeL = "-fx-border-color: red;";
        String styledeD = "-fx-border-color: red;";

        libelleProd.setStyle(styledeL);
        prixProd.setStyle(styleP);
        descProd.setStyle(styledeD);

        if (libelleProd.getText().equals("")) {
            libelleProd.setStyle(styledeL);
            verif = 1;
        }
        if (descProd.getText().equals("")) {
            descProd.setStyle(styledeD);
            verif = 1;
        }
        if (prixProd.getText().equals("")) {
            prixProd.setStyle(styleP);
            verif = 1;
        }

        if (verif == 0) {
            return true;
        }

        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;

    }*/
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
        alert.setContentText("vous-etes sur de supprimer ce PRODUCT !");

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
        if (libS.getText().isEmpty() || prixS.getText().isEmpty() || quantiteS.getText().isEmpty() || dispoS.getText().isEmpty() || idProdS.getText().isEmpty()) {
            cds1.setText("Veuillez remplir tous les champs ");
        } else {
            sto.setLibelle(libS.getText());
            int prix1 = Integer.parseInt(prixS.getText());
            int q = Integer.parseInt(quantiteS.getText());
            sto.setDisponibilite(dispoS.getText());
            int idPS = Integer.parseInt(idProdS.getText());

            sto.setPrix(prix1);
            sto.setPrix(q);
            sto.setIdProduit(idPS);

            st.ajouterStock(sto);
        }
    }

    @FXML
    private void ModifierStock(ActionEvent event) {
        Stock sto = new Stock();

        sto.setLibelle(libS.getText());
        int prix1 = Integer.parseInt(prixS.getText());
        int q = Integer.parseInt(quantiteS.getText());
        sto.setDisponibilite(dispoS.getText());
        int idPS = Integer.parseInt(idProdS.getText());

        sto.setPrix(prix1);
        sto.setPrix(q);
        sto.setIdProduit(idPS);

        int id = Integer.parseInt(idS.getText());
        sto.setId(id);

        st.modifierStock(sto);

    }

    @FXML
    private void SupprimerStock(ActionEvent event) {
        int id = Integer.parseInt(idS.getText());
        st.supprimerStock(id);
    }

    /**
     * ************************************************************************EMPLACEMENT**************************************************************************************
     */
    @FXML
    private void AjouterEmplacement(ActionEvent event) {
        Emplacement emp = new Emplacement();

        if (lieuE.getText().isEmpty() && capaciteE.getText().isEmpty() && idSE.getText().isEmpty()) {
            cds2.setText("Veuillez remplir tous les champs ");
        } else {
            emp.setLieu(lieuE.getText());
            int cap = Integer.parseInt(capaciteE.getText());
            int idstock = Integer.parseInt(idSE.getText());

            emp.setCapacite(cap);
            emp.setStock(idstock);

            em.ajouterEmplacement(emp);
        }
    }

    @FXML
    private void ModifierEmplacement(ActionEvent event) {
        Emplacement emp = new Emplacement();

        emp.setLieu(lieuE.getText());
        int cap = Integer.parseInt(capaciteE.getText());
        int idstock = Integer.parseInt(idSE.getText());

        emp.setCapacite(cap);
        emp.setStock(idstock);

        int id = Integer.parseInt(idEmp.getText());
        emp.setId(id);

        em.modifierEmplacement(emp);
    }

    @FXML
    private void SupprimerEmplacement(ActionEvent event) {
        int id = Integer.parseInt(idEmp.getText());
        em.supprimerEmplacement(id);
    }

    /**
     * ************************************************************************FAVORIS**************************************************************************************
     */
    @FXML
    private void AjouterFavoris(ActionEvent event) {

        Favoris fav = new Favoris();
        if (produitF.getText().isEmpty() && userF.getText().isEmpty()) {
            cds3.setText("Veuillez remplir tous les champs ");
        } else {
            int produitFav = Integer.parseInt(produitF.getText());
            int userFav = Integer.parseInt(userF.getText());

            fav.setIdProduit(produitFav);
            fav.setIdUser(userFav);

            fa.ajouterFavoris(fav);
        }
    }

    @FXML
    private void SupprimerFavoris(ActionEvent event) {
        int id = Integer.parseInt(idfav.getText());
        fa.supprimerFavoris(id);
    }

    @FXML
    private void searchStock(ActionEvent event) {
    }

    @FXML
    private void searchEmplacement(ActionEvent event) {
    }

    @FXML
    private void searchFavoris(ActionEvent event) {
    }

}
