package blackjack.cardcounting.controllers;

import blackjack.cardcounting.models.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayController {

    //https://www.baeldung.com/spring-autowire
    @Autowired
    private ChallengeController challengeController;

    @GetMapping("/play")
    public String play(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty,
                       Model model) {
        return showCard(0, 0, model);
    }

    @GetMapping("/play/{id}")
    public String playChallenge(@PathVariable long id, Model model) {
        return showCard(id, 0, model);
    }

    @GetMapping("/play/{id}/{index}")
    public String showCard(@PathVariable long id, @PathVariable int index, Model model) {
//        model.addAttribute("challenge", dummy);
        model.addAttribute("index", index);
        return "play";
    }
}
