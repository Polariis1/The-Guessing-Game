package org.polar.freader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
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
    //public void comboValues() {}

    private Spinner<Integer> difficultySpinner;
    public void spinnerValues() {
        SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        difficultySpinner.setValueFactory(values);
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
    }
    public void gameDifficulty(javafx.event.ActionEvent event) {
        try {
            Integer difficulty = difficultySpinner.getValue();
            System.out.println("Spinner Value: " + difficulty);
        }catch (Exception e){System.out.println("Error! " + e.getMessage());}
    }
    public void generateDatabase(javafx.event.ActionEvent event) throws IOException {
        System.out.println("generateDatabases ran");


        String userHome = System.getProperty("user.home");
        System.out.print(userHome);

        //if (userHome+"/AppData/Roaming/highscore3/"){
            //check if directory exists if not make one!
        //s}
        File dir = new File(userHome+ "/AppData/Roaming/highscore3/");
        new File(String.valueOf(dir)).mkdirs();

        boolean dirCreated = dir.mkdirs();
        if (!dirCreated) {
            System.out.println("Directory creation failed: " + dir.getPath());
        }

        File databaseGeneration = new File(dir+"/text2.txt");
        boolean isFileCreated = databaseGeneration.createNewFile();

        if (isFileCreated){
            System.out.println("\n"+"File was successfully created at: "+dir);
        }else {
            System.out.println("\n"+"File was NOT created"+ "dir: "+dir);
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