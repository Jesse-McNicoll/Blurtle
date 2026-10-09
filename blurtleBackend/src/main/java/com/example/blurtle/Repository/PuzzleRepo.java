package com.example.blurtle.Repository;

import com.example.blurtle.Entity.Puzzle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PuzzleRepo extends JpaRepository<Puzzle, Long> {
}
