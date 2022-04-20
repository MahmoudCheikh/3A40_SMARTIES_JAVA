package com.smarties.test;

import com.smarties.entities.Users;
import com.smarties.tools.MaConnexion;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Smarties extends Application {

    public static Users user;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        MaConnexion connexion = MaConnexion.getInstance();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Smarties");
        showMainView();
    }

    public void showMainView() throws IOException {
        BorderPane mainLayout = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(mainLayout, 1366, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
