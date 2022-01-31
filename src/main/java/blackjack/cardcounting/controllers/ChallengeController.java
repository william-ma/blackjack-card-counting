package blackjack.cardcounting.controllers;

import blackjack.cardcounting.models.Challenge;
import blackjack.cardcounting.strategies.*;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private static final Map<Integer, Function<String, Double>> strategyMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1, s -> new HiLo().getRunningCount(s)),
            new AbstractMap.SimpleEntry<>(2, s -> new HiOptI().getRunningCount(s)),
            new AbstractMap.SimpleEntry<>(3, s -> new HiOptII().getRunningCount(s)),
            new AbstractMap.SimpleEntry<>(4, s -> new KO().getRunningCount(s)),
            new AbstractMap.SimpleEntry<>(5, s -> new OmegaII().getRunningCount(s)),
            new AbstractMap.SimpleEntry<>(6, s -> new Red7().getRunningCount(s)),
            new AbstractMap.SimpleEntry<>(7, s -> new Halves().getRunningCount(s)),
            new AbstractMap.SimpleEntry<>(8, s -> new ZenCount().getRunningCount(s))
    );

    // TODO: initlalize with latest challenge?
    // https://www.roboleary.net/java/2020/06/03/spring-boot-api.html#create-a-class-to-start-the-application
    private AtomicLong counter = new AtomicLong();

    // TODO: Test data only! Before we implement the database
    private final Challenge dummy = new Challenge(1, 1, "22tqj");

    @PostMapping(path="/submit/{id}", produces="application/json")
    public boolean submitChallenge(@PathVariable long id,
                                   @RequestParam(value = "strategy") int strategy,
                                   @RequestParam(value = "running_count") double runningCount,
                                   @RequestParam(value = "true_count", required = false) Double trueCount) {

        if (strategyMap.containsKey(strategy)) {
            // TODO: Get challenge
            double ourRunningCount = strategyMap.get(strategy).apply(dummy.values());
            return (Math.abs(ourRunningCount - runningCount) < 0.0001);
        } else {
            System.err.println("ERROR: Cannot find strategy");
            return false ;
        }
    }

    @GetMapping("/new")
    public Challenge newChallenge(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty) {
        return new Challenge(counter.incrementAndGet(), difficulty, Challenge.generateValues(difficulty));
    }

    @GetMapping
    public List<Challenge> getChallenges() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Challenge getChallenge(@PathVariable long id) {
        return new Challenge(0, 1, "22tqj");
    }

}
