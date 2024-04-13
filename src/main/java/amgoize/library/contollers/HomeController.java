package amgoize.library.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping()
    public String home() {
        System.out.println("Serving home page...");
        return "home/homepage";
    }
}
