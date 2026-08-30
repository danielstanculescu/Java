package demo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class UiApplication {

  @GetMapping(value = "/{path:[^\\.]*}")
  public String redirect() {
    return "forward:/";
  }

  @RequestMapping("/user")
  @ResponseBody
  public Principal user(Principal user) {
    return user;
  }

  public static void main(String[] args) {
    SpringApplication.run(UiApplication.class, args);
  }


  @RequestMapping("/resource")
  public Map<String, Object> home() {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }

  @Configuration
  protected static class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
        .formLogin(form -> form.loginPage("/login").successForwardUrl("/user").permitAll())
        .logout(Customizer.withDefaults())
        .authorizeHttpRequests(authorize -> authorize
          .requestMatchers("/index.html", "/", "/home", "/login", "/*.js", "/*.css", "/*.ico").permitAll()
          .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
          .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
        )
        .addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class);
      return http.build();
    }
  }

  static final class CsrfCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
      CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
      if (csrfToken != null) {
        // Render the token value to a cookie by causing the deferred token to be loaded
        csrfToken.getToken();
      }
      filterChain.doFilter(request, response);
    }
  }

}
