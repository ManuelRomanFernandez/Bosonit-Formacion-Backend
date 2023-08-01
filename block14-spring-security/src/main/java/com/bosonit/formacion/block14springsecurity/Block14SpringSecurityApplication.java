package com.bosonit.formacion.block14springsecurity;

import com.bosonit.formacion.block14springsecurity.domain.Persona;
import com.bosonit.formacion.block14springsecurity.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.parameters.P;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
public class Block14SpringSecurityApplication {

	@Autowired
	PersonaRepository personaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Block14SpringSecurityApplication.class, args);
	}

	@PostConstruct
	public void loadPersonas() {
		Persona admin = new Persona();
		admin.setUsuario("Admin");
		admin.setPassword("Admin");
		admin.setName("Manuel");
		admin.setSurname("Roman");
		admin.setCompany_email("admin@email.com");
		admin.setPersonal_email("admin@email.com");
		admin.setCity("Malaga");
		admin.setActive(true);
		admin.setCreated_date(new Date());
		admin.setAdmin(true);

		personaRepository.save(admin);

		Persona usuario = new Persona();
		usuario.setUsuario("User");
		usuario.setPassword("User");
		usuario.setName("Maria");
		usuario.setSurname("Casado");
		usuario.setCompany_email("maria@email.com");
		usuario.setPersonal_email("maria@email.com");
		usuario.setCity("Sevilla");
		usuario.setActive(true);
		usuario.setCreated_date(new Date());
		usuario.setAdmin(false);

		personaRepository.save(usuario);
	}

}
