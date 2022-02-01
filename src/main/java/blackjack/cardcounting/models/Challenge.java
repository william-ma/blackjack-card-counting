package blackjack.cardcounting.models;

import org.springframework.data.annotation.Id;

public record Challenge(@Id String id, int difficulty, String values) {

    /**
     * Helper function to generate card values randomly.
     * @param difficulty
     * @return
     */
    public static String generateValues(int difficulty) {
        return "2ttqk";
    }

}
