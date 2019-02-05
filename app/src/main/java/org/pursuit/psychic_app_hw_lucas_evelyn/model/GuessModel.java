package org.pursuit.psychic_app_hw_lucas_evelyn.model;

public class GuessModel {

    private int correct_guess;
    private int wrong_guess;

    public GuessModel(int correct_guess, int wrong_guess) {
        this.correct_guess = correct_guess;
        this.wrong_guess = wrong_guess;
    }

    public int getCorrect_guess() {
        return correct_guess;
    }

    public int getWrong_guess() {
        return wrong_guess;
    }

    public void setCorrect_guess(int correct_guess) {
        this.correct_guess = correct_guess;
    }

    public void setWrong_guess(int wrong_guess) {
        this.wrong_guess = wrong_guess;
    }
}
