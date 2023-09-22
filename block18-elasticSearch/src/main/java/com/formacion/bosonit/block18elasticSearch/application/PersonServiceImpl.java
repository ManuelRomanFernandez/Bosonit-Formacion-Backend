package com.formacion.bosonit.block18elasticSearch.application;

import com.formacion.bosonit.block18elasticSearch.domain.Person;
import com.formacion.bosonit.block18elasticSearch.domain.dto.PersonInputDto;
import com.formacion.bosonit.block18elasticSearch.domain.dto.PersonOutputDto;
import com.formacion.bosonit.block18elasticSearch.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The {@code PersonServiceImpl} class implements the {@link PersonService} interface and provides
 * service methods for managing {@link Person} entities. It interacts with a {@link PersonRepository}
 * to perform CRUD (Create, Read, Update, Delete) operations on persons stored in Elasticsearch.
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository repository;

    Function<Person, PersonOutputDto> personToOuput = person ->
            new PersonOutputDto().personToOutputDto(person);

    Supplier<NoSuchElementException> exceptionSupplier = () ->
            new NoSuchElementException("Person with provided ID does not exists");

    /**
     * Retrieves a person by their unique identifier.
     *
     * @param id The unique identifier of the person.
     * @return A {@link PersonOutputDto} representing the person with the specified ID.
     * @throws NoSuchElementException if no person with the specified ID is found.
     */
    @Override
    public PersonOutputDto getPersonById(String id) {
        return repository.findById(id).map(personToOuput).orElseThrow(exceptionSupplier);
    }

    /**
     * Retrieves a paginated list of all persons.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the persons on the specified page.
     */
    @Override
    public Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable).map(personToOuput);
    }

    /**
     * Retrieves persons with an age less than or equal to the specified age, in a paginated manner.
     *
     * @param age        The maximum age to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonByLessOrEqualAge(int age, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findByLessOrEqualAge(age, pageable).map(personToOuput);
    }

    /**
     * Retrieves persons with an age greater than or equal to the specified age, in a paginated manner.
     *
     * @param age        The minimum age to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonByGreaterOrEqualAge(int age, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findByGreaterOrEqualAge(age, pageable).map(personToOuput);
    }

    /**
     * Retrieves persons by their last name in a paginated manner.
     *
     * @param lastname   The last name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonByLastname(String lastname, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findByLastname(lastname, pageable).map(personToOuput);
    }

    /**
     * Retrieves persons by a different name (not an exact match) in a paginated manner.
     *
     * @param name       The name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonByDiffName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findByDiffName(name, pageable).map(personToOuput);
    }

    /**
     * Retrieves persons by the name of their pet in a paginated manner.
     *
     * @param petName    The pet name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonByPetName(String petName, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findByPetName(petName, pageable).map(personToOuput);
    }

    /**
     * Retrieves a paginated list of persons whose last name starts with the specified character.
     *
     * @param letter     The character to match as the first letter of the last name.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An {@link Iterable} of {@link PersonOutputDto} representing persons with the specified first letter in their last name.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonByFirstLetterLastname(char letter, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findByFirstLetterLastname(Character.toUpperCase(letter), pageable).map(personToOuput);
    }

    /**
     * Retrieves a paginated list of persons who do not have any pets.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An {@link Iterable} of {@link PersonOutputDto} representing persons without pets.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonsWithoutPets(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findPersonsWithoutPets(pageable).map(personToOuput);
    }

    /**
     * Retrieves a paginated list of persons who have at least one pet.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An {@link Iterable} of {@link PersonOutputDto} representing persons with at least one pet.
     */
    @Override
    public Iterable<PersonOutputDto> getPersonsWithPets(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findPersonsWithPets(pageable).map(personToOuput);
    }


    /**
     * Adds a new person based on the provided {@link PersonInputDto}.
     *
     * @param dto The {@link PersonInputDto} containing the information for the new person.
     * @return A {@link PersonOutputDto} representing the newly created person.
     */
    @Override
    public PersonOutputDto addPerson(PersonInputDto dto) {
        Person addPerson = repository.save(dto.inputDtoToPerson(dto));
        return new PersonOutputDto().personToOutputDto(addPerson);
    }

    /**
     * Updates an existing person with the specified ID using the data from the provided {@link PersonInputDto}.
     *
     * @param id  The unique identifier of the person to update.
     * @param dto The {@link PersonInputDto} containing the updated information.
     * @return A {@link PersonOutputDto} representing the updated person.
     * @throws NoSuchElementException if no person with the specified ID is found.
     */
    @Override
    public PersonOutputDto updatePersonById(String id, PersonInputDto dto) {
        repository.findById(id).orElseThrow(exceptionSupplier);

        Person person = dto.inputDtoToPerson(dto);
        person.setId(id);

        Person updatedPerson = repository.save(person);
        return new PersonOutputDto().personToOutputDto(updatedPerson);
    }

    /**
     * Deletes a person with the specified unique identifier.
     *
     * @param id The unique identifier of the person to delete.
     * @throws NoSuchElementException if no person with the specified ID is found.
     */
    @Override
    public void deletePerson(String id) {
        repository.findById(id).orElseThrow(exceptionSupplier);
        repository.deleteById(id);
    }
}
