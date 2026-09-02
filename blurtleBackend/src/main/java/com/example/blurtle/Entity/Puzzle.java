package com.example.blurtle.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Puzzle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate puzzleDate;
    private String puzzleWord;
    private String scrambledWord;

    public Puzzle(){}

    public Puzzle(LocalDate puzzleDate, String puzzleWord, String scrambledWord) {
        this.puzzleDate = puzzleDate;
        this.puzzleWord = puzzleWord;
        this.scrambledWord = scrambledWord;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "puzzleDate=" + puzzleDate +
                ", puzzleWord='" + puzzleWord + '\'' +
                ", scrambledWord='" + scrambledWord + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPuzzleDate() {
        return puzzleDate;
    }

    public void setPuzzleDate(LocalDate puzzleDate) {
        this.puzzleDate = puzzleDate;
    }

    public String getPuzzleWord() {
        return puzzleWord;
    }

    public void setPuzzleWord(String puzzleWord) {
        this.puzzleWord = puzzleWord;
    }

    public String getScrambledWord() {
        return scrambledWord;
    }

    public void setScrambledWord(String scrambledWord) {
        this.scrambledWord = scrambledWord;
    }
}
