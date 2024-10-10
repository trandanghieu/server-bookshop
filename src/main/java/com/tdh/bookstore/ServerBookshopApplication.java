package com.tdh.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Swagger configuration is set up in SwaggerConfig class
@SpringBootApplication
public class ServerBookshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerBookshopApplication.class, args);
	}

}
