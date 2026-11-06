package com.example.blurtle.Controller;

import com.example.blurtle.Entity.Puzzle;
import com.example.blurtle.ResponseDTO.PuzzleResponseDTO;
import com.example.blurtle.Service.PuzzleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PuzzleResponseDTO> getPuzzleWord(){
        Puzzle puzzle = puzzleService.getDailyPuzzle();
        return new ResponseEntity<>(new PuzzleResponseDTO(puzzle.getScrambledWord()), HttpStatus.OK);
    }

}
