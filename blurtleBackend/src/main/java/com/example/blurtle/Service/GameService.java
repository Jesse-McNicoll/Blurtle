package com.example.blurtle.Service;

import com.example.blurtle.Model.GuessScore;
import com.example.blurtle.Model.GuessStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    Logger logger = LoggerFactory.getLogger(GameService.class);

    private final PuzzleService puzzleService;
    private final DictionaryService dictionaryService;
    private final ScrambleService scrambleService;

    GameService(PuzzleService puzzleService, DictionaryService dictionaryService, ScrambleService scrambleService) {
        this.puzzleService = puzzleService;
        this.dictionaryService = dictionaryService;
        this.scrambleService = scrambleService;
    }

    /**
     * Method Name: checkGuess
     * Parameter Input: String guessWord
     * Output: GuessScore object, an object containing details on guess quality
     * Description: Evaluate correctness of guess.  If it is an exact match, set boolean for exactMatch to true.
     *      Calculate score.  Save guess and also size.
     */
    //TODO: Continue implementing this out, correctly handing off responsibilities to the appropriate service.
    public GuessScore checkGuess(String guess){
        String solution = puzzleService.getDailyPuzzle().getPuzzleWord();
        GuessScore guessScore = new GuessScore();
        guessScore.setGuessWord(guess);
        if(!dictionaryService.isValidWord(guess)){
            guessScore.setGuessStatus(GuessStatus.INVALID_WORD);
            logger.info("Guess word: " + guess + " Status: " + guessScore.getGuessStatus());
            return guessScore;
        }
        //Validate that the guess is a scramble of the word
        GuessStatus status = GuessStatus.EXACT_MATCH;
        int[] alphabetArray = new int[26];
        for (int i = 0; i < solution.length(); i++) {
            alphabetArray[solution.charAt(i) - 'a']++;
        }
        for (int i = 0; i < guess.length(); i++) {
            alphabetArray[guess.charAt(i) - 'a']--;
        }
        for(int i = 0; i < alphabetArray.length; i++) {
            if(alphabetArray[i] > 0) {
                status = GuessStatus.VALID_GUESS;
            }
            if (alphabetArray[i] < 0) {
                status = GuessStatus.VALID_WORD;
                break;
            }
        }
        guessScore.setGuessStatus(status);
        logger.info("Guess word: " + guess + " Status: " + guessScore.getGuessStatus());
        return guessScore;
    }

    public String rescramble(String word){
        return scrambleService.rescramble(word);
    }
}
