package demo;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

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

  @GetMapping("/token")
  @ResponseBody
  public Map<String, String> token(HttpSession session) {
    return Collections.singletonMap("token", session.getId());
  }


  @Bean
  RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                          MessageListenerAdapter listenerAdapter) {

    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

    return container;
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(UiApplication.class);

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  Receiver receiver() {
    return new Receiver();
  }

  @Bean
  StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    return new StringRedisTemplate(connectionFactory);
  }


  public static void main(String[] args) throws InterruptedException {
    //SpringApplication.run(UiApplication.class, args);

    ApplicationContext ctx = SpringApplication.run(UiApplication.class, args);

    StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
    Receiver receiver = ctx.getBean(Receiver.class);

    while (receiver.getCount() == 0) {

      LOGGER.info("Sending message...");
      template.convertAndSend("chat", "Hello from Redis!");
      Thread.sleep(500L);
    }

    System.exit(0);
  }



  @Configuration
  protected static class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
        .httpBasic(basic -> basic
          .securityContextRepository(new HttpSessionSecurityContextRepository())
        )
        .logout(Customizer.withDefaults())
        .authorizeHttpRequests(authorize -> authorize
          .requestMatchers("/index.html", "/", "/home", "/login").permitAll()
          .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
      return http.build();
    }
  }
}

