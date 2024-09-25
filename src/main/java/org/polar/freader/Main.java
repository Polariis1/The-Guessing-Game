package org.polar.freader;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class Main extends Application {
    private logic logic = new logic(); // Initialize logic here
    private startController startController;

    private final int startOffSetX = -10;
    private final int startOffSetY = 100;
    private final double startZoomScale = 2;

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage; // Store the primary stage reference
        showStartScene();
    }

    private boolean isOtherSceneLoaded = false;

    public void showStartScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/polar/freader/start.fxml"));
        Parent root = loader.load();

        startController = loader.getController();
        startController.setMain(this); //pass main reference to controller
        startController.setRoot(root); //set the root node in the controller

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showGameScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/polar/freader/game.fxml"));
        Parent root = loader.load();

        gameController gameController = loader.getController();
        gameController.setMain(this);  // Pass Main instance to the gameController
        gameController.setLogic(logic);  // Pass logic instance to the gameController
        gameController.setStartController(startController);
        logic.setController(gameController);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene); // Now set the scene after zoom completes
        primaryStage.setResizable(false);
        primaryStage.show(); // Show the stage
        isOtherSceneLoaded = true; // Indicate that the scene has loaded

    }

    public void showOptionsScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/polar/freader/options.fxml"));
        Parent root = loader.load();

        options options = loader.getController();
        options.setMain(this);  // Pass Main instance to the gameController
        options.setStartController(startController);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene); // Use the primary stage
        primaryStage.setResizable(false);
        primaryStage.show();
        isOtherSceneLoaded = true;
    }

    public void showLeaderboardScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/polar/freader/leaderboard.fxml"));
        Parent root = loader.load();

        Leaderboard leaderboard = loader.getController();
        leaderboard.setMain(this);
        leaderboard.setStartController(startController);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene); // Use the primary stage
        primaryStage.setResizable(false);
        primaryStage.show();
        isOtherSceneLoaded = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
