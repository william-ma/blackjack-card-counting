package blackjack.cardcounting.strategies;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class HiOptII extends Strategy {

    public HiOptII() {
        map = Map.ofEntries(
                new AbstractMap.SimpleEntry<>('2', 1.0),
                new AbstractMap.SimpleEntry<>('3', 1.0),
                new AbstractMap.SimpleEntry<>('4', 2.0),
                new AbstractMap.SimpleEntry<>('5', 2.0),
                new AbstractMap.SimpleEntry<>('6', 1.0),
                new AbstractMap.SimpleEntry<>('7', 1.0),
                new AbstractMap.SimpleEntry<>('8', 0.0),
                new AbstractMap.SimpleEntry<>('9', 0.0),
                new AbstractMap.SimpleEntry<>('t', -2.0),
                new AbstractMap.SimpleEntry<>('j', -2.0),
                new AbstractMap.SimpleEntry<>('q', -2.0),
                new AbstractMap.SimpleEntry<>('k', -2.0),
                new AbstractMap.SimpleEntry<>('a', 0.0)
        );
    }

}
