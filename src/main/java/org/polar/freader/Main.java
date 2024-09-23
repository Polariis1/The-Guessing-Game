package org.polar.freader;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private Parent root;
    private org.polar.freader.controller controller;
    private final int startOffSetX = -10;
    private final int startOffSetY = 100;
    private final double startZoomScale = 2;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("start.fxml")));
        root = loader.load();
        stage.getIcons().add(new Image("https://cdn.discordapp.com/attachments/673164580075077632/1287723173353099375/Designer_1.png?ex=66f29529&is=66f143a9&hm=64d1f93d71a92f9d40870ed095c6d8273944a9e78b01df7fc0a73e9f6af471ef&"));

        // import controller
        controller = loader.getController(); //initialize controller
        controller.setMain(this); //set main application reference

        root.setTranslateX(startOffSetX);
        root.setTranslateY(startOffSetY);
        root.setScaleX(startZoomScale); //start with 000% zoom
        root.setScaleY(startZoomScale);

        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Highscore!");
        stage.setScene(scene);
        stage.setResizable(false);
        controller.SpawnSplashText();

        stage.show();
    }
    public void menuOutZoom(ActionEvent actionEvent) {
        // Reset initial translation and scale
        root.setTranslateX(startOffSetX);
        root.setTranslateY(startOffSetY);

        root.setScaleX(startZoomScale);
        root.setScaleY(startZoomScale);

        // Create a timeline to animate the zoom out effect
        Timeline zoomOutTimeline = new Timeline();

        // Define the keyframes for scaling
        KeyValue scaleXValue = new KeyValue(root.scaleXProperty(), 1.0);
        KeyValue scaleYValue = new KeyValue(root.scaleYProperty(), 1.0);

        // Define translations to bring it to the center
        KeyValue translateXValue = new KeyValue(root.translateXProperty(), 0);
        KeyValue translateYValue = new KeyValue(root.translateYProperty(), 0);

        // Create a keyframe with the desired duration
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), scaleXValue, scaleYValue, translateXValue, translateYValue);

        // Add the keyframe to the timeline
        zoomOutTimeline.getKeyFrames().add(keyFrame);

        zoomOutTimeline.play();
    }
    public org.polar.freader.controller getController() {
        return controller; //provide access to the controller
    }
    //game logic


    public static void main(String[] args) {
        launch();
    }
}