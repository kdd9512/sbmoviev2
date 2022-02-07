package com.sbmoviev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Sbmoviev2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sbmoviev2Application.class, args);
    }

}
