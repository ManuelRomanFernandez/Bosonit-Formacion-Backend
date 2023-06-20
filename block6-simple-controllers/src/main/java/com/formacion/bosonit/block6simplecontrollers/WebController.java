package com.formacion.bosonit.block6simplecontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @GetMapping("/user/{value}")
    public String user(@PathVariable String value) {
        return "Hola " + value;
    }

    @PostMapping("/useradd")
    public Person useradd(@RequestBody String json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Person jsonUser = mapper.readValue(json, Person.class);

            Person user = new Person(jsonUser.getName(), jsonUser.getCity(), jsonUser.getAge() + 1);

            return user;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
