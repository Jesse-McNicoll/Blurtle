package com.example.blurtle.RepositoryTest;

import com.example.blurtle.Entity.Puzzle;
import com.example.blurtle.Entity.PuzzleResult;
import com.example.blurtle.Entity.User;
import com.example.blurtle.Repository.PuzzleRepo;
import com.example.blurtle.Repository.PuzzleResultRepo;
import com.example.blurtle.Repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.time.ZoneId;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PuzzleResultRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PuzzleRepo puzzleRepo;

    @Autowired
    private PuzzleResultRepo puzzleResultRepo;

    private PuzzleResult testPuzzleResult;

    @BeforeEach
    public void setup() {

        puzzleResultRepo.deleteAll();
        puzzleRepo.deleteAll();
        userRepo.deleteAll();

        User testUser = userRepo.saveAndFlush(
                new User("testFirstName", "testLastName", "testEmail@gmail.com")
        );

        Puzzle testPuzzle = puzzleRepo.saveAndFlush(
                new Puzzle(LocalDate.now(ZoneId.of("America/Chicago")), "crunchy", "chycrun")
        );

        testPuzzleResult = puzzleResultRepo.saveAndFlush(new PuzzleResult(testUser,
                testPuzzle,
                3,
                true));

    }

    @AfterEach
    public void tearDown() {
        puzzleResultRepo.deleteAll();
        puzzleRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    public void findByUserAndPuzzle(){
        PuzzleResult puzzleResult = puzzleResultRepo.findByUserAndPuzzle(testPuzzleResult.getUser(), testPuzzleResult.getPuzzle());
        Assertions.assertEquals(testPuzzleResult.getPuzzle().getPuzzleWord(), puzzleResult.getPuzzle().getPuzzleWord());
        Assertions.assertEquals(testPuzzleResult.getPuzzle().getScrambledWord(), puzzleResult.getPuzzle().getScrambledWord());
        Assertions.assertEquals(testPuzzleResult.getNumGuesses(), puzzleResult.getNumGuesses());
        Assertions.assertEquals(testPuzzleResult.getUser().getEmail(), puzzleResult.getUser().getEmail());
        Assertions.assertEquals(testPuzzleResult.getPuzzleSolved(), puzzleResult.getPuzzleSolved());
    }
}
