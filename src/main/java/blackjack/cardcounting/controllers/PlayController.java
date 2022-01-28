package blackjack.cardcounting.controllers;

import blackjack.cardcounting.models.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayController {

    //https://www.baeldung.com/spring-autowire
    @Autowired
    private ChallengeController challengeController;

    @GetMapping("/play")
    public String play(@RequestParam(value = "difficulty", defaultValue = "1") int difficulty,
                       Model model) {
        return challengeController.showCard(0, 0, model);
    }
}
