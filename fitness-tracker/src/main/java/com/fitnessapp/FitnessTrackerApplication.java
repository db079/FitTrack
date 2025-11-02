package com.fitnessapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.fitnessapp.model")
@EnableJpaRepositories(basePackages = "com.fitnessapp.repository")
public class FitnessTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FitnessTrackerApplication.class, args);
    }
}
