package org.polar.freader;

public class logic {

    private gameController controller;
    public void setController(gameController controller) {
        this.controller = controller;
    }


    int guessingNum = 0;

    public void numberGeneration(String difficulty) {
        System.out.println("OVER HERE: "+difficulty);
        int difficultyNums = 0;
        if (difficulty != null) {
            if(difficulty.equals("Easy")) {
                difficultyNums = 100;
            }else if (difficulty.equals("Medium")) {
                difficultyNums = 1000;
            }else if (difficulty.equals("Hard")) {
                difficultyNums = 10000;
            }System.out.println(difficultyNums);

            guessingNum = (int) (Math.random() * difficultyNums);
            System.out.println("Generated number: "+guessingNum);

        } else {
            System.out.println("ddddifficulty = null");
        }
    }
    public int guessAmount;

    public void numberGuessing(Integer num) {
        System.out.println(num);
        if (num == guessingNum) {
            System.out.println("Guess Correct");
            controller.comboValues();
            controller.StatusLabel("Guess Correct!");
            System.out.println("Amount of guesses "+ guessAmount);
            guessAmount = 1;
        }else if (num >= guessingNum) {
            System.out.println("Lower");
            controller.StatusLabel("Lower");
            guessAmount++;
        }else {
            System.out.println("Higher");
            controller.StatusLabel("Higher");
            guessAmount++;
        }
    }
}
