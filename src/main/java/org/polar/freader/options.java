package org.polar.freader;

import java.io.IOException;

public class options {
    private Main main;
    private startController start;

    public void setMain(Main main) {
        this.main = main;
    }
    public void setStartController(startController start){
        this.start = start;
    }

    public void optionsToMenu(javafx.event.ActionEvent event) throws IOException {
        if (main != null) {
            main.showStartScene();
        }else {
            System.err.println("main is: "+main);
        }
    }
}
