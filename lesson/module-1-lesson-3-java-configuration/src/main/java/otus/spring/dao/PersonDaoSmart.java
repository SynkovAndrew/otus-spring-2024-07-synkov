package otus.spring.dao;

import otus.spring.domain.Person;

public class PersonDaoSmart implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 21);
    }
}
