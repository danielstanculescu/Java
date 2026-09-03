package com.example.redisdemo;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public Map<String, String> login(HttpSession session, @RequestParam String username) {
        // Interacting with the session forces Spring to mint a unique ID,
        // serialize the payload into Redis, and pass down the SESSION cookie.
        session.setAttribute("username", username);
        session.setAttribute("role", "USER");

        return Map.of(
                "message", "Login successful",
                "sessionId", session.getId()
        );
    }
}