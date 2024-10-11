package org.polar.freader;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Spinner;
import org.polar.freader.startController;

import java.io.*;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class gameController {
    public Spinner<Integer> spinner;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Main main;
    private logic logic;
    private startController start;
    private Leaderboard leaderboard;

    public void setMain(Main main) {
        this.main = main;
    }
    public void setLogic(logic logic) {
        this.logic = logic;
    }
    public void setStartController(startController start){
        this.start = start;
    }
    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    public void gameToMenu(javafx.event.ActionEvent event) throws IOException {
        if(main != null) {
            main.showStartScene();

            //(zoom)menu -> game -> (zoom(dont want))menu
            //find out a way to set these to 0 so no zoom 
            //        root.setTranslateX(0);
            //        root.setTranslateY(0);
            //        root.setScaleX(0);
            //        root.setScaleY(0);
        }else {
            System.err.println("Error main is null");
        }
        cleanFiles(); //deletes empty save file

        System.out.println("Exit to Menu");
    }

    public void cleanFiles() throws IOException {
        //removes save file if returning to menu and its empty.
        if (filePath != null && Files.exists(filePath) && Files.size(filePath) == 0) {
            Files.delete(filePath);
            System.out.println("File deleted successfully: " + filePath);
        } else if (filePath != null && Files.exists(filePath)) {
            System.out.println("File is not empty, not deleting: " + filePath);
        }else {
            System.out.println("File does not exist, not deleting: " + filePath);
        }
    }

    private Path directoryPath;

    @FXML
    public void initialize() {

        //SOLVED IT!! IT SAYS NULL BECAUSE INITIALIZE() STARTS WITH THE START SCENE
        // NOT THE GAME SCENE! make new controller for specific scene
        if (difficultyComboBox != null) {
            String[] difficulty = {"Easy", "Medium", "Hard"};
            difficultyComboBox.setItems(FXCollections.observableArrayList(difficulty));
        }
        //generate directory for save files
        String userHome = System.getProperty("user.home");
        directoryPath = Paths.get(userHome, "AppData", "Roaming", ".highscore", "saves");

        if (Files.notExists(directoryPath)) {
            new File(String.valueOf(directoryPath)).mkdirs();
        }else{
            System.out.println("\n"+"Directory already exists at: "+ directoryPath);
        }
    }

    @FXML
    private Pane selectionBackground;

    public void LoadNewGame(javafx.event.ActionEvent event) throws IOException {
        selectionBackground.setVisible(false);
        selectOldSaveBackground.setVisible(false);
        generateDatabase();
    }
    @FXML
    private Pane selectOldSaveBackground;

    public void LoadOldGame(javafx.event.ActionEvent event) throws IOException {
        selectionBackground.setVisible(false);
        savesList();
    }
    public void goBackToSelection(javafx.event.ActionEvent event) throws IOException {
        selectionBackground.setVisible(true);
    }

    @FXML
    private VBox savesVBox;
    private Button saves;

    public void savesList() throws IOException {
        savesVBox.setAlignment(Pos.CENTER);
        savesVBox.setSpacing(10);

        File[] saveFileList = directoryPath.toFile().listFiles();

        List<String> images = Arrays.asList("btnImage1", "btnImage2", "btnImage3",
                "btnImage4","btnImage5","btnImage6","btnImage7","btnImage8");

        if(saveFileList != null) {
            for (File save : saveFileList) {
                //add buttons
                saves = new Button(save.getName());
                saves.getStyleClass().addAll("savesButton");

                //adds random image to button
                int random = (int) (Math.random()* images.size());
                String backgroundImg = images.get(random);
                saves.getStyleClass().add(backgroundImg);

                saves.setPrefHeight(50);
                saves.setPrefWidth(550);
                saves.setText(save.getName());
                saves.setOnAction(e -> {
                    selectOldSaveBackground.setVisible(false);
                    filePath = Path.of(directoryPath +"/"+ save.getName());
                });

                savesVBox.getChildren().add(saves);
            }
        }else
            System.out.println("No save files found");
    }

    @FXML
    public ComboBox<String> difficultyComboBox;

    @FXML
    public void comboValues() {
            String value = difficultyComboBox.getValue();
            System.out.println("value: " + value);

        if(logic != null){
            logic.numberGeneration(difficultyComboBox.getValue());
        }else{
            System.err.println("logic is null");
        }
    }

    public void guess(ActionEvent event) throws NoSuchMethodException, IOException {
        logic.numberGuessing(spinner.getValue());
    }
    @FXML
    private Label statusLabel;

    public void StatusLabel(String lowerOrHigher) {
        statusLabel.setText(lowerOrHigher);
    }

    private Path filePath;
    private String saveFile;

    public void generateDatabase() throws IOException {
        //make it so if its empty and u return to the menu it gets removed.
        System.out.println("generateDatabases ran");

        int fileNum = 1;
        do { //runs once, before condition is checked at the end.
            saveFile = "save" + fileNum;
            filePath = Path.of(directoryPath + "/" + saveFile + ".txt");
            System.out.println(filePath.toString() + " FileNum " + fileNum);

            fileNum++;
        } while (Files.exists(filePath)); //does the file exist? it does? run again!

        File databaseGeneration = new File(directoryPath +"/"+saveFile+".txt");
        boolean isFileCreated = databaseGeneration.createNewFile();

        if (isFileCreated){
            System.out.println("\n"+"File was successfully created at: "+ directoryPath);
        }else {
            System.out.println("\n"+"File already exists at: "+ directoryPath);
        }
    }

    public void writeData() throws IOException {
        String data = saveFile +" "+ difficultyComboBox.getValue() +" "+ String.valueOf(logic.guessAmount)+'\n';
        if (filePath != null) {  //ensure filePath is initialized
            Files.write(filePath, data.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Data written to file: " + filePath);
            printData.appendText(data + "\n");
        } else {
            System.out.println("Error: filePath is null. You must generate a database first.");
        }
    }
    @FXML
    private TextArea printData;


}