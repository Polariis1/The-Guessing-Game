package org.polar.freader;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.nio.file.*;
import javafx.scene.control.ComboBox;

import java.io.File;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ComboBox<String> difficultyComboBox;
    public void comboValues() {
        String[] difficulty = {"Easy", "Medium", "Hard"};
        difficultyComboBox.setItems(FXCollections.observableArrayList(difficulty));
        difficultyComboBox.setValue("Medium");
    }

    public void gameToMenu(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Exit to menu.");
    }

    public void MenuToGame(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("switch to game scene.");
        //make popup menu to either choose old save or make new. boolean true or false then send to generatedatabase.
    }
    public void gameDifficulty(javafx.event.ActionEvent event) {
        String difficulty = difficultyComboBox.getValue();
        System.out.println("Difficulty: " + difficulty);
    }
    public void generateDatabase(javafx.event.ActionEvent event) throws IOException {
        System.out.println("generateDatabases ran");

        String userHome = System.getProperty("user.home");
        Path directoryPath = Paths.get(userHome, "AppData","Roaming", ".highscore", "saves2");

        if (Files.notExists(directoryPath)) {
            new File(String.valueOf(directoryPath)).mkdirs();
        }else{
            System.out.println("\n"+"Directory already exists at: "+directoryPath);
        }

        String saveFile = "save"+3;
        File databaseGeneration = new File(directoryPath+"/"+saveFile+".txt");
        boolean isFileCreated = databaseGeneration.createNewFile();

        if (isFileCreated){
            System.out.println("\n"+"File was successfully created at: "+directoryPath);
        }else {
            System.out.println("\n"+"File was already exists at: "+directoryPath);
        }

        }/*
        String fileName = "text.txt";
        String directoryPath = "C:\\Program Files\\highscore\\databases\\";

        File databaseGeneration = new File(directoryPath + fileName);
        while(!databaseGeneration.createNewFile()){
            int fileNumber=+1;
            databaseGeneration = new File("file"+ String.valueOf(fileNumber)+".txt");
            System.out.println("made new file at: " + databaseGeneration.getAbsolutePath());
        }*/
        //while()

}