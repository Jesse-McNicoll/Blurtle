package com.example.blurtle.Repository;

import com.example.blurtle.Entity.Puzzle;
import com.example.blurtle.Entity.PuzzleResult;
import com.example.blurtle.Entity.PuzzleResultId;
import com.example.blurtle.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PuzzleResultRepo extends JpaRepository<PuzzleResult, PuzzleResultId> {

    PuzzleResult findByUserAndPuzzle(User user, Puzzle puzzle);
}
