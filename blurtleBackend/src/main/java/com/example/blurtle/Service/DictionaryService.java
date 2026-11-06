package com.example.blurtle.Service;

import com.example.blurtle.Model.GuessScore;
import com.example.blurtle.Model.GuessStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DictionaryService {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    private final String timeZone = "America/Chicago";
    private final List<String> weekdayDictionary;
    private final List<String> weekendDictionary;
    private final Set<String> guessDictionary;
    private String dailyWord;
    private final Set<DayOfWeek> weekdays = new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY));
    private final Set<DayOfWeek> weekendDays = new HashSet<>(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));


    public DictionaryService() throws IOException {

        try (
                InputStream weekdayStream = getClass().getClassLoader().getResourceAsStream("weekdayWords.txt");
                InputStream weekendStream = getClass().getClassLoader().getResourceAsStream("weekendWords.txt");
                InputStream guessStream = getClass().getClassLoader().getResourceAsStream("scowl70.txt");
        ) {
            if (weekdayStream== null || weekendStream== null || guessStream == null) {
                throw new IOException("Dictionary resource(s) not found");
            }

            weekdayDictionary = new ArrayList<String>(new BufferedReader(new InputStreamReader(weekdayStream))
                    .lines()
                    .toList()
            );

            weekendDictionary = new ArrayList<String>(new BufferedReader(new InputStreamReader(weekendStream))
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
        LocalDate currentDate = LocalDate.now(ZoneId.of(timeZone));
        Random random = new Random(currentDate.toEpochDay());
        DayOfWeek day = currentDate.getDayOfWeek();
        if(weekdays.contains(day)) {
            dailyWord = weekdayDictionary.get(random.nextInt(weekdayDictionary.size()));
        }
        else{
            dailyWord = weekdayDictionary.get(random.nextInt(weekdayDictionary.size()));
        }
        return dailyWord;
    }

    public boolean isValidWord(String guess){
        return guessDictionary.contains(guess);
    }

}
