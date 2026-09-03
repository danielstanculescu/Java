package com.example.redisdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisIndexedHttpSession
// Hooks session management context into your Redis container
public class SessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();

        serializer.setCookieName("SESSION");       // Standard Spring Session token name
        serializer.setCookiePath("/");             // Visible across full application routing scope
        serializer.setUseSecureCookie(true);       // Forces HTTPS transmission exclusively
        serializer.setUseHttpOnlyCookie(true);     // Hard block against XSS script access
        serializer.setSameSite("Lax");             // Protection framework against CSRF exploits

        return serializer;
    }
}