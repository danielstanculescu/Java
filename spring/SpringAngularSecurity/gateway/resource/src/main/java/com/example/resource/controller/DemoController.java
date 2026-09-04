package com.example.resource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // GET /demo/hello
    @GetMapping("/hello")
    @CrossOrigin(
            origins = "http://localhost:8081",
            allowedHeaders = {
                    //"X-abc",
                    "X-Requested-With",
                    "X-Auth-Token"
            }
    )
    public Map<String, String> sayHello(Principal user){
        System.out.println(user.getName());
        return Map.of("text", "Hello World from Resource Server");
    }
}
