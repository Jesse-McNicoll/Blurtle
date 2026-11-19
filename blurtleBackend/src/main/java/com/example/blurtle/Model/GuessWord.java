package com.example.blurtle.Model;

import jakarta.validation.constraints.NotEmpty;


public class GuessWord {

    @NotEmpty(message = "Guess cannot be null or empty")
    String guessWord;

    public String getGuessWord() {
        return guessWord;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }
}
