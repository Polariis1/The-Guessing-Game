package org.polar.freader;

public class saveData {
    private String filename = null;
    private String difficulty = null;
    private String guessAmount = null;
    private String username = null;

    public saveData() {}

    public saveData(String filename, String difficulty, String guessAmount, String username) {
        this.filename = filename;
        this.difficulty = difficulty;
        this.guessAmount = guessAmount;
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGuessAmount() {
        return guessAmount;
    }
    public void setGuessAmount(String guessAmount) {
        this.guessAmount = guessAmount;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
