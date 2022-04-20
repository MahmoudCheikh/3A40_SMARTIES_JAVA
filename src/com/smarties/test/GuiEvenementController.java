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
import com.smarties.services.EnvoyerEmail;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Window;
import javafx.util.Callback;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author user
 */
public class GuiEvenementController implements Initializable {

    private EvenementService es = new EvenementService();
    private ActiviteService act = new ActiviteService();
   private EnvoyerEmail mail= new EnvoyerEmail();
      Connection cnx;

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
    @FXML
    private Button tri;
    @FXML
    private TextField cherA;
    @FXML
    private Button rechercheA;
    @FXML
    private Button upload;
    @FXML
    private ImageView img;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField txtimp;
    @FXML
    private Button impression;
    @FXML
    private ImageView afficheview;
    @FXML
    private AnchorPane noimagefound;
 
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
            ArrayList a1 = (ArrayList) es.afficherEvenement();
            listevent.getItems().addAll(a1);
            ArrayList a2 = (ArrayList) act.afficherActivite();
            listactivite.getItems().addAll(a2);
    
            
       /* String query = "select id from evenement ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            ObservableList<Integer> list1 = FXCollections.observableArrayList (rs.getInt(query));
            while(rs.next()){
                txtidevent.setItems(list1);} */    
}
   private void refresh() {
        List<Evenement> event = es.afficherEvenement();
        listevent.getItems().clear();
        listevent.getItems().addAll(event);
    }
   private void refresh1() {
        List<Activite> actt = act.afficherActivite();
        listactivite.getItems().clear();
        listactivite.getItems().addAll(actt);
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
         else if(txtdated.getValue().compareTo(txtdatef.getValue())> 0) {
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
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setContentText(" Mail envoyé!");
            alert1.show();   
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
     mail.envoyer(txtnom.getText());
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
         refresh();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Evenement modifié!");
            alert.show();
    }
    }
    //********activite*************


    @FXML
    private void ajouterAct(ActionEvent event) throws SQLException {
          if((txtname.getText().equals(""))||(txtdescription.getText().equals(""))||(txtimage.getText().equals(""))){
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
      if((txtname.getText().equals(""))||(txtdescription.getText().equals(""))||(txtimage.getText().equals(""))){
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
    refresh1();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("modification effectuée!");
            alert.show();
    
    } 
}
//chercher evenement
    @FXML
    private void chercher(ActionEvent event) {
           EvenementService  n=new EvenementService();
           List<Evenement> R=  n.Chercher(rech.getText());
          
       ObservableList<Evenement> datalist = FXCollections.observableArrayList(R);

            listevent.setItems(datalist);
        
        
    }


//recupération des données evenement
    @FXML
    private void recupdonnees(MouseEvent event) {
         String   nom;
         int id; 
         LocalDate dated;
         LocalDate datef;
         String   type;
         String   lieu;
         int nbpart; 
         int nbplaces;
        nom = listevent.getSelectionModel().getSelectedItem().getNom();
	txtnom.setText(nom);
        id = listevent.getSelectionModel().getSelectedItem().getId();
        txtid.setText(String.valueOf(id));
        dated = listevent.getSelectionModel().getSelectedItem().getDate_d();
        txtdated.setValue(dated);
        datef = listevent.getSelectionModel().getSelectedItem().getDate_f();
        txtdatef.setValue(datef);
        type = listevent.getSelectionModel().getSelectedItem().getType();
	txttype.setText(type);
        lieu = listevent.getSelectionModel().getSelectedItem().getLieu();
	txtlieu.setText(lieu);
        nbpart = listevent.getSelectionModel().getSelectedItem().getNb_participants();
        txtnbpart.setText(String.valueOf(nbpart));
        nbplaces = listevent.getSelectionModel().getSelectedItem().getNb_places();
        txtnbplaces.setText(String.valueOf(nbplaces));
    }
//recuperation des donnees activité 
    @FXML
    private void recupdonneesAct(MouseEvent event) {
         String   nom;
         int id; 
         String   description;
         String   image;
         int idE;
        nom = listactivite.getSelectionModel().getSelectedItem().getNom();
	txtname.setText(nom);
        id = listactivite.getSelectionModel().getSelectedItem().getId();
        txtID.setText(String.valueOf(id));
        description = listactivite.getSelectionModel().getSelectedItem().getDescription();
	txtdescription.setText(description);
        image = listactivite.getSelectionModel().getSelectedItem().getImage();
	txtimage.setText(image);
         idE = listactivite.getSelectionModel().getSelectedItem().getId_event();
      //  txtidevent.setText(String.valueOf(idE));
    }

    @FXML
    private void trierpardate(ActionEvent event) {
        List<Evenement> g = es.trier();
        ObservableList<Evenement> datalist = FXCollections.observableArrayList(g);
        listevent.setItems(datalist);
    }
    
   //chercher Activite
     @FXML
    private void chercherA(ActionEvent event) {
            ActiviteService  n=new ActiviteService();
           List<Activite> R=  n.Chercher(cherA.getText());
          ObservableList<Activite> items =FXCollections.observableArrayList(R);

            listactivite.setItems(items);
    }

    @FXML
    private void uploadimage(ActionEvent event) throws FileNotFoundException {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null); 
        File f=chooser.getSelectedFile(); 
        String Filename=f.getAbsolutePath(); 
        txtimage.setText(Filename);
        Image image =new Image(new FileInputStream(f));
        img.setImage(image);
        imgview.setVisible(false);
            
    }

    @FXML
    private void uploadAffiche(ActionEvent event) throws FileNotFoundException {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null); 
        File f=chooser.getSelectedFile(); 
        String Filename=f.getAbsolutePath(); 
        txtimp.setText(Filename);
        Image image =new Image(new FileInputStream(f));
        afficheview.setImage(image);
        imgview.setVisible(false);
            
    }

    @FXML
    private void Impression(ActionEvent event) {
        
           ImageView imageView =new ImageView(afficheview.getImage());
            Printer printer = Printer.getDefaultPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
            double scaleX = pageLayout.getPrintableWidth() / imageView.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / imageView.getBoundsInParent().getHeight();
            imageView.getTransforms().add(new Scale(scaleX, scaleY));

            PrinterJob job = PrinterJob.createPrinterJob();
          Window window=null; 
                  if (job.showPageSetupDialog(window)) { 
             boolean success = job.showPrintDialog(window);
                    if (success) {
                        job.endJob();
                    }                
      }
    }         

    
            
   
    

   
}