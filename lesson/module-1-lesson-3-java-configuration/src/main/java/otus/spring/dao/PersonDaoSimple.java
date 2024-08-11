package otus.spring.dao;

import otus.spring.domain.Person;

public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
