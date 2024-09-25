package org.polar.freader;

import javafx.animation.Interpolator;
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
    private Timeline zoomInTimeline;

    public void menuToSceneTransition() {
        //start with normal scale
        root.setScaleX(1.0); // Normal size
        root.setScaleY(1.0); // Normal size

        //centering
        root.setTranslateX(0);
        root.setTranslateY(0);


        zoomInTimeline = new Timeline();

        //zoom in
        KeyValue scaleXValue = new KeyValue(root.scaleXProperty(), 10.0, Interpolator.EASE_BOTH);
        KeyValue scaleYValue = new KeyValue(root.scaleYProperty(), 10.0, Interpolator.EASE_BOTH);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), scaleXValue, scaleYValue); // 1.5 seconds duration

        // Add the keyframe to the timeline
        zoomInTimeline.getKeyFrames().add(keyFrame);

        zoomInTimeline.play();
    }

    public void menuToGame(ActionEvent event) throws IOException {
        if (main != null) {
            menuToSceneTransition();

            zoomInTimeline.setOnFinished(ev -> {
                try {
                    main.showGameScene();
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
        } else {
            System.err.println("main is: " + main);
        }
    }

    public void menuToOptions(javafx.event.ActionEvent event) throws IOException {
        if (main != null) {
            main.showOptionsScene();
        }else {
            System.err.println("main is: "+main);
        }
    }
    public void menuToLeaderboard(ActionEvent event) throws IOException {
        if (main != null) {
            main.showLeaderboardScene();
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