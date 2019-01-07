package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    private final String message;

    public WelcomeController(
            @Value("${WELCOME_MESSAGE:HELLO}") String message
    ) {
        this.message = message;
    }

    @GetMapping("/")
    public String sayHello() {
        return message;
    }

    @GetMapping("/welcome")
    public String sayHello(String msg) {
        if (msg != null) {
            return msg;
        }
        return this.message;
    }
}

