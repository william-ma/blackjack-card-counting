package blackjack.cardcounting.controllers;

import blackjack.cardcounting.models.Challenge;
import blackjack.cardcounting.models.Difficulty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/challenges")
public class ChallengeController {

    // TODO: initlalize with latest challenge?
    // https://www.roboleary.net/java/2020/06/03/spring-boot-api.html#create-a-class-to-start-the-application
    private AtomicLong counter = new AtomicLong();

    @PostMapping("/submit/{id}")
    public String submitCount(@PathVariable long id,
                              @RequestParam(value = "running_count") int runningCount,
                              @RequestParam(value = "true_count", required = false) Integer trueCount,
                              Model model) {
        // calculate the count
        // compare the counts
        // see if it matches...
        model.addAttribute("results", "CORRECT!");
        return "results";
    }

    @GetMapping("/new")
    @ResponseBody
    public Challenge newChallenge(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty) {
        return new Challenge(counter.incrementAndGet(), difficulty, Challenge.generateValues(difficulty));
    }

    @GetMapping
    @ResponseBody
    public List<Challenge> getChallenges() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Challenge getChallenge(@PathVariable long id) {
        return new Challenge(0, 1, "22tqj");
    }

    @GetMapping("/play/{id}")
    public String playChallenge(@PathVariable long id, Model model) {
        return showCard(id, 0, model);
    }

    @GetMapping("/play/{id}/{index}")
    public String showCard(@PathVariable long id, @PathVariable int index, Model model) {
        model.addAttribute("challenge", getChallenge(id));
        model.addAttribute("index", index);
        return "play";
    }

}
