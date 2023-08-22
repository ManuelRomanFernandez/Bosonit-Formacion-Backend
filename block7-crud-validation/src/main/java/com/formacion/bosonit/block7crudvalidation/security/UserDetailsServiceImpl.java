package com.formacion.bosonit.block7crudvalidation.security;

import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonaRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = repository
                .findAll()
                .stream()
                .filter(person -> person.getUsuario().equals(username))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No existe usuario: " + username));

        Collection<? extends GrantedAuthority> authorities = persona.isAdmin()
                ? new HashSet<>(Set.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
                : new HashSet<>(Set.of(new SimpleGrantedAuthority("ROLE_USER")));

        return new User(
                persona.getUsuario(),
                persona.getPassword(),
                persona.getActive(),
                true,
                true,
                true,
                authorities);
    }
}
