package blackjack.cardcounting.controllers;

import blackjack.cardcounting.models.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/play")
public class PlayController {

    //https://www.baeldung.com/spring-autowire
    @Autowired
    private ChallengeController challengeController;

    @GetMapping
    public String play(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty,
                       Model model) {
        Challenge challenge = challengeController.create(difficulty);
        return showCard(challenge.id(), 0, model);
    }

    @GetMapping("/{id}/{index}")
    public String showCard(@PathVariable String id,
                           @PathVariable int index,
                           Model model) {
        Challenge challenge = challengeController.get(id);
        if (challenge != null) {
            boolean isInBounds = index < challenge.values().length();
            if (isInBounds) {
                Character card = challenge.values().charAt(index);
                System.out.println("Showing card: '" + card + "'");
                model.addAttribute("card", card);
            }

            model.addAttribute("challenge", challenge);
            model.addAttribute("index", index);
            model.addAttribute("isEnd", !isInBounds);
        }

        return "play";
    }

    @PostMapping("/submit/{id}")
    public String submit(@PathVariable String id,
                         @RequestParam(defaultValue = "1") int strategy,
                         @RequestParam(value = "running_count") double runningCount,
                         @RequestParam(value = "true_count", required = false) Double trueCount,
                         Model model) {
        boolean isCorrect = challengeController.submit(id, strategy, runningCount, trueCount);
        model.addAttribute("isCorrect", isCorrect);
//        model.addAttribute("isCorrect", isCorrect);
        return "results";
    }
}
