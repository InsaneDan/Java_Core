package lesson4.repository.impl;

import lesson4.model.Person;
import lesson4.repository.PersonRepository;

import java.util.List;

public class TestPersonRepository implements PersonRepository {
    @Override
    public List<Person> getAllData() {
        return List.of(
                new Person("Hans", "Muster"),
                new Person("Ruth", "Mueller"),
                new Person("Heinz", "Kurz"),
                new Person("Cornelia", "Meier"),
                new Person("Werner", "Meyer"),
                new Person("Lydia", "Kunz"),
                new Person("Anna", "Best"),
                new Person("Stefan", "Meier"),
                new Person("Martin", "Mueller")
        );
    }
}
