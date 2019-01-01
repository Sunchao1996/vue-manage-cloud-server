package com.sc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.sc")
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
