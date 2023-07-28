package com.formacion.bosonit.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaServer
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	private static final String BACKEND_URI = "http://backend-service:8081";
	private static final String FRONT_URI = "http://front-service:8082";

	@Bean
	public RouteLocator passengerRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("passenger/{cliente_id}", r -> r.path("/passenger/{cliente_id}")
						.and().method(HttpMethod.GET)
						.uri(BACKEND_URI))
				.route("passenger", r -> r.path("/passenger")
						.and().method(HttpMethod.GET)
						.uri(BACKEND_URI))
				.route("passenger", r -> r.path("/passenger")
						.and().method(HttpMethod.POST)
						.uri(BACKEND_URI))
				.route("passenger/{cliente_id}", r -> r.path("/passenger/{cliente_id}")
						.and().method(HttpMethod.PUT)
						.uri(BACKEND_URI))
				.route("passenger/{cliente_id}", r -> r.path("/passenger/{cliente_id}")
						.and().method(HttpMethod.DELETE)
						.uri(BACKEND_URI))
				.build();
	}

	@Bean
	public RouteLocator tripRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("trip/{trip_id}", r -> r.path("/trip/{trip_id}")
						.and().method(HttpMethod.GET)
						.uri(BACKEND_URI))
				.route("trip", r -> r.path("/trip")
						.and().method(HttpMethod.GET)
						.uri(BACKEND_URI))
				.route("trip/count/{trip_id}", r -> r.path("/trip/count/{trip_id}")
						.and().method(HttpMethod.GET)
						.uri(BACKEND_URI))
				.route("trip/verify/{trip_id}", r -> r.path("/trip/verify/{trip_id}")
						.and().method(HttpMethod.GET)
						.uri(BACKEND_URI))
				.route("trip", r -> r.path("/trip")
						.and().method(HttpMethod.POST)
						.uri(BACKEND_URI))
				.route("trip/{trip_id}/{cliente_id}", r -> r.path("/trip/{trip_id}/{cliente_id}")
						.and().method(HttpMethod.POST)
						.uri(BACKEND_URI))
				.route("trip/{trip_id}", r -> r.path("/trip/{trip_id}")
						.and().method(HttpMethod.PUT)
						.uri(BACKEND_URI))
				.route("trip/{trip_id}/{status}", r -> r.path("/trip/{trip_id}/{status}")
						.and().method(HttpMethod.PUT)
						.uri(BACKEND_URI))
				.route("trip/{trip_id}", r -> r.path("/trip/{trip_id}")
						.and().method(HttpMethod.DELETE)
						.uri(BACKEND_URI))
				.build();
	}

	@Bean
	public RouteLocator ticketRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("generateTicket/{cliente_id}/{viaje_id}", r -> r.path("/generateTicket/{cliente_id}/{viaje_id}")
						.and().method(HttpMethod.POST)
						.uri(FRONT_URI))
				.build();
	}

}
