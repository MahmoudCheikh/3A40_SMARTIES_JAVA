/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Evenement;
import com.smarties.services.EvenementService;
import com.smarties.entities.Activite;
import com.smarties.services.ActiviteService;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiEvenementController implements Initializable {

    private EvenementService es = new EvenementService();
    private ActiviteService act = new ActiviteService();
   
    @FXML
    private TextField txtnom;
    @FXML
    private DatePicker txtdated;
    @FXML
    private DatePicker txtdatef;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtlieu;
    @FXML
    private TextField txtnbpart;
    @FXML
    private TextField txtnbplaces;
    @FXML
    private Button btnajouter;
    @FXML
    private ListView<Evenement> listevent;
    @FXML
    private TextField txtid;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtimage;
    @FXML
    private TextField txtidevent;
    @FXML
    private Button btnajouterA;
    @FXML
    private ListView<Activite> listactivite;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtID;
    @FXML
    private Button btnmodif;
    @FXML
    private AnchorPane anchorevent;
    @FXML
    private TextField rech;
    @FXML
    private Button recherche;
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ArrayList a1 = (ArrayList) es.afficherEvenement();
        listevent.getItems().addAll(a1);
        ArrayList a2 = (ArrayList) act.afficherActivite();
        listactivite.getItems().addAll(a2);
    
    }
  
    @FXML
    private void ajouter(ActionEvent event) {
          LocalDate now = LocalDate.now();
         if((txtnom.getText().equals(""))||(txttype.getText().equals(""))||(txtlieu.getText().equals(""))||(txtdated.getValue().equals(""))||(txtdatef.getValue().equals(""))||(txtnbpart.getText().equals(""))||(txtnbplaces.getText().equals(""))){
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
         }
         else if(!( Pattern.matches("[a-z,A-Z]*",txtnom.getText()))){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nom de l'Evenement doit etre de type String !");
            alert.showAndWait();
          
       }
         else if(!( Pattern.matches("[a-z,A-Z]*",txttype.getText()))){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("type de l'evenement doit etre de type String !");
            alert.showAndWait();
          
       }
         else if(!( Pattern.matches("[a-z,A-Z]*",txtlieu.getText()))){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("lieu de l'evenement  doit etre de type String !");
            alert.showAndWait();
          
       }
         else if(!( Pattern.matches("[0-9]*",txtnbpart.getText()))){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nombre participants doit etre de type Int !");
            alert.showAndWait();
       }
         else if(!( Pattern.matches("[0-9]*",txtnbplaces.getText()))){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nombre de places doit etre de type Int !");
            alert.showAndWait();
       }
         else if((txtdated.getValue().getYear()>txtdatef.getValue().getYear())||(txtdated.getValue().getMonthValue()>txtdatef.getValue().getMonthValue())||(txtdated.getValue().getDayOfMonth()>txtdatef.getValue().getDayOfMonth())) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre inferieur a la date fin !");
            alert.showAndWait();
      }
          else if(txtdated.getValue().isBefore(now)){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre supérieur a celle d'aujourd'hui ! ");
            alert.showAndWait();
       }
         
         else{
        Evenement ev = new Evenement();
        LocalDate date = txtdated.getValue(); 
         LocalDate dated = txtdatef.getValue(); 
        ev.setNom(txtnom.getText());
        ev.setDate_d(date);
        ev.setDate_f(dated);
        ev.setType(txttype.getText());
        ev.setLieu(txtlieu.getText());
        int x1 =  Integer.parseInt(txtnbpart.getText());
        int x2 =  Integer.parseInt(txtnbplaces.getText());

        ev.setNb_participants(x2);
        ev.setNb_places(x1);
        
        es.ajouterEvenement(ev);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Evenement ajouté!");
            alert.show();        
        ObservableList<Evenement> items =FXCollections.observableArrayList();
        List<Evenement> listevvent = es.afficherEvenement();
        for(Evenement r : listevvent) {
            String ch = r.toString();
            items.add(r);
        }
       
    listevent.setItems(items);
       }

    }

    @FXML
    private void delete(ActionEvent event) {
       int id = Integer.parseInt(txtid.getText());
        es.supprimerEvenement(id);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Evenement supprimé!");
            alert.show();
           ObservableList<Evenement> items =FXCollections.observableArrayList();
        List<Evenement> listevvent = es.afficherEvenement();
        for(Evenement r : listevvent) {
            String ch = r.toString();
            items.add(r);
        }
       
    listevent.setItems(items);
    }

    @FXML
    private void update(ActionEvent event) {
        
        Evenement ev = new Evenement();
        LocalDate date = txtdated.getValue(); 
         LocalDate datef = txtdatef.getValue(); 
        ev.setNom(txtnom.getText());
       
       ev.setDate_d(date);
        ev.setDate_f(datef);
        ev.setType(txttype.getText());
        ev.setLieu(txtlieu.getText());
        int x1 =  Integer.parseInt(txtnbpart.getText());
        int x2 =  Integer.parseInt(txtnbplaces.getText());

        ev.setNb_participants(x2);
        ev.setNb_places(x1);
        
         int id = Integer.parseInt(txtid.getText());
         ev.setId(id);
         
         es.modifierEvenement(ev);
    }
    //********activite*************


    @FXML
    private void ajouterAct(ActionEvent event) {
          if((txtname.getText().equals(""))||(txtdescription.getText().equals(""))||(txtimage.getText().equals(""))||(txtidevent.getText().equals(""))){
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
         }
          else if(!( Pattern.matches("[a-z,A-Z]*",txtname.getText()))){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nom de l'activite  doit etre de type String !");
            alert.showAndWait();
          
       }
          else if(txtdescription.getText().length()<20){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la description doit au moin contenir 20 caractéres");
            alert.showAndWait();
          
       }
          else{
        Activite av = new Activite();
        av.setNom(txtname.getText());
        av.setDescription(txtdescription.getText());
        av.setImage(txtimage.getText());
        int x3 =  Integer.parseInt(txtidevent.getText());
       av.setId_event(x3);
         
         
        act.ajouterActivite(av);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Activite ajoutée!");
            alert.show();
             ObservableList<Activite> items =FXCollections.observableArrayList();
        List<Activite> listact = act.afficherActivite();
        for(Activite r : listact) {
            String ch = r.toString();
            items.add(r);
        }
       
    listactivite.setItems(items);
           
          }
    }

    @FXML
    private void deleteAct(ActionEvent event) {
           int id = Integer.parseInt(txtID.getText());
        act.supprimerActivite(id);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Activite Supprimée!");
            alert.show();
               ObservableList<Activite> items =FXCollections.observableArrayList();
        List<Activite> listact = act.afficherActivite();
        for(Activite r : listact) {
            String ch = r.toString();
            items.add(r);
        }
       
    listactivite.setItems(items);
    }

    @FXML
    private void updateAct(ActionEvent event) {
      if((txtname.getText().equals(""))||(txtdescription.getText().equals(""))||(txtimage.getText().equals(""))||(txtidevent.getText().equals(""))){
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();
         }
          else if(!( Pattern.matches("[a-z,A-Z]*",txtname.getText()))){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("nom de l'activite  doit etre de type String !");
            alert.showAndWait();
          
       }
          else if(txtdescription.getText().length()<20){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la description doit au moin contenir 20 caractéres");
            alert.showAndWait();
          
       }
          else{
        Activite actt = new Activite();
        
        actt.setNom(txtname.getText());
        actt.setDescription(txtdescription.getText());
        actt.setImage(txtimage.getText());
    
        int x1 =  Integer.parseInt(txtidevent.getText());
       

        actt.setId_event(x1);
     
        
         int id = Integer.parseInt(txtID.getText());
         actt.setId(id);
         
         act.modifierActivite(actt);
      ObservableList<Activite> items =FXCollections.observableArrayList();
        List<Activite> listact = act.afficherActivite();
        for(Activite r : listact) {
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
    private void chercher(ActionEvent event) {
           EvenementService  n=new EvenementService();
           List<Evenement> R=   n.Chercher(rech.getText());
          
       ObservableList<Evenement> datalist = FXCollections.observableArrayList(R);

     
            listevent.setItems(datalist);
        
        
    }

    

   

   

}