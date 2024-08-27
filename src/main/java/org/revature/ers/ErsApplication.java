package org.revature.ers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// Disable Security autoconfiguration to maintain use of session based login and gain access to Password Encoder Class.
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ErsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErsApplication.class, args);
    }
}
