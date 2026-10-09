package com.example.blurtle.RepositoryTest;

import com.example.blurtle.Entity.Puzzle;
import com.example.blurtle.Repository.PuzzleRepo;
import com.example.blurtle.Service.ScrambleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ScrambleService.class)
public class PuzzleRepoTest {

    @Autowired
    private PuzzleRepo puzzleRepo;


    private Puzzle testPuzzle;

    @BeforeEach
    public void setup() {
        String puzzleWord = "BLURTLE";
        String scrambleWord = "TRUBLLE";
        testPuzzle = new Puzzle(LocalDate.now(ZoneId.of("America/Chicago")), puzzleWord, scrambleWord);
        puzzleRepo.save(testPuzzle);
    }

    @AfterEach
    public void teardown() {
        puzzleRepo.deleteAll();
    }

    @Test
    public void findById() {
        Puzzle puzzle = puzzleRepo.findById(testPuzzle.getPuzzleId()).orElse(null);

        Assertions.assertNotNull(puzzle);

        Assertions.assertEquals(testPuzzle.getPuzzleDate(), puzzle.getPuzzleDate());
        Assertions.assertEquals(testPuzzle.getPuzzleWord(), puzzle.getPuzzleWord());
        Assertions.assertEquals(testPuzzle.getScrambledWord(), puzzle.getScrambledWord());

    }
}
