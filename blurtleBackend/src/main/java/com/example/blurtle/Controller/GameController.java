package com.example.blurtle.Controller;

import com.example.blurtle.Model.GuessScore;
import com.example.blurtle.Model.GuessWord;
import com.example.blurtle.Service.DictionaryService;
import com.example.blurtle.Service.GameService;
import com.example.blurtle.Service.ScrambleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

@RestController
@RequestMapping("/Blurtle")
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    private String randomWord = "";

    private String scrambledWord = "";

    private final String timeZone = "America/Chicago";

    private LocalDate currentDate = LocalDate.now(ZoneId.of(timeZone));

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/rescramble")
    public ResponseEntity<String> rescramble(){
        scrambledWord = gameService.rescramble(randomWord);
        return new  ResponseEntity<>(scrambledWord, HttpStatus.OK);
    }

    //TODO: change usage in Blurtle Front-End
    @PostMapping("/checkGuessWord")
    public GuessScore postGuessWord(@RequestBody GuessWord guessWord){
        logger.info("Method \'checkGuessWord\' has been invoked with a guess of: " + guessWord.getGuessWord());
        return gameService.checkGuess(guessWord.getGuessWord().toLowerCase());
    }
}
