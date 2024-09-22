package org.polar.freader;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Main main;

    public void hidePlayBtn() {
        unzoomBtn.setDisable(true);
        unzoomBtn.setVisible(false);
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void menuToGame(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        System.out.println("Switch to Game");
    }

    public void gameToMenu(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        cleanFiles(); //deletes empty save file
        SpawnSplashText(); //spawns splash text

        System.out.println("Exit to Menu");
    }
    public void menuToOptions(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("options.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
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
    @FXML
    private Button unzoomBtn;
    public void playOutZoom(javafx.event.ActionEvent event) throws IOException {
        if (main != null) {
            main.menuOutZoom(event);
        }
        hidePlayBtn();
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

    private Path directoryPath;

    @FXML
    public void initialize() {
        //populating the combobox with the difficulty values when fxml starts
        comboValues();
        try {
            SpawnSplashText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //generate directory for save files
        String userHome = System.getProperty("user.home");
        directoryPath = Paths.get(userHome, "AppData", "Roaming", ".highscore", "saves2");

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
    private ComboBox<String> difficultyComboBox;

    @FXML
    private void comboValues() {
        if (difficultyComboBox != null) {
            String[] difficulty = {"Easy", "Medium", "Hard"};
            difficultyComboBox.setItems(FXCollections.observableArrayList(difficulty));
        }
    }
    public void gameDifficulty(javafx.event.ActionEvent event) {
            String difficulty = difficultyComboBox.getValue();
            System.out.println("Difficulty: " + difficulty);
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

}