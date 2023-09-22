package com.formacion.bosonit.block18elasticSearch;

import com.formacion.bosonit.block18elasticSearch.domain.Person;
import com.formacion.bosonit.block18elasticSearch.domain.Pet;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code Block18ElasticSearchApplication} class represents the main entry point for a Spring Boot application
 * that demonstrates Elasticsearch integration. It includes a method annotated with {@link PostConstruct} to populate
 * Elasticsearch with sample data upon application startup.
 */
@SpringBootApplication
public class Block18ElasticSearchApplication {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * The main method that launches the Spring Boot application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Block18ElasticSearchApplication.class, args);
    }

    /**
     * A method annotated with {@link PostConstruct} that populates Elasticsearch with sample data upon application startup.
     * It generates random {@link Person} objects with associated {@link Pet} objects and indexes them into Elasticsearch.
     */
    @PostConstruct
    private void loading() {
        IndexCoordinates coordinates = IndexCoordinates.of("person");
        String[] names = {"Manuel", "Antonio", "Daniel", "Javier", "Jesus", "Reichel", "Ana", "Guadalupe", "Maria", "Ortensia"};
        String[] lastnames = {"Ramon", "Roman", "Lopez", "Garcia", "Fernandez", "Castillo", "Alba"};
        String[] petNames = {"Boby", "Bola", "Kiki", "Adolfo", "Nala"};
        String[] petRaces = {"dog", "cat", "hamster", "turtle"};

        // Delete the existing Elasticsearch index to start fresh
        elasticsearchTemplate.indexOps(coordinates).delete();

        for (int i = 0; i < 50; i++) {
            List<Pet> petList = new ArrayList<>();

            for (int j = 0; j < randomNumber(3, 0); j++) {
                Pet pet = new Pet(
                        petNames[randomNumber(5, 0)],
                        petRaces[randomNumber(4, 0)],
                        randomNumber(18, 0));

                petList.add(pet);
            }

            Person person = new Person(
                    names[randomNumber(10, 0)],
                    lastnames[randomNumber(7, 0)],
                    randomNumber(82, 18),
                    petList
            );

            IndexQuery query = new IndexQueryBuilder().withObject(person).build();
            elasticsearchTemplate.index(query, coordinates);
        }
    }

    /**
     * Generates a random integer between the specified minimum and maximum values (inclusive).
     *
     * @param max The maximum value.
     * @param min The minimum value.
     * @return A random integer within the specified range.
     */
    private int randomNumber(int max, int min) {
        return new Random().nextInt(max) + min;
    }
}
