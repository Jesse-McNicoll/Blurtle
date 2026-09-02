package com.example.blurtle.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="puzzle_results")
@IdClass(PuzzleResultId.class)
public class PuzzleResults {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "puzzle_id")
    private Long puzzleId;

    private Long numGuesses;

    private Boolean puzzleSolved;

    public PuzzleResults() {
    }

    public PuzzleResults(Long userId, Long puzzleId, Long numGuesses, Boolean puzzleSolved) {
        this.userId = userId;
        this.puzzleId = puzzleId;
        this.numGuesses = numGuesses;
        this.puzzleSolved = puzzleSolved;
    }

    @Override
    public String toString() {
        return "PuzzleResults{" +
                "userId=" + userId +
                ", puzzleId=" + puzzleId +
                ", numGuesses=" + numGuesses +
                ", puzzleSolved=" + puzzleSolved +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Long puzzleId) {
        this.puzzleId = puzzleId;
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
}
