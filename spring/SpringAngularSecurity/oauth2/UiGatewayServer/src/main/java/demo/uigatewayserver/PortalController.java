package demo.uigatewayserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class PortalController {

    @GetMapping("/")
    public String getHome() {
        return "Return Home";
    }

    // 3 independent points
    // /api
    // route -> http://127.0.0.1:8080/data/api (it redirects to this method on 8080)
    // route -> http://127.0.0.1:8080/data2 (it calls directly the method /api/data in 8090)
    private final WebClient webClient = WebClient.builder().build();
    @GetMapping("/api")
    public Mono<String> indexPage(@RegisteredOAuth2AuthorizedClient("web-portal-client") OAuth2AuthorizedClient authorizedClient) {
        String token = authorizedClient.getAccessToken().getTokenValue();

        // Connects to port 8090, fixing the ClientAuthorizationRequiredException routing error
        return webClient.get()
                .uri("http://127.0.0.1:8090/api/data")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class);
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
@EnableWebFluxSecurity
class ClientSecurityConfig {
    @Bean
    public SecurityWebFilterChain clientSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange(exchanges -> exchanges
                                .pathMatchers("/error", "/error/**").permitAll()
                                .anyExchange().authenticated()
                )
                //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/error", "/error/**").permitAll()
                        //.anyRequest().authenticated()

                .oauth2Login(Customizer.withDefaults());
        //.exceptionHandling(exceptions -> exceptions
        //      .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/oauth2/authorization/web-portal-client"))
        //);

        return http.build();
    }
}









