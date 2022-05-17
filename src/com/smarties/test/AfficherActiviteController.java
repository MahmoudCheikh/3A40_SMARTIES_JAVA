/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;


import com.smarties.entities.Activite;
import com.smarties.entities.Evenement;
import com.smarties.services.ActiviteService;
import com.smarties.services.EvenementService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AfficherActiviteController implements Initializable {

    @FXML
    private Text topText;
    @FXML
    private VBox vboxActivite;
    @FXML
    private Button back;
    
    
    private int idEvenement;
    
     
    /**
     * Initializes the controller class.
     */  
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     
    }   
  
      public Parent make (Activite e){
        Parent parent = null;
        
        try {
             parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModelActivite.fxml")));
             HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#nomactivitetxt")).setText("Nom Activite:" + e.getNom());
            ((Text) innerContainer.lookup("#descriptionactivitetxt")).setText("Description:" + e.getDescription());
           // ((Text) innerContainer.lookup("#imageactivitetxt")).setText("image:" + e.getImage());
            //JFileChooser chooser=new JFileChooser();
       
            /*Image img =new Image(new FileInputStream(e.getImage()));
            ((ImageView) innerContainer.lookup("#image")).setImage(img);*/
                        File imageFile = new File(GuiProduitController.imgUploadDir + "/" + e.getImage());
        Image imagea = new Image(imageFile.toURI().toString());

            ((ImageView) innerContainer.lookup("#image")).setImage(imagea);
        
                 } catch (IOException ex) {
            System.out.println(ex.getMessage());
       }
        return parent;
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvent.fxml"));
}
     public void setIdEvenement(int a){
        
        this.idEvenement= a;
    
        System.out.println("set id"+this.idEvenement);
      
        
    }
      public void setActiviteEvenement(List<Activite> a){
      
         //Collections.reverse(listA);
        if (!a.isEmpty()) {
            for (Activite ac : a) {
          
	    vboxActivite.getChildren().add(make(ac));                        
                System.out.println("activite par event ="+ac.getNom());
               }
            }  
        else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            vboxActivite.getChildren().add(stackPane);
        }
      
        
    } 
        
    
    

}
