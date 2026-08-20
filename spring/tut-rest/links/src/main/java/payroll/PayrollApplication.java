package payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
The following tactics help make your services less likely to break existing clients you may or may not control:
    - Do not remove old fields. Instead, support them.
    - Use rel-based links so clients need not hard code URIs.
    - Retain old links as long as possible. Even if you have to change the URI, keep the rels so that older clients have a path to the newer features.
    - Use links, not payload data, to instruct clients when various state-driving operations are available.
 */
@SpringBootApplication
public class PayrollApplication {

    public static void main(String... args){
        SpringApplication.run(PayrollApplication.class, args);
    }
}
