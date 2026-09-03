package com.example.blurtle.Entity;

import java.io.Serializable;
import java.util.Objects;

public class PuzzleResultId implements Serializable {

    private Long puzzle;
    private Long user;

    //default constructor
    public PuzzleResultId(){}

    public PuzzleResultId(Long puzzle, Long user) {
        this.puzzle = puzzle;
        this.user = user;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        PuzzleResultId that = (PuzzleResultId) o;
        return (puzzle == that.puzzle &&
                user == that.user);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.puzzle, this.user);
    }
}
