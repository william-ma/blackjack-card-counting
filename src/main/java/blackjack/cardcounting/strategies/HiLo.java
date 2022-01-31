package blackjack.cardcounting.strategies;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class HiLo extends Strategy {

    public HiLo() {
        map = Map.ofEntries(
                new AbstractMap.SimpleEntry<>('2', 1.0),
                new AbstractMap.SimpleEntry<>('3', 1.0),
                new AbstractMap.SimpleEntry<>('4', 1.0),
                new AbstractMap.SimpleEntry<>('5', 1.0),
                new AbstractMap.SimpleEntry<>('6', 1.0),
                new AbstractMap.SimpleEntry<>('7', 0.0),
                new AbstractMap.SimpleEntry<>('8', 0.0),
                new AbstractMap.SimpleEntry<>('9', 0.0),
                new AbstractMap.SimpleEntry<>('t', -1.0),
                new AbstractMap.SimpleEntry<>('j', -1.0),
                new AbstractMap.SimpleEntry<>('q', -1.0),
                new AbstractMap.SimpleEntry<>('k', -1.0),
                new AbstractMap.SimpleEntry<>('a', -1.0)
        );
    }

}
