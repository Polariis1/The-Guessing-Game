package org.polar.freader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Highscore!");
        stage.setScene(scene);
        stage.show();

    }

    public void generateNumbers() throws IOException {}

    /*
    File databaseGeneration = new File("File");

    while(databaseGeneration.createfile() =!){
        int fileNumber=+1;
        File databaseGeneration = new File("File"+ String.valueOf(fileNumber));
    }*/

    public static void main(String[] args) {
        launch();
    }
}