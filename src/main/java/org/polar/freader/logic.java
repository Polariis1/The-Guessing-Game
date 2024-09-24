package org.polar.freader;

import javafx.event.ActionEvent;
import org.polar.freader.controller;

public class logic {
    private final controller controller;

    //pass controller instance to this class
    public logic(controller controller) {
        this.controller = controller;
    }
    public void numberGeneration() {
        String difficulty = controller.comboValues();
        System.out.println("OVER HERE: "+difficulty);
        if (difficulty != null) {
            switch (difficulty) {
                case "Easy" -> System.out.println("Easy lol");
                case "Medium" -> System.out.println("Medium lol");
                case "Hard" -> System.out.println("Hard lol");
                default -> System.out.println("Unknown lol");
            }
        } else {
            System.out.println("difficulty = null");
        }

    }
}
