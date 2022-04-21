/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.smarties.entities.Stock;
import com.smarties.services.StockService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class StatProductsController implements Initializable {

    @FXML
    private PieChart pieChart;
    private StockService st = new StockService();
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Stock sto = new Stock();
                pieChart.setTitle("Quantite"); 
        pieChart.getData().setAll(new PieChart.Data("Quantite <20", st.Recherche3()), new PieChart.Data("Quantite [20-50]", st.Recherche1()),
                new PieChart.Data("Quantite [50-70]", st.Recherche2()), new PieChart.Data("Quantite [70-100]", st.Recherche4()));
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiBack.fxml"));
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    
}
