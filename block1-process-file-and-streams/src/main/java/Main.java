import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/people.csv";
        try{
            List<Person> people = getPeopleList(filePath);

            List<Person> ageFilteredPeople = ageFilter(people);

            ageFilteredPeople.forEach(person -> System.out.println(person.toString()));

            System.out.println("----------------------");

            List<Person> nameFilteredPeople = nameFilter(people);

            nameFilter(nameFilteredPeople).forEach(person -> System.out.println(person.toString()));

            System.out.println("----------------------");

            Optional<Person> madridOptional = ageFilteredPeople.stream()
                    .filter(person -> person.getTown().equalsIgnoreCase("madrid"))
                    .findFirst();

            madridOptional.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No results found for town: Madrid"));

            System.out.println("----------------------");

            Optional<Person> barcelonaOptional = ageFilteredPeople.stream()
                    .filter(person -> person.getTown().equalsIgnoreCase("barcelona"))
                    .findFirst();

            barcelonaOptional.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No results found for town: Barcelona"));
        }catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static List<Person> getPeopleList(String filePath) throws IOException {
        List<Person> peopleList = new ArrayList<>();
        Path peopleFile = Paths.get(filePath);
        List<String> lines = Files.readAllLines(peopleFile);

        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            try {
                String[] currentFields = currentLine.split(":", -1);
                validateLine(currentFields, i);
                Person currentPerson = new Person(currentFields);
                peopleList.add(currentPerson);
            } catch (InvalidLineFormatException e) {
                e.printStackTrace();
            }
        }

        return peopleList;
    }

    public static void validateLine(String[] fields, int lineNumber) throws InvalidLineFormatException {
        if (fields[0].isEmpty()) {
            throw new InvalidLineFormatException("Name cannot be empty at line " + (lineNumber + 1));
        } else if (fields.length == 2) {
            throw new InvalidLineFormatException("Missing one delimiter at line " + (lineNumber + 1));
        } else if (fields.length == 1) {
            throw new InvalidLineFormatException("Missing two delimiters at line " + (lineNumber + 1));
        }
    }

    public static List<Person> ageFilter(List<Person> people) {
        List<Person> newPeopleList = new ArrayList<>();

        people.stream()
                .filter(person -> person.getAge() < 27 && person.getAge() != 0)
                .forEach(newPeopleList::add);

        return newPeopleList;
    }

    public static List<Person> nameFilter(List<Person> people) {
        List<Person> newPeopleList = new ArrayList<>();

        people.stream()
                .filter(person -> !startsWithIgnoreCaseAndAccents(person.getName(), 'a'))
                .forEach(newPeopleList::add);

        return newPeopleList;
    }

    public static boolean startsWithIgnoreCaseAndAccents(String text, char startingLetter) {
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
        char normalizedStartingLetter = Character.toLowerCase(startingLetter);
        return normalizedText.startsWith(String.valueOf(normalizedStartingLetter));
    }
}