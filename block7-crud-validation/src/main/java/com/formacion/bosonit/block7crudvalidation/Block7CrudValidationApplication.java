package com.formacion.bosonit.block7crudvalidation;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@EnableFeignClients
@SpringBootApplication
public class Block7CrudValidationApplication {

	@Autowired
	PersonaRepository repository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidationApplication.class, args);
	}

	@PostConstruct
	public void loadPersonas() {
		Persona admin = new Persona();
		admin.setUsuario("Admin");
		admin.setPassword(passwordEncoder.encode("Admin"));
		admin.setName("Manuel");
		admin.setSurname("Roman");
		admin.setCompany_email("admin@email.com");
		admin.setPersonal_email("admin@email.com");
		admin.setCity("Malaga");
		admin.setActive(true);
		admin.setCreated_date(new Date());
		admin.setAdmin(true);

		repository.save(admin);

		Persona usuario = new Persona();
		usuario.setUsuario("User");
		usuario.setPassword(passwordEncoder.encode("User"));
		usuario.setName("Pablo");
		usuario.setSurname("Garcia");
		usuario.setCompany_email("pablo@email.com");
		usuario.setPersonal_email("pablo@email.com");
		usuario.setCity("Sevilla");
		usuario.setActive(true);
		usuario.setCreated_date(new Date());
		usuario.setAdmin(false);

		repository.save(usuario);
	}

}
