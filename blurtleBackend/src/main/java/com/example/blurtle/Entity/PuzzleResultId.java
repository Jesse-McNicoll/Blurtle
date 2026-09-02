package com.example.blurtle.Entity;

public class PuzzleResultId {

    private Long puzzleId;
    private Long userId;

    //default constructor
    public PuzzleResultId(){}

    public PuzzleResultId(Long puzzleId, Long userId) {
        this.puzzleId = puzzleId;
        this.userId = userId;
    }
}
