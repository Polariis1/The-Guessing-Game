package org.polar.freader;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void menuToGame(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        System.out.println("Switch to Game");
    }

    public void gameToMenu(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

        cleanFiles(); //deletes empty save file

        System.out.println("Exit to Menu");
    }
    public void menuToOptions(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("option.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        System.out.println("Enter Options");

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

    public void exitProgram() {
        System.exit(0);
    }

    //Live in menu related above
    //Live in game related below

    @FXML
    public void initialize() {
        // populating the combobox with the difficulty values when fxml starts
        comboValues();
    }

    @FXML
    private Pane selectionBackground;

    public void LoadNewGame(javafx.event.ActionEvent event) throws IOException {
        selectionBackground.setVisible(false);
        generateDatabase();
    }

    // Method to populate ComboBox after the scene is set up
    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private void comboValues() {
        if (difficultyComboBox != null) {
            String[] difficulty = {"Easy", "Medium", "Hard"};
            difficultyComboBox.setItems(FXCollections.observableArrayList(difficulty));
            difficultyComboBox.setValue("Medium");  // Set default value
        }
    }
    public void gameDifficulty(javafx.event.ActionEvent event) {
            String difficulty = difficultyComboBox.getValue();
            System.out.println("Difficulty: " + difficulty);
        }

    private Path filePath;

    public void generateDatabase() throws IOException {
        //make it so if its empty and u return to the menu it gets removed.
        System.out.println("generateDatabases ran");

        String userHome = System.getProperty("user.home");
        Path directoryPath = Paths.get(userHome, "AppData", "Roaming", ".highscore", "saves2");

        if (Files.notExists(directoryPath)) {
            new File(String.valueOf(directoryPath)).mkdirs();
        }else{
            System.out.println("\n"+"Directory already exists at: "+ directoryPath);
        }
        int fileNum = 1;
        String saveFile;
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
        String data = "hello world darkness smile friend naurr what no way is that true"+"\n";
        if (filePath != null) {  // Ensure filePath is initialized
            Files.write(filePath, data.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Data written to file: " + filePath);
            printData.appendText(data + "\n");
        } else {
            System.out.println("Error: filePath is null. You must generate a database first.");
        }
    }
    @FXML
    private TextArea printData;

    public String readData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }
        } catch (IOException e) { //free up resources by closing the stream
            e.printStackTrace();
        }
        return null;
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

