package demo.resourceserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PortalResourceController {

    @GetMapping("/api/data")
    public Map<String, Object> secureDataExchange(@AuthenticationPrincipal Jwt jwt) {
        // 🛠️ CRITICAL FIX: Use HashMap instead of Map.of() because Map.of() crashes on null values!
        Map<String, Object> responseData = new HashMap<String, Object>();

        responseData.put("status", "Access Granted!");
        responseData.put("message", "Hello from Resource Server (Port 8090)");

        // Safely extract attributes with simple fallbacks if they are null
        responseData.put("subject", jwt.getSubject() != null ? jwt.getSubject() : "unknown-user");
        responseData.put("clientId", jwt.getClaimAsString("client_id") != null ? jwt.getClaimAsString("client_id") : "web-portal-client");

        return responseData;
    }
}

@Configuration
class ResourceConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}