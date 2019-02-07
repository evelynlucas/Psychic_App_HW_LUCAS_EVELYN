package org.pursuit.psychic_app_hw_lucas_evelyn.model;

public class GuessModel {

    private int correctGuess;
    private int wrongGuess;

    public GuessModel(int correctGuess, int wrongGuess) {
        this.correctGuess = correctGuess;
        this.wrongGuess = wrongGuess;
    }

    public int getCorrectGuess() {
        return correctGuess;
    }

    public int getWrongGuess() {
        return wrongGuess;
    }

    public void setCorrectGuess(int correctGuess) {
        this.correctGuess = correctGuess;
    }

    public void setWrongGuess(int wrongGuess) {
        this.wrongGuess = wrongGuess;
    }
}
