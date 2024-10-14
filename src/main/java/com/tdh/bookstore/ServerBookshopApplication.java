package com.tdh.bookstore;

import com.tdh.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Swagger configuration is set up in SwaggerConfig class
@SpringBootApplication
public class ServerBookshopApplication implements CommandLineRunner  {
	@Autowired
	private AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(ServerBookshopApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		authService.createDefaultAdmin();
	}


}
