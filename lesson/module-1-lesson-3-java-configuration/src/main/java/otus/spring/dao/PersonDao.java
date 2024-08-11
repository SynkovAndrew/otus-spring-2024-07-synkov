package otus.spring.dao;

import otus.spring.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
