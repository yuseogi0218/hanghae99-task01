package hanghae99.reboot.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class API {

    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }
}
