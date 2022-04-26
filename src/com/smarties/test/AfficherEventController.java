
package com.smarties.test;

import com.mysql.jdbc.Constants;
import com.smarties.entities.Activite;
import com.smarties.entities.Evenement;
import com.smarties.services.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AfficherEventController implements Initializable {
  public static Evenement currentEvenement;
  Activite a=new  Activite();
 
    @FXML
    private Text topText;
    @FXML
    private VBox vboxevent;
    @FXML
    private AnchorPane a1;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        EvenementService tt = new EvenementService();
        List<Evenement> listE = tt.afficherEvenement();
        Collections.reverse(listE);
        if (!listE.isEmpty()) {
            for (Evenement e : listE) {      
	    vboxevent.getChildren().add(make(e));                        
                 }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            vboxevent.getChildren().add(stackPane);
        }
    }    
     public Parent make (Evenement e){
        Parent parent = null;
        try {
             parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModelEvent.fxml")));
             HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#nomeventtxt")).setText("Nom Event:" + e.getNom());
            ((Text) innerContainer.lookup("#datedebuttxt")).setText("Date Debut:" + e.getDate_d());
            ((Text) innerContainer.lookup("#datefintxt")).setText("Date Fin:" + e.getDate_f());
            ((Text) innerContainer.lookup("#lieutxt")).setText("Lieu:" + e.getLieu());
            ((Text) innerContainer.lookup("#typetxt")).setText("Type:" + e.getType());
            ((Text) innerContainer.lookup("#nbparticipantstxt")).setText("Nombre de participants:" + e.getNb_participants());
            ((Text) innerContainer.lookup("#nbplacestxt")).setText("Nombre de places:" + e.getNb_places());
          // ((Text) innerContainer.lookup("#createdAtText")).setText("CreatedAt : " + rdv.getCreatedAt());
           // ((Text) innerContainer.lookup("#updatedAtText")).setText("UpdatedAt : " + rdv.getUpdatedAt());
           ((Button) innerContainer.lookup("#afficherAct")).setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                     try {
                         AfficherAct(a,e);
                     } catch (IOException ex) {
                         Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
            ((Button) innerContainer.lookup("#participer")).setOnAction((event) -> {
                 try {
                     participerevent(e);
                 } catch (SQLException ex) {
                     Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             });
             ((Button) innerContainer.lookup("#nepasparticiper")).setOnAction((event) -> {
                 try {
                     nepasparticiperevent(e);
                 } catch (SQLException ex) {
                     Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             });
          } catch (IOException ex) {
            System.out.println(ex.getMessage());
       }
        return parent;
    }

    private void AfficherAct(Activite a,Evenement e) throws IOException{
 
      
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("AfficherActivite.fxml"));
        a1.getChildren().setAll(xx);
        
    
    }
   
   private void participerevent(Evenement e) throws SQLException{
         EvenementService t=new EvenementService();
       Boolean tr = true;
      if(t.verifierparticiper(e,Smarties.user.getId())==tr){
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("vous participez deja a l'évenement "+e.getNom());
             alert.showAndWait();}
      else{
             t.participer(e,Smarties.user.getId());
             Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
             alert1.setTitle("Information Dialog");
             alert1.setHeaderText(null);
             alert1.setContentText("vous participer maintenant a l'evenement "+e.getNom());
             Optional<ButtonType> showAndWait = alert1.showAndWait();
         }
   }
      private void nepasparticiperevent(Evenement e) throws SQLException{
         EvenementService t=new EvenementService();
       Boolean tr = false;
      if(t.verifierparticiper(e,Smarties.user.getId())==tr){         
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("vous devez deja participer a l'évenement "+e.getNom());
             alert.showAndWait();}
      else{
      t.supprimerparticipation(e,Smarties.user.getId());
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("vous ne participez plus a l'evenement "+e.getNom());
             alert.showAndWait();
      
              }
   }
   
}
