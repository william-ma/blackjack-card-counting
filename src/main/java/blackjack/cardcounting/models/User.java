package blackjack.cardcounting.models;

public class User {

    private final long id;
    private final String values;

    public User() {
        this.id = 0;
        this.values = "";
    }

    public User(long id, String values) {
        this.id = id;
        this.values = values;
    }

//    /**
//     * Factory method to create new challenges
//     * @param difficulty difficulty of challenge ranging from 1-5
//     * @return
//     */
//    public static Challenge newChallenge(int difficulty) {
//        return new Challenge(1, difficulty, "2ttqk");
//    }
}
