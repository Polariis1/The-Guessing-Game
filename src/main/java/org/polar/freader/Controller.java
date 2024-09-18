package org.polar.freader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ComboBox<String> difficultyComboBox;
    //pub

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
}