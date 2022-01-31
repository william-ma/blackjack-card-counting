package blackjack.cardcounting.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Strategy {

    protected Map<Character, Double> map = new HashMap<>();

    public double getRunningCount(String values) {

        double runningCount = 0;

        for (Character value : values.toCharArray()) {
            if (map.containsKey(value)) {
                runningCount += map.get(value);
            } else {
                System.err.println("Error: key not found '" + value + "'");
            }
        }

        return runningCount;
    }

    public double getTrueCount(int numDecks, String values) {
        return getRunningCount(values) / numDecks;
    }
}
