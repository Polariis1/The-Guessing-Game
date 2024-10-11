package org.polar.freader;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
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

    private int num = 0;
    @FXML
    private TreeTableView<File> treeTableView = new TreeTableView<>();

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

    public void triggerReadData(ActionEvent event) throws IOException {
        // Create root item for the file tree (starting from the root directory "/")
        TreeItem<File> root = new TreeItem<>(new File("/"));

        // Populate the tree with file structure

        // Create columns
        TreeTableColumn<File, String> saveNameCol = new TreeTableColumn<>("Saves:");
        TreeTableColumn<File, Number> saveGuessesCol = new TreeTableColumn<>("Guesses:");

        int fileNum = 1;
        do { //runs once, before condition is checked at the end.
            saveFile = "save" + fileNum;
            filePath = Path.of(directoryPath + "/" + saveFile + ".txt");
            System.out.println(filePath+ "saveFile: "+saveFile);

            fileNum++;
        } while (Files.exists(filePath)); //does the file exist? it does? run again!


        saveNameCol.setCellValueFactory(param -> {
            TreeItem<File> save = param.getValue();
            return new SimpleStringProperty(save.getValue().getName());
        });

        // Set CellValueFactory for the file size column (use length() to get file size)
        saveGuessesCol.setCellValueFactory(param -> {
            TreeItem<File> save = param.getValue();
            return new SimpleStringProperty(save.getValue().getName()).length();
        }); //read file guess

            File[] saveFileList = directoryPath.toFile().listFiles();
            assert saveFileList != null;
            for (File saves : saveFileList) {
                System.out.println(saves.toString());
                TreeItem<File> saveRoot = new TreeItem<>(saves);
                root.getChildren().add(saveRoot);
            }

        // Add columns to the TreeTableView
        treeTableView.getColumns().setAll(saveNameCol, saveGuessesCol);

        treeTableView.setRoot(root);
        treeTableView.setShowRoot(true);
    }
}
