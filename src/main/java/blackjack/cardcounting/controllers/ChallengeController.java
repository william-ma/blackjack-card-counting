package blackjack.cardcounting.controllers;

import blackjack.cardcounting.models.Challenge;
import blackjack.cardcounting.models.ChallengeRepository;
import blackjack.cardcounting.strategies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeRepository repository;

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

    @PostMapping(path="/submit/{id}", produces="application/json")
    public boolean submit(@PathVariable String id,
                          @RequestParam(value = "strategy") int strategy,
                          @RequestParam(value = "running_count") double runningCount,
                          @RequestParam(value = "true_count", required = false) Double trueCount) {

        if (strategyMap.containsKey(strategy)) {
            try {
                Challenge challenge = repository.findById(id).orElseThrow();
                double ourRunningCount = strategyMap.get(strategy).apply(challenge.values());
                return (Math.abs(ourRunningCount - runningCount) < 0.0001);
            } catch (NoSuchElementException e) {
                System.err.println("ERROR: Cannot find challenge id: " + id);
                return false;
            }
        } else {
            System.err.println("ERROR: Cannot find strategy");
            return false ;
        }
    }

    @PostMapping("/create")
    public Challenge create(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty) {
        String values = Challenge.generateValues(difficulty);
        return repository.save(new Challenge(values, difficulty, values));
    }

    @GetMapping
    public List<Challenge> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Challenge get(@PathVariable String id) {
        try {
            return repository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

}
