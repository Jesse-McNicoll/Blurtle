package com.example.blurtle.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ScrambleService {

    public String scramble(String word) {

        List<Character> chars = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        Random random = new Random(word.hashCode());
        Collections.shuffle(chars, random);

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }

        return sb.toString();
    }

    public String rescramble(String word) {
        List<Character> chars = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        Collections.shuffle(chars, new Random());

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }

        return sb.toString();
    }
}
