package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisIndexedHttpSession // Force-injects springSessionRepositoryFilter into the absolute front of the web container filter stack
public class RedisSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        // Force the name to explicitly be 'SESSION' instead of JSESSIONID
        serializer.setCookieName("SESSION");
        serializer.setCookiePath("/");

        // Ensure local HTTP debugging works (Browsers block Secure cookies on non-HTTPS connections)
        serializer.setUseSecureCookie(false);

        return serializer;
    }
}