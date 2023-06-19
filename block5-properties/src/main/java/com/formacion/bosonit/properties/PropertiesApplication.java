package com.formacion.bosonit.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertiesApplication.class, args);
	}
}
