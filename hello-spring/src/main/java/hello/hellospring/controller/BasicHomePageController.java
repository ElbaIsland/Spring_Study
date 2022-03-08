package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicHomePageController {
    @GetMapping("/")
    public String home() {
        return "basicHomePage";
    }
}
