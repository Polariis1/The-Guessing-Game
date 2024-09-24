package org.polar.freader;

import javafx.event.ActionEvent;

public class logic {
    controller controller = new controller();
    public void numberGeneration() {
        String difficulty = controller.gameDifficulty();
        switch (difficulty) {
            case "Easy" -> System.out.println("Easy lol");
            case "Medium" -> System.out.println("Medium lol");
            case "Hard" -> System.out.println("Hard lol");
            default -> System.out.println("Unknown lol");
        }
    }

}
