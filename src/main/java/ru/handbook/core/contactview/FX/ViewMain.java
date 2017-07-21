package ru.handbook.core.contactview.FX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by operator1 on 21.07.2017.
 */
public class ViewMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Contact View");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
