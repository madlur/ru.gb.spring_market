package controller;

@RequestMapping("/")
@Controller
public class WelcomeController {

    @GetMapping
    public String welcomePage() {
        return "user";
    }
}