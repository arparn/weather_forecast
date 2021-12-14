package iot.weather.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class Test {

    @GetMapping
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
