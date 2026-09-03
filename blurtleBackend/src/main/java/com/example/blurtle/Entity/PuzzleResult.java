package com.example.blurtle.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="puzzle_results")
@IdClass(PuzzleResultId.class)
public class PuzzleResult {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "puzzle_id")
    private Puzzle puzzle;

    private Long numGuesses;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Long getNumGuesses() {
        return numGuesses;
    }

    public void setNumGuesses(Long numGuesses) {
        this.numGuesses = numGuesses;
    }

    public Boolean getPuzzleSolved() {
        return puzzleSolved;
    }

    public void setPuzzleSolved(Boolean puzzleSolved) {
        this.puzzleSolved = puzzleSolved;
    }

    private Boolean puzzleSolved;

    public PuzzleResult() {
    }

    public PuzzleResult(User user, Puzzle puzzle, Long numGuesses, Boolean puzzleSolved) {
        this.user = user;
        this.puzzle = puzzle;
        this.numGuesses = numGuesses;
        this.puzzleSolved = puzzleSolved;
    }

    @Override
    public String toString() {
        return "PuzzleResults{" +
                "userId=" + user +
                ", puzzleId=" + puzzle +
                ", numGuesses=" + numGuesses +
                ", puzzleSolved=" + puzzleSolved +
                '}';
    }

}

