package com.formacion.bosonit.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ComponenteCommandLineRunner implements CommandLineRunner {

    @Value("${spring.profiles.active:}")
    String profileActive;

    @Value("${bd.url:}")
    String url;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Profile set on: " + profileActive);
        System.out.println("Database url set on: " + url);

    }
}
