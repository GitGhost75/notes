package de.kiemle.notes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        log.info("calling hello()");
        return "Hello World!";
    }
}
