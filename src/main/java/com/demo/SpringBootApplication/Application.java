package com.demo.SpringBootApplication;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
