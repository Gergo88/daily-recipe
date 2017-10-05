package com.gergely.jonas.dailyrecipe.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages = "com.gergely.jonas.dailyrecipe")
@EntityScan(basePackages = "com.gergely.jonas.dailyrecipe")
@ComponentScan(basePackages = "com.gergely.jonas.dailyrecipe")
@SpringBootApplication
public class DailyRecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyRecipeApplication.class, args);
	}
}
