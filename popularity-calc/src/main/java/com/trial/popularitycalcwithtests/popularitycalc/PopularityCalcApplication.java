package com.trial.popularitycalcwithtests.popularitycalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PopularityCalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopularityCalcApplication.class, args);
	}

}
