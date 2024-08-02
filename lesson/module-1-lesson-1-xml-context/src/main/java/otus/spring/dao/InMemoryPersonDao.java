package otus.spring.dao;

import otus.spring.domain.Person;

public class InMemoryPersonDao implements PersonDao {
    @Override
    public Person findByFirstName(String firstName) {
        return new Person("Andrew", "Synkov");
    }
}
