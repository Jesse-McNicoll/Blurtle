package com.example.blurtle.Controller;

import com.example.blurtle.Entity.Puzzle;
import com.example.blurtle.Service.PuzzleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

@RestController
@RequestMapping("/Blurtle/puzzle")
public class PuzzleController {

    private final PuzzleService puzzleService;

    public PuzzleController(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }

    @GetMapping("/getDailyPuzzle")
    public Map<String, String> getPuzzleWord(){
        Puzzle puzzle = puzzleService.getDailyPuzzle();
        return Map.of("scrambledWord", puzzle.getScrambledWord(),
                "size", String.valueOf(puzzle.getScrambledWord().length()));
    }

}
