/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Abonnement;
import com.smarties.services.AbonnementService;
import com.smarties.entities.Location;
import com.smarties.services.LocationService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GuiAbonnementController implements Initializable {

    private AbonnementService as = new AbonnementService();
    private LocationService loc = new LocationService();

    @FXML
    private TextField textIDAbonnement;
    @FXML
    private TextField textIDUserA;
    @FXML
    private TextField TextType;
    @FXML
    private TextField textDateDebAbonnment;
    @FXML
    private TextField textDateFinAbonnment;
    @FXML
    private TextField textPrixAb;
    @FXML
    private Button btnAjouterAb;
    @FXML
    private Button btnSupprimerAb;
    @FXML
    private Button btnModifierAb;
    @FXML
    private ListView<?> listAb;
    @FXML
    private TextField textIdLocation;
    private TextField textDateLoc;
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
    private ListView<?> ListLoc;
    @FXML
    private TextField TextDuree;
    @FXML
    private DatePicker textDatePickLoc;
    @FXML
    private Label wrong;
    @FXML
    private Label wrong2;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList al = (ArrayList) as.afficherAbonnement();
        listAb.getItems().addAll(al);

        ArrayList l = (ArrayList) loc.afficherLocation();
        ListLoc.getItems().addAll(l);

    }

    @FXML
    private void AjouterAbonnement(ActionEvent event) {

        Abonnement ab = new Abonnement();
       /*  else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data.");
        }*/
       if(textIDAbonnement.getText().isEmpty()&&textIDUserA.getText().isEmpty()&&textPrixAb.getText().isEmpty())
       {
       wrong.setText("Veuillez remplir tous les champs ");
     
       }
       else{
        ab.setDateD(textDateDebAbonnment.getText());
        ab.setDateF(textDateFinAbonnment.getText());
        ab.setType(TextType.getText());

        int x1 = Integer.parseInt(textIDAbonnement.getText());
        int x2 = Integer.parseInt(textIDUserA.getText());
        int x3 = Integer.parseInt(textPrixAb.getText());

        ab.setId(x1);
        ab.setId_user_id(x2);
        ab.setPrix(x3);

        as.ajouterAbonnement(ab);
         wrong.setText(" Abonnement ajoute avec succces ");
         textDateDebAbonnment.setText(" ");
         textDateFinAbonnment.setText(" ");
         textIDAbonnement.setText(" ");
         textIDUserA.setText(" ");
         textPrixAb.setText(" ");
         textPrixAb.setText(" ");
       }

    }

    @FXML
    private void SupprimerAbonnement(ActionEvent event) {
        int id = Integer.parseInt(textIDAbonnement.getText());
        as.supprimerAbonnement(id);
        textIDAbonnement.setText(" ");
    }

    @FXML
    private void ModifierAbonnement(ActionEvent event) {
        Abonnement ab = new Abonnement();

        ab.setDateD(textDateDebAbonnment.getText());
        ab.setDateF(textDateFinAbonnment.getText());
        ab.setType(TextType.getText());

        int x1 = Integer.parseInt(textIDAbonnement.getText());
        int x2 = Integer.parseInt(textIDUserA.getText());
        int x3 = Integer.parseInt(textPrixAb.getText());

        ab.setId(x1);
        ab.setId_user_id(x2);
        ab.setPrix(x3);

        as.modifierAbonnement(ab);
        

    }

    @FXML
    private void AjouterLoaction(ActionEvent event) {

        Location loc1 = new Location();
        
       LocalDate date = textDatePickLoc.getValue(); 
       loc1.setDate(date);
       if(textIdLocation.getText().isEmpty()&&TextIdUserLoc.getText().isEmpty()&&TextIdAbonne.getText().isEmpty()&&TextDuree.getText().isEmpty())
       {
         wrong2.setText("Veuillez remplir tous les champs ");
       }
       else 
           
       {  
        
        loc1.setHeure(TextHeureLoc.getText());

        int x1 = Integer.parseInt(textIdLocation.getText());
        int x2 = Integer.parseInt(TextIdUserLoc.getText());
        int x3 = Integer.parseInt(TextIdAbonne.getText());
        Float x4 = Float.parseFloat(TextDuree.getText());
        loc1.setId(x1);
        loc1.setIdUser(x2);
        loc1.setIdAbonnement(x3);
        loc1.setDuree(x4);
        loc.ajouterLocation(loc1);
       wrong2.setText("location ajoute avec succes ");}
    }

    @FXML
    private void SupprimerLocation(ActionEvent event) {

        int id = Integer.parseInt(textIdLocation.getText());
        loc.supprimerLocation(id);
      
        
    }

    @FXML
    private void ModifierLocation(ActionEvent event) {

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
      

    }


}
