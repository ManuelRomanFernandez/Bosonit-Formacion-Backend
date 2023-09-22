package com.formacion.bosonit.block18elasticSearch.repository;

import com.formacion.bosonit.block18elasticSearch.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * The {@code PersonRepository} interface extends the {@link ElasticsearchRepository} and provides custom query methods
 * for accessing and managing {@link Person} entities stored in Elasticsearch.
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {

    /**
     * Retrieves a page of persons with an age less than or equal to the specified age.
     *
     * @param age      The maximum age to filter by.
     * @param pageable The {@link Pageable} configuration for pagination.
     * @return A {@link Page} of {@link Person} entities that meet the age criteria.
     */
    @Query("{\"range\": {\"age\": [{\"lte\": \"?0\"}]}}")
    Page<Person> findByLessOrEqualAge(int age, Pageable pageable);

    /**
     * Retrieves a page of persons with an age greater than or equal to the specified age.
     *
     * @param age      The minimum age to filter by.
     * @param pageable The {@link Pageable} configuration for pagination.
     * @return A {@link Page} of {@link Person} entities that meet the age criteria.
     */
    @Query("{\"range\": {\"age\": [{\"gte\": \"?0\"}]}}")
    Page<Person> findByGreaterOrEqualAge(int age, Pageable pageable);

    /**
     * Retrieves a page of persons by their last name.
     *
     * @param lastname The last name to filter by.
     * @param pageable The {@link Pageable} configuration for pagination.
     * @return A {@link Page} of {@link Person} entities that match the last name criteria.
     */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"lastname\": \"?0\"}}]}}")
    Page<Person> findByLastname(String lastname, Pageable pageable);

    /**
     * Retrieves a page of persons with a name different from the specified name.
     *
     * @param name     The name to filter by.
     * @param pageable The {@link Pageable} configuration for pagination.
     * @return A {@link Page} of {@link Person} entities that do not match the specified name.
     */
    @Query("{\"bool\": {\"must_not\": [{\"match\": {\"name\": \"?0\"}}]}}")
    Page<Person> findByDiffName(String name, Pageable pageable);

    /**
     * Retrieves a page of persons by the name of their pet.
     *
     * @param petName  The pet name to filter by.
     * @param pageable The {@link Pageable} configuration for pagination.
     * @return A {@link Page} of {@link Person} entities that match the specified pet name criteria.
     */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"pets.petName\": \"?0\"}}]}}")
    Page<Person> findByPetName(String petName, Pageable pageable);

    /**
     * Retrieves a paginated list of persons whose last name starts with the specified character.
     *
     * @param letter   The character to match as the first letter of the last name.
     * @param pageable The pagination information for the result set.
     * @return A {@link Page} of {@link Person} objects matching the specified criteria.
     */
    @Query("{\"match_phrase_prefix\": {\"lastname\": \"?0\"}}")
    Page<Person> findByFirstLetterLastname(char letter, Pageable pageable);

    /**
     * Retrieves a paginated list of persons who do not have any pets.
     *
     * @param pageable The pagination information for the result set.
     * @return A {@link Page} of {@link Person} objects who do not have pets.
     */
    @Query("{\"bool\": {\"must_not\": [{\"exists\": {\"field\": \"pets\"}}]}}")
    Page<Person> findPersonsWithoutPets(Pageable pageable);

    /**
     * Retrieves a paginated list of persons who have at least one pet.
     *
     * @param pageable The pagination information for the result set.
     * @return A {@link Page} of {@link Person} objects who have at least one pet.
     */
    @Query("{\"bool\": {\"must\": [{\"exists\": {\"field\": \"pets\"}}]}}")
    Page<Person> findPersonsWithPets(Pageable pageable);

}

