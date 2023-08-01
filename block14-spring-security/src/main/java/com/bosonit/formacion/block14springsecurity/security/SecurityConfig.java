package com.bosonit.formacion.block14springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers().permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET).hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.POST).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE).hasRole(ROLE_ADMIN)
                .anyRequest().authenticated();

        return http.build();
    }

}
