package com.example.blurtle.Service;

import com.example.blurtle.Model.GuessScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DictionaryService {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    private final String zoneID = "GMT";
    private final List<String> promptDictionary;
    private final Set<String> guessDictionary;
    private String dailyWord;

    public DictionaryService() throws IOException {

        try (
                InputStream promptStream = getClass().getClassLoader().getResourceAsStream("scowl35.txt");
                InputStream guessStream = getClass().getClassLoader().getResourceAsStream("scowl70.txt");
        ) {
            if (promptStream == null || guessStream == null) {
                throw new IOException("Dictionary resource not found");
            }

            promptDictionary = new ArrayList<String>(new BufferedReader(new InputStreamReader(promptStream))
                    .lines()
                    .toList()
            );

            guessDictionary = new HashSet<>(
                    new BufferedReader(new InputStreamReader(guessStream))
                            .lines()
                            .toList()
            );
        }

    }

    public String getDailyRandomWord() {

        //Create a random object seeded with today's date according to GMT.
        //Use the first int of the random int stream to specify the index of the word used from the prompt dictionary
        Random random = new Random(LocalDate.now(ZoneId.of(zoneID)).toEpochDay());
//        Random random = new Random();
        dailyWord = promptDictionary.get(random.nextInt(promptDictionary.size()));

        return dailyWord;

    }

    /**
     * Method Name: checkGuess
     * Parameter Input: String guessWord
     * Output: GuessScore object, an object containing details on guess quality
     * Description: Evaluate correctness of guess.  If it is an exact match, set boolean for exactMatch to true.
     *      Calculate score.  Save guess and also size.
     */
    public GuessScore checkGuess(String guessWord){
        String word = dailyWord.toLowerCase();
        String guess = guessWord.toLowerCase();
        GuessScore guessScore = new GuessScore();
        guessScore.setGuessWord(guess);
        if(!guessDictionary.contains(guess)){
            return guessScore;
        }
        guessScore.setValidWord(true);
        if(guess.length() > word.length()) {
            return guessScore;
        }

        //Validate that the guess is a scramble of the word
        int[] alphabetArray = new int[26];
        for (int i = 0; i < word.length(); i++) {
            alphabetArray[word.charAt(i) - 'a']++;
        }
        for (int i = 0; i < guess.length(); i++) {
            alphabetArray[guess.charAt(i) - 'a']--;
        }
        guessScore.setValidGuess(true);
        guessScore.setExactMatch(true);
        for(int i = 0; i < alphabetArray.length; i++) {
            if(alphabetArray[i] != 0) {
                guessScore.setExactMatch(false);
            }
            if (alphabetArray[i] < 0) {
                guessScore.setValidGuess(false);
                break;
            }
        }

        guessScore.setScore(guess.length());
        guessScore.setLength(guess.length());

        logger.debug("Guess word: " + guessWord + " Score: " + guessScore.getScore() + " isExactMatch: " + guessScore.isExactMatch());
        return guessScore;
    }
}
