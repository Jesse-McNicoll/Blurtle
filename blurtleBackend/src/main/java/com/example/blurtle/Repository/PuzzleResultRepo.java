package com.example.blurtle.Repository;

import com.example.blurtle.Entity.PuzzleResult;
import com.example.blurtle.Entity.PuzzleResultId;
import org.springframework.data.repository.CrudRepository;

public interface PuzzleResultRepo extends CrudRepository<PuzzleResult, PuzzleResultId> {
}
