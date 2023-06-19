package com.formacion.bosonit.commandLinerRunner;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommandLinerRunnerApplication {

	/*
	 * El orden de los mensajes está determinado, en primer lugar, por la anotación @PostConstruct.
	 * Esta anotación especifica aquellos procesos que se van a ejecutar en el mismo momento que arranca nuestro programa
	 * dado por el ciclo de vida del mismo.
	 * Posteriomente, los otros dos métodos que están contenidos en @Bean apareceran según el orden en el que aparezcan
	 * en el código, debido a la lectura líneal del mismo.
	 */

	@PostConstruct
	void firstMessage(){
		System.out.println("Hola desde clase inicial");
	}

	@Bean
	CommandLineRunner secondMessage(){
		return e -> System.out.println("Hola desde clase secundaria");
	}


	/*
	 * Usando properties como parametros en la función
	 * @param param1
	 * @param param2
	 * @param param3
	 * @return
	 */

	/*
	@Bean
	CommandLineRunner thirdMessage(@Value("${param.first}") String param1,
								   @Value("${param.second}") String param2,
								   @Value("${param.third}") String param3)
	{
		return e -> {
			System.out.println("Soy la tercera clase");

			if(param1 != null || param2 != null || param3 != null)
				System.out.println("Argumentos del programa");

			if (param1 != null) {
				System.out.println("param1: " + param1);
			}

			if (param2 != null) {
				System.out.println("param2: " + param2);
			}

			if (param3 != null) {
				System.out.println("param3: " + param3);
			}
		};
	}
	*/

	/*
	 * Usando argumentos
	 * @return
	 */
	/*
	@Bean
	CommandLineRunner thirdMessage() {
		return args -> {
			System.out.println("Soy la tercera clase");

			if (args.length > 0) {
				System.out.println("Argumentos del programa");
				for (String arg : args) {
					System.out.println(arg);
				}
			}
		};
	}

	 */

	public static void main(String[] args) {
		SpringApplication.run(CommandLinerRunnerApplication.class, args);
	}
}
