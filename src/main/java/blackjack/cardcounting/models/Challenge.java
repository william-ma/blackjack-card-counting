package blackjack.cardcounting.models;

public record Challenge(long id, int difficulty, String values) {

    /**
     * Helper function to generate card values randomly.
     * @param difficulty
     * @return
     */
    public static String generateValues(int difficulty) {
        return "2ttqk";
    }

}
