package com.example.blurtle.Model;

public class GuessScore {

    String guessWord;
    boolean isValidWord;
    boolean isValidGuess;
    boolean isExactMatch;
    int length;
    int score;
    int time;

    public boolean isValidGuess() {
        return isValidGuess;
    }

    public void setValidGuess(boolean validGuess) {
        isValidGuess = validGuess;
    }

    public String getGuessWord() {
        return guessWord;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }

    public boolean isExactMatch() {
        return isExactMatch;
    }

    public void setExactMatch(boolean exactMatch) {
        isExactMatch = exactMatch;
    }

    public boolean isValidWord() {
        return isValidWord;
    }

    public void setValidWord(boolean validWord) {
        isValidWord = validWord;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
