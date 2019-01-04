package com.petrego.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.petrego"})
public class PetregoApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(PetregoApplicationTest.class, args);
    }
}