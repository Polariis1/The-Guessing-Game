package org.polar.freader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private Path directoryPath;

    public void readData() throws IOException {
        String userHome = System.getProperty("user.home");
        directoryPath = Paths.get(userHome, "AppData", "Roaming", ".highscore", "saves");

        File[] saveFileList = directoryPath.toFile().listFiles();
        if (saveFileList != null) {
            for (File saveFile : saveFileList) {
                try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(saveFile)))) {
                    String line;
                    int num = 3;

                    readAndPrint.appendText("NEW SAVE:"+"\n");

                    while ((line = reader.readLine()) != null) {


                        sortData(line);


                        readAndPrint.appendText("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String sortData() throws IOException {
        //call read data to get some data.
        String[] lineArr = line.split(" ", 3);

        String test = Arrays.toString(lineArr[2].getBytes());
        System.out.println(test);

        num +=3;
        for (String a : lineArr) {
            readAndPrint.appendText(a+'\n');
        }readAndPrint.appendText("\n");
        return null;
    }

    public void triggerReadData(ActionEvent event) throws IOException {
        readData();
    }

}
