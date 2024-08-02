package otus.spring.dao;

import otus.spring.domain.Person;

public interface PersonDao {

    Person findByFirstName(String firstName);
}
