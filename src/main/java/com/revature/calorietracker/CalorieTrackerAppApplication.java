package com.revature.calorietracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.revature.calorietracker")
@EntityScan("com.revature.calorietracker.models")
@EnableJpaRepositories("com.revature.calorietracker.repos")
public class CalorieTrackerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalorieTrackerAppApplication.class, args);
	}

}
