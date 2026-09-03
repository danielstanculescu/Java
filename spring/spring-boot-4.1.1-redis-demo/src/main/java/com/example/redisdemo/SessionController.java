package com.example.redisdemo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class SessionController {

    private final CacheService cacheService;

    public SessionController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/set-session")
    public String setSessionCookie(@RequestParam String key, @RequestParam String sessionToken, HttpServletResponse response) {
        // Store payload in Redis database
        cacheService.put(key, sessionToken, 30);

        // Manually build and send a strict session cookie back to the client
        String cookieHeader = String.format("CUSTOM_JSESSIONID=%s; Path=/; Secure; HttpOnly; SameSite=Lax; Max-Age=1800", sessionToken);
        response.addHeader(HttpHeaders.SET_COOKIE, cookieHeader);

        return "Data cached in Redis. Session cookie sent via response header.";
    }

    @GetMapping("/get-session")
    public String getSessionData(@RequestParam String key) {
        String data = cacheService.get(key);
        return (data != null) ? "Session Active: " + data : "Session expired or invalid key.";
    }
}
