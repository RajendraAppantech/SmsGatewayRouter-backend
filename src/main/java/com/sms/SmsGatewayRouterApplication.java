package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class SmsGatewayRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsGatewayRouterApplication.class, args);
		System.out.println("Server Start...");
	}
}
