
package com.smarties.test;

import com.mysql.jdbc.Constants;
import com.smarties.entities.Activite;
import com.smarties.entities.Evenement;
import com.smarties.services.EvenementService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AfficherEventController implements Initializable {
  public  Evenement currentEvenement;
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
          } catch (IOException ex) {
            System.out.println(ex.getMessage());
       }
        return parent;
    }

    private void AfficherAct(Activite a,Evenement e) throws IOException{
        currentEvenement=e;
     // if(a.getId()== currentEvenement.getId()){
        AnchorPane xx;
        xx = FXMLLoader.load(getClass().getResource("AfficherActivite.fxml"));
        a1.getChildren().setAll(xx);
       //}
    
    }
}
