package com.example.blurtle.Controller;

import com.example.blurtle.Model.GuessScore;
import com.example.blurtle.Model.GuessWord;
import com.example.blurtle.Service.DictionaryService;
import com.example.blurtle.Service.ScrambleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Blurtle")
@CrossOrigin(origins = "http://localhost:5173")
public class BlurtleMainController {

    private static final Logger logger = LoggerFactory.getLogger(BlurtleMainController.class);

    private String randomWord = "";

    private String scrambledWord = "";

    private final DictionaryService dictionaryService;

    private final ScrambleService scrambleService;

    public BlurtleMainController(DictionaryService dictionaryService, ScrambleService scrambleService) {
        this.dictionaryService = dictionaryService;
        this.scrambleService = scrambleService;
    }

//    RandomWordAPI randomWordAPI;

    @GetMapping("/randomWord")
    public String getRandomWord() {
        randomWord = dictionaryService.getDailyRandomWord();
        return randomWord;
    }

    @GetMapping("/wordSize")
    public Integer wordSize() {
        if(randomWord.length() == 0){
            randomWord = dictionaryService.getDailyRandomWord();
        }
        return randomWord.length();
    }

    @GetMapping("/getNewWord")
    public Map<String, String> getWord(){
        logger.info("Method \'getNewWord\' has been invoked");
        return Map.of("word", getRandomWord(), "size", wordSize().toString());
    }

    @GetMapping("/getScrambledWord")
    public Map<String, String> getScrambledWord(){
        if(randomWord.length() == 0){
            randomWord = dictionaryService.getDailyRandomWord();
        }
        if(scrambledWord.length() == 0){
            scrambledWord = scrambleService.scramble(randomWord);
        }
        return Map.of("word", randomWord, "scrambledWord", scrambledWord, "size", wordSize().toString());
    }

    @PostMapping("/postGuessWord")
    public GuessScore postGuessWord(@RequestBody GuessWord guessWord){
        logger.info("Method \'postGuessWord\' has been invoked with a guess of: " + guessWord.getGuessWord());
        return dictionaryService.checkGuess(guessWord.getGuessWord());
    }
}
