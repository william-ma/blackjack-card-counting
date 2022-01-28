package blackjack.cardcounting.models;

public record Challenge(long id, int difficulty, String values) {

    public static String generateValues(int difficulty) {
        return "2ttqk";
    }

}
