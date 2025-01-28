package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
@EntityScan({"com.entity"})
public class BibliotecaApplication {	
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
