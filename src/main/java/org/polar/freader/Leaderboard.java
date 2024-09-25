package org.polar.freader;

import java.io.IOException;

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
}
