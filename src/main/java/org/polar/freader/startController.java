package org.polar.freader;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class startController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Main main;
    private logic logic;
    private final int startOffSetX = -10;
    private final int startOffSetY = 100;
    private final double startZoomScale = 2;

    public void setMain(Main main) {
        this.main = main;
    }
    public void setRoot(Parent root) {
        this.root = root;
    }
    public void setLogic(logic logic) {
        this.logic = logic;
    }

    @FXML
    public void initialize() {

        try {
            SpawnSplashText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //generate directory for save files
        String userHome = System.getProperty("user.home");
        Path directoryPath = Paths.get(userHome, "AppData", "Roaming", ".highscore", "saves");

        if (Files.notExists(directoryPath)) {
            new File(String.valueOf(directoryPath)).mkdirs();
        }else{
            System.out.println("\n"+"Directory already exists at: "+ directoryPath);
        }
    }

    public void menuOutZoom() {
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


    public void hidePlayBtn() {
        unzoomBtn.setDisable(true);
        unzoomBtn.setVisible(false);
    }

    @FXML
    private Button unzoomBtn;
    public void playOutZoom(ActionEvent event) throws IOException {
        if (main != null) {
            menuOutZoom();
        }
        hidePlayBtn();
    }

    public void menuToGame(javafx.event.ActionEvent event) throws IOException {
        if (main != null) {
            main.showGameScene();
        }
    }
    public void menuToOptions(javafx.event.ActionEvent event) throws IOException {
        if (main != null) {
            main.showOptionsScene();
        }
    }

    public void exitProgram() {
        System.exit(0);
    }
    @FXML
    private Label splashText;
    public void SpawnSplashText() throws IOException{

        InputStream inputStream = getClass().getResourceAsStream("/org/polar/freader/splashTextsList.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

        List<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }reader.close();

        int num = (int) (Math.random()*lines.size());
        System.out.println(num);
        String splashTxt = lines.get(num);
        if (splashText != null) {
            splashText.setText(splashTxt);
        }else {
            System.out.println("No splash text found");
        }
    }

    //Live in menu related above
    //Live in game related below


}