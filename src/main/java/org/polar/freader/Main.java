package org.polar.freader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private logic logic = new logic(); // Initialize logic here
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage; // Store the primary stage reference
        showStartScene();
    }

    public void showStartScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/polar/freader/start.fxml"));
        Parent root = loader.load();

        startController controller = loader.getController();
        controller.setMain(this); //pass main reference to controller
        controller.setRoot(root); //set the root node in the controller

        root.setTranslateX(-10);
        root.setTranslateY(100);
        root.setScaleX(2);
        root.setScaleY(2);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene); // Use the primary stage
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showGameScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/polar/freader/game.fxml"));
        Parent root = loader.load();
        gameController gameController = loader.getController();
        gameController.setLogic(logic); // Pass logic instance to the gameController

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene); // Use the primary stage
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showOptionsScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/polar/freader/options.fxml"));
        Parent root = loader.load();
        startController startController = loader.getController();
        startController.setMain(this); // Pass main reference to controller

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene); // Use the primary stage
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
