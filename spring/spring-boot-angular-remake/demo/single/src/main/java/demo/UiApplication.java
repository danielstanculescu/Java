package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class UiApplication {

  @GetMapping("/user")
  @ResponseBody
  public Principal user(Principal user) {
    return user;
  }

  @RequestMapping("/resource")
  public Map<String, Object> home() {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }

  @GetMapping(value = "/{path:[^\\.]*}")
  public String redirect() {
    return "forward:/";
  }

  public static void main(String[] args) {
    SpringApplication.run(UiApplication.class, args);
  }

  /*
    @GetMapping("/api/health-check")
    public String healthCheck(){
        return "ok";
    }
  */

  @Configuration
  protected static class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
          .httpBasic(basic -> basic
          .securityContextRepository(new HttpSessionSecurityContextRepository())
        )
        .authorizeHttpRequests(authorize -> authorize
          .requestMatchers("/index.html", "/", "/home", "/login", "/*.js", "/*.css", "/*.ico").permitAll()
          .anyRequest().authenticated()
        )


          //.csrf(AbstractHttpConfigurer::disable);

          // .csrf(csrf -> csrf
          //.csrfTokenRepository(new HttpSessionCsrfTokenRepository()));

          .csrf(csrf -> csrf
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));



      return http.build();
    }
  }

}
