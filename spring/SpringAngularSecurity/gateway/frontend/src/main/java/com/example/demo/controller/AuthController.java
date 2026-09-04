package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/user")
    public Principal me(Principal user){
        return user;
    }

    /* not using anymore in Gateway server
    @GetMapping("/token")
    public Map<String, String> getSessionIdToken(HttpSession session){
        System.out.println("session.getId() " + session.getId());
        return Collections.singletonMap("token", session.getId());
    }

     */
}
