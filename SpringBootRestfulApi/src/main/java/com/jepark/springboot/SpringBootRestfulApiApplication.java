package com.jepark.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.jepark.springboot"})
public class SpringBootRestfulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfulApiApplication.class, args);
	}
}
