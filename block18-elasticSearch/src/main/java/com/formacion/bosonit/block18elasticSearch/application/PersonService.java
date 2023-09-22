package com.formacion.bosonit.block18elasticSearch.application;

import com.formacion.bosonit.block18elasticSearch.domain.dto.PersonInputDto;
import com.formacion.bosonit.block18elasticSearch.domain.dto.PersonOutputDto;

/**
 * The {@code PersonService} interface defines a set of methods for managing person-related operations.
 * These operations include retrieving, creating, updating, and deleting person records.
 */
public interface PersonService {

    /**
     * Retrieves a person by their unique identifier.
     *
     * @param id The unique identifier of the person.
     * @return A {@link PersonOutputDto} representing the person with the specified ID.
     */
    PersonOutputDto getPersonById(String id);

    /**
     * Retrieves a paginated list of all persons.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the persons on the specified page.
     */
    Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize);

    /**
     * Retrieves persons with an age less than or equal to the specified age, in a paginated manner.
     *
     * @param age        The maximum age to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    Iterable<PersonOutputDto> getPersonByLessOrEqualAge(int age, int pageNumber, int pageSize);

    /**
     * Retrieves persons with an age greater than or equal to the specified age, in a paginated manner.
     *
     * @param age        The minimum age to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    Iterable<PersonOutputDto> getPersonByGreaterOrEqualAge(int age, int pageNumber, int pageSize);

    /**
     * Retrieves persons by their last name in a paginated manner.
     *
     * @param lastname   The last name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    Iterable<PersonOutputDto> getPersonByLastname(String lastname, int pageNumber, int pageSize);

    /**
     * Retrieves persons by a different name (not an exact match) in a paginated manner.
     *
     * @param name       The name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    Iterable<PersonOutputDto> getPersonByDiffName(String name, int pageNumber, int pageSize);

    /**
     * Retrieves persons by the name of their pet in a paginated manner.
     *
     * @param petName    The pet name to filter by.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An iterable of {@link PersonOutputDto} representing the filtered persons on the specified page.
     */
    Iterable<PersonOutputDto> getPersonByPetName(String petName, int pageNumber, int pageSize);

    /**
     * Retrieves a paginated list of persons whose last name starts with the specified character.
     *
     * @param letter    The character to match as the first letter of the last name.
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An {@link Iterable} of {@link PersonOutputDto} representing persons with the specified first letter in their last name.
     */
    Iterable<PersonOutputDto> getPersonByFirstLetterLastname(char letter, int pageNumber, int pageSize);

    /**
     * Retrieves a paginated list of persons who do not have any pets.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An {@link Iterable} of {@link PersonOutputDto} representing persons without pets.
     */
    Iterable<PersonOutputDto> getPersonsWithoutPets(int pageNumber, int pageSize);

    /**
     * Retrieves a paginated list of persons who have at least one pet.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The maximum number of persons to return per page.
     * @return An {@link Iterable} of {@link PersonOutputDto} representing persons with at least one pet.
     */
    Iterable<PersonOutputDto> getPersonsWithPets(int pageNumber, int pageSize);

    /**
     * Adds a new person based on the provided {@link PersonInputDto}.
     *
     * @param dto The {@link PersonInputDto} containing the information for the new person.
     * @return A {@link PersonOutputDto} representing the newly created person.
     */
    PersonOutputDto addPerson(PersonInputDto dto);

    /**
     * Updates an existing person with the specified ID using the data from the provided {@link PersonInputDto}.
     *
     * @param id  The unique identifier of the person to update.
     * @param dto The {@link PersonInputDto} containing the updated information.
     * @return A {@link PersonOutputDto} representing the updated person.
     */
    PersonOutputDto updatePersonById(String id, PersonInputDto dto);

    /**
     * Deletes a person with the specified unique identifier.
     *
     * @param id The unique identifier of the person to delete.
     */
    void deletePerson(String id);
}

