package com.example.blurtle.Model;

public class GuessScore {

    String guessWord;
    GuessStatus guessStatus;

    public GuessStatus getGuessStatus() {
        return guessStatus;
    }

    public void setGuessStatus(GuessStatus guessStatus) {
        this.guessStatus = guessStatus;
    }

    public String getGuessWord() {
        return guessWord;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }

}
