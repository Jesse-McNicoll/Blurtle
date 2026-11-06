package com.example.blurtle.Service;

import com.example.blurtle.Entity.Puzzle;
import com.example.blurtle.Repository.PuzzleRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class PuzzleService {

    private final String timeZone = "America/Chicago";

    public final PuzzleRepo puzzleRepo;
    private final DictionaryService dictionaryService;
    private final ScrambleService scrambleService;

    public PuzzleService(PuzzleRepo puzzleRepo, DictionaryService dictionaryService, ScrambleService scrambleService) {
        this.puzzleRepo = puzzleRepo;
        this.dictionaryService = dictionaryService;
        this.scrambleService = scrambleService;
    }

    public Puzzle findByPuzzleDate(LocalDate puzzleDate){

        return puzzleRepo.findByPuzzleDate(puzzleDate).orElseGet(() -> {
                String puzzleWord = dictionaryService.getDailyRandomWord();
                String scrambledWord = scrambleService.scramble(puzzleWord);

                Puzzle puzzle = new Puzzle(puzzleDate,
                        puzzleWord, scrambleService.scramble(puzzleWord));

                return puzzleRepo.save(puzzle);
        });
    }

    public Puzzle getDailyPuzzle(){
        LocalDate today = LocalDate.now(ZoneId.of(timeZone));
        return findByPuzzleDate(today);
    }
}
