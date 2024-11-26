package org.polar.freader;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Leaderboard {

    private Main main;
    private startController start;
    private gameController gameController;

    public void setMain(Main main) {
        this.main = main;
    }
    public void setStartController(startController start) {
        this.start = start;
    }
    public void setController(gameController gamecontroller) {
        this.gameController = gamecontroller;
    }

    public void leaderboardToMenu(javafx.event.ActionEvent event) throws IOException {
        if (main != null) {
            main.showStartScene();
        }else {
            System.err.println("main is: "+main);
        }
    }
    @FXML
    private TextArea readAndPrint;

    private String userHome = System.getProperty("user.home");
    private Path directoryPath = Paths.get(userHome, "AppData", "Roaming", ".highscore", "saves");


    //put all the first words in a list then increase number[0] to go through them
    //then do the same with the other words.

    public void readData() throws IOException {

        File[] saveFileList = directoryPath.toFile().listFiles();
        if (saveFileList != null) {
            for (File saveFile : saveFileList) {
                try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(saveFile)))) {
                    String line;
                    int num = 3;

                    readAndPrint.appendText("NEW SAVE:"+"\n");

                    while ((line = reader.readLine()) != null) {
                        
                        sortData(line);

                        String[] lineArr = line.split(" ", 3);

                        String test = Arrays.toString(lineArr[2].getBytes());
                        System.err.println(test); // LOOK I AM OVER HERE

                        readAndPrint.appendText("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String sortData(String line) throws IOException {
        //call read data to get some data.
        String[] lineArr = line.split(" ", 3);

        String test = Arrays.toString(lineArr[2].getBytes());
        System.out.println(test);

        for (String a : lineArr) {
            readAndPrint.appendText(a+'\n');
        }readAndPrint.appendText("\n");
        return null;
    }

    private Path filePath;
    private String saveFile;

    @FXML
    private TableView LeaderboardTableView = new TableView<>();

    public void triggerReadData(ActionEvent event) throws IOException {

        TableColumn<saveData, String> filenameCol = new TableColumn<>("Filename");
        filenameCol.setCellValueFactory(new PropertyValueFactory<>("filename"));

        TableColumn<saveData, String> difficultyCol = new TableColumn<>("Difficulty");
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

        TableColumn<saveData, String> guessAmountCol = new TableColumn<>("Guess Amount");
        guessAmountCol.setCellValueFactory(new PropertyValueFactory<>("guessAmount"));

        TableColumn<saveData, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));


        LeaderboardTableView.getColumns().addAll(filenameCol, difficultyCol, guessAmountCol, usernameCol);


        ObservableList<saveData> data = FXCollections.observableArrayList();

        File[] saveFileList = directoryPath.toFile().listFiles();
        if (saveFileList != null) {
            for (File saveFile : saveFileList) {
                try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(saveFile)))) {
                    String line;
                    int num = 3;


                    while ((line = reader.readLine()) != null) {

                        String[] lineArr = line.split(" ", 4);
                        String filename = lineArr[0];
                        String difficulty = lineArr[1];
                        String guessAmount = lineArr[2];
                        String username = lineArr[3];

                        Arrays.toString(filename.getBytes());
                        Arrays.toString(difficulty.getBytes());
                        Arrays.toString(guessAmount.getBytes());
                        Arrays.toString(username.getBytes());

                        data.add(new saveData(filename, difficulty, guessAmount, username));

                        String test = Arrays.toString(lineArr[2].getBytes());
                        System.err.println(test); // LOOK I AM OVER HERE

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LeaderboardTableView.setItems(data);
    }
}
