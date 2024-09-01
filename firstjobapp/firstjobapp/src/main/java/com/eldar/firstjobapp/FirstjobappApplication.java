package com.eldar.firstjobapp;

import com.eldar.firstjobapp.job.JobController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = JobController.class)
public class FirstjobappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstjobappApplication.class, args);
	}

}
