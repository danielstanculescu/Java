package demo.clientrestserver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
public class PortalController {

    private final RestClient restClient = RestClient.builder().build();
    @GetMapping("/")
    public String indexPage(@RegisteredOAuth2AuthorizedClient("web-portal-client") OAuth2AuthorizedClient authorizedClient) {
        String token = authorizedClient.getAccessToken().getTokenValue();

        // Connects to port 8090, fixing the ClientAuthorizationRequiredException routing error
        return restClient.get()
                .uri("http://127.0.0.1:8090/api/data")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(String.class);
    }
    /*

    @GetMapping("/")
    public Map<String, Object> indexPage(
            @RegisteredOAuth2AuthorizedClient("web-portal-client") OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser) {

        return Map.of(
                "status", "OAuth Handshake Complete! Victory!",
                "loggedInUser", oidcUser.getFullName() != null ? oidcUser.getFullName() : oidcUser.getSubject(),
                "rawAccessToken", authorizedClient.getAccessToken().getTokenValue().substring(0, 15) + "...",
                "scopes", authorizedClient.getAccessToken().getScopes()
        );
    }

     */
}

@Configuration
class ClientSecurityConfig {
    @Bean
    public SecurityFilterChain clientSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error", "/error/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(Customizer.withDefaults());
                //.exceptionHandling(exceptions -> exceptions
                  //      .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/oauth2/authorization/web-portal-client"))
                //);

        return http.build();
    }
}