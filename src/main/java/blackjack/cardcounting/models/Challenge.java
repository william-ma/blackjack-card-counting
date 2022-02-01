package blackjack.cardcounting.models;

import blackjack.cardcounting.strategies.*;
import org.springframework.data.annotation.Id;

import java.util.*;
import java.util.function.Function;

public record Challenge(@Id String id, int difficulty, String values) {

    private static List<Character> cardValues = Arrays.asList('2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k', 'a');

    /**
     * Helper function to generate card values randomly.
     * @param difficulty
     * @return
     */
    public static String generateValues(int difficulty) {

        String values = "";

        switch (difficulty) {
            case 2 -> values = generateValuesHelper(8);
            case 3 -> values = generateValuesHelper(14);
            // default is easy
            default -> values = generateValuesHelper(5);
        }

        return values;
    }

    private static String generateValuesHelper(int length) {
        StringBuilder s = new StringBuilder();
        Random random = new Random();
        while (s.length() < length) {
            s.append(cardValues.get(random.nextInt(cardValues.size())));
        }
        return s.toString();
    }

}
