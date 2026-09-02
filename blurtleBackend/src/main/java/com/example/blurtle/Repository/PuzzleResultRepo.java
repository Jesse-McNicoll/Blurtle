package com.example.blurtle.Repository;

import com.example.blurtle.Entity.PuzzleResults;
import org.springframework.data.repository.CrudRepository;

public interface PuzzleResultRepo extends CrudRepository<PuzzleResults, Long> {
}
