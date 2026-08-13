package com.examplegroup.demoartifact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Hi from Spring, code made with IntelliJ. Great!!";
    }

}
