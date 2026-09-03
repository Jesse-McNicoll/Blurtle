package com.example.blurtle.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "puzzles")
public class Puzzle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
