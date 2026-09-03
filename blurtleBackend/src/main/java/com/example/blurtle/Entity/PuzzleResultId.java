package com.example.blurtle.Entity;

import java.io.Serializable;

public class PuzzleResultId implements Serializable {

    private Long puzzle;
    private Long user;

    //default constructor
    public PuzzleResultId(){}

    public PuzzleResultId(Long puzzle, Long user) {
        this.puzzle = puzzle;
        this.user = user;
    }
}
