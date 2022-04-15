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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private TextField idProd;
    @FXML
    private ListView<?> listeProduit;
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
    private ListView<?> listeS;
    @FXML
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
    }

    @FXML
    private void AjoutProd(ActionEvent event) {
        Produit pro = new Produit();

        pro.setLibelle(libelleProd.getText());
        pro.setImage(imageProd.getText());
        pro.setDescription(descProd.getText());
        Float prix = Float.parseFloat(prixProd.getText());
        pro.setType(typeProd.getText());

        pro.setPrix(prix);

        pr.ajouterProduit(pro);
    }

    @FXML
    private void ModifierProd(ActionEvent event) {
        Produit pro = new Produit();

        pro.setLibelle(libelleProd.getText());
        pro.setImage(imageProd.getText());
        pro.setDescription(descProd.getText());
        Float prix = Float.parseFloat(prixProd.getText());
        pro.setType(typeProd.getText());

        pro.setPrix(prix);

        int id = Integer.parseInt(idProd.getText());
        pro.setId(id);

        pr.modifierProduit(pro);
    }

    @FXML
    private void SupprimerProd(ActionEvent event) {
        int id = Integer.parseInt(idProd.getText());
        pr.supprimerProduit(id);
    }

    /**
     * ************************************************************************STOCK**************************************************************************************
     */
    @FXML
    private void AjoutStock(ActionEvent event) {

        Stock sto = new Stock();

        sto.setLibelle(libS.getText());
        int prix1 = Integer.parseInt(prixS.getText());
        int q = Integer.parseInt(quantiteS.getText());
        sto.setDisponibilite(dispoS.getText());
        int idPS = Integer.parseInt(idProdS.getText());
        int empl = Integer.parseInt(idEmpalcement.getText());

        sto.setPrix(prix1);
        sto.setPrix(q);
        sto.setIdProduit(idPS);
        sto.setEmplacement(empl);

        st.ajouterStock(sto);
    }

    @FXML
    private void ModifierStock(ActionEvent event) {
        Stock sto = new Stock();

        sto.setLibelle(libS.getText());
        int prix1 = Integer.parseInt(prixS.getText());
        int q = Integer.parseInt(quantiteS.getText());
        sto.setDisponibilite(dispoS.getText());
        int idPS = Integer.parseInt(idProdS.getText());
        int empl = Integer.parseInt(idEmpalcement.getText());

        sto.setPrix(prix1);
        sto.setPrix(q);
        sto.setIdProduit(idPS);
        sto.setEmplacement(empl);

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

        emp.setLieu(lieuE.getText());
        int cap = Integer.parseInt(capaciteE.getText());
        int idstock = Integer.parseInt(idSE.getText());

        emp.setCapacite(cap);
        emp.setStock(idstock);

        em.ajouterEmplacement(emp);
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

        int produitFav = Integer.parseInt(produitF.getText());
        int userFav = Integer.parseInt(userF.getText());

        fav.setIdProduit(produitFav);
        fav.setIdUser(userFav);

        fa.ajouterFavoris(fav);
    }

    @FXML
    private void SupprimerFavoris(ActionEvent event) {
        int id = Integer.parseInt(idfav.getText());
        fa.supprimerFavoris(id);
    }

}
