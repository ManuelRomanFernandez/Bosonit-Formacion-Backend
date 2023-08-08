package com.formacion.bosonit.backendfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaServer
@SpringBootApplication
public class BackendFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendFrontApplication.class, args);
	}

}
