package com.formacion.bosonit.block18elasticSearch.controller;

import com.formacion.bosonit.block18elasticSearch.application.PersonService;
import com.formacion.bosonit.block18elasticSearch.domain.dto.PersonInputDto;
import com.formacion.bosonit.block18elasticSearch.domain.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * The {@code PersonController} class is a Spring MVC controller that handles HTTP requests related to person
 * entities. It exposes various endpoints for retrieving, creating, updating, and deleting persons.
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService service;

    /**
     * Retrieves a person by their unique identifier.
     *
     * @param id The unique identifier of the person.
     * @return A {@link PersonOutputDto} representing the person with the specified ID.
     */
    @GetMapping("/{id}")
    PersonOutputDto getPersonById(@PathVariable String id) {
        return service.getPersonById(id);
    }

    /**
     * Retrieves a paginated list of all persons.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an iterable of {@link PersonOutputDto} representing the persons
     *         on the specified page.
     */
    @GetMapping
    ResponseEntity<Iterable<PersonOutputDto>> getAllPersons(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getAllPersons(pageNumber, pageSize));
    }

    /**
     * Retrieves persons with an age less than or equal to the specified age, in a paginated manner.
     *
     * @param age        The maximum age to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an iterable of {@link PersonOutputDto} representing the filtered
     *         persons on the specified page.
     */
    @GetMapping("/lteAge")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonByLessOrEqualAge(
            @RequestParam int age,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonByLessOrEqualAge(age, pageNumber, pageSize));
    }

    /**
     * Retrieves persons with an age greater than or equal to the specified age, in a paginated manner.
     *
     * @param age        The minimum age to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an iterable of {@link PersonOutputDto} representing the filtered
     *         persons on the specified page.
     */
    @GetMapping("/gteAge")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonByGreaterOrEqualAge(
            @RequestParam int age,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonByGreaterOrEqualAge(age, pageNumber, pageSize));
    }

    /**
     * Retrieves persons by their last name in a paginated manner.
     *
     * @param lastname   The last name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an iterable of {@link PersonOutputDto} representing the filtered
     *         persons on the specified page.
     */
    @GetMapping("/lastname/{lastname}")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonsByLastname(
            @PathVariable String lastname,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonByLastname(lastname, pageNumber, pageSize));
    }

    /**
     * Retrieves persons by a different name (not an exact match) in a paginated manner.
     *
     * @param name       The name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an iterable of {@link PersonOutputDto} representing the filtered
     *         persons on the specified page.
     */
    @GetMapping("/diffName/{name}")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonsByDiffName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonByDiffName(name, pageNumber, pageSize));
    }

    /**
     * Retrieves persons by the name of their pet in a paginated manner.
     *
     * @param petName    The pet name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an iterable of {@link PersonOutputDto} representing the filtered
     *         persons on the specified page.
     */
    @GetMapping("/petName/{petName}")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonsByPetName(
            @PathVariable String petName,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonByPetName(petName, pageNumber, pageSize));
    }

    /**
     * Retrieves a paginated list of persons whose last name starts with the specified character.
     *
     * @param letter    The character to match as the first letter of the last name.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an {@link Iterable} of {@link PersonOutputDto} representing persons with the specified first letter in their last name.
     */
    @GetMapping("firstLetterLastname/{letter}")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonByFirstLetterLastname(
            @PathVariable char letter,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonByFirstLetterLastname(letter, pageNumber, pageSize));
    }

    /**
     * Retrieves a paginated list of persons who do not have any pets.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an {@link Iterable} of {@link PersonOutputDto} representing persons without pets.
     */
    @GetMapping("noPets")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonsWithoutPets(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonsWithoutPets(pageNumber, pageSize));
    }

    /**
     * Retrieves a paginated list of persons who have at least one pet.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return A {@link ResponseEntity} containing an {@link Iterable} of {@link PersonOutputDto} representing persons with at least one pet.
     */
    @GetMapping("withPets")
    ResponseEntity<Iterable<PersonOutputDto>> getPersonsWithPets(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return ResponseEntity.ok().body(service.getPersonsWithPets(pageNumber, pageSize));
    }


    /**
     * Adds a new person based on the provided {@link PersonInputDto}.
     *
     * @param person The {@link PersonInputDto} containing the information for the new person.
     * @return A {@link ResponseEntity} containing a {@link PersonOutputDto} representing the newly created person.
     */
    @PostMapping
    ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        return ResponseEntity.created(URI.create("/person")).body(service.addPerson(person));
    }

    /**
     * Updates an existing person with the specified ID using the data from the provided {@link PersonInputDto}.
     *
     * @param id  The unique identifier of the person to update.
     * @param dto The {@link PersonInputDto} containing the updated information.
     * @return A {@link ResponseEntity} containing a {@link PersonOutputDto} representing the updated person.
     */
    @PutMapping("/{id}")
    ResponseEntity<PersonOutputDto> updatePersonById(
            @PathVariable String id,
            @RequestBody PersonInputDto dto
    ){
        return ResponseEntity.ok().body(service.updatePersonById(id, dto));
    }

    /**
     * Deletes a person with the specified unique identifier.
     *
     * @param id The unique identifier of the person to delete.
     */
    @DeleteMapping("/{id}")
    void deletePersonById(@PathVariable String id) {
        service.deletePerson(id);
    }
}

