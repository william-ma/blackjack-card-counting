package blackjack.cardcounting.controllers;

import blackjack.cardcounting.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User user(@RequestParam(value = "id") long id) {
        return new User();
    }

}
