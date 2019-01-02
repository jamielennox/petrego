package com.petrego.petrego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class PetregoApplication extends SpringBootServletInitializer {
    public static void main(final String[] args) {
        SpringApplication.run(PetregoApplication.class, args);
    }
}
