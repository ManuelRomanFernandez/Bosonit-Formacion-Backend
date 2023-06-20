package com.formacion.bosonit.block6pathvariableheaders.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formacion.bosonit.block6pathvariableheaders.model.Greeting;
import com.formacion.bosonit.block6pathvariableheaders.model.Pokemon;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MainController {
    @PostMapping("/pokemon")
    public Pokemon postPokemon(@RequestBody String json) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Pokemon jsonPokemon = mapper.readValue(json, Pokemon.class);

            return new Pokemon(
                    jsonPokemon.getName(),
                    jsonPokemon.getElementType(),
                    jsonPokemon.getPokedexNumber()
            );
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/user/{id}")
    public String getUserId(@PathVariable String id){
        return "User ID : " + id;
    }

    @PutMapping("/post")
    public HashMap putPost(@RequestParam HashMap<String,String> valor){
        return valor;
    }

    private Integer counter = 1;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter++, name);
    }
}
