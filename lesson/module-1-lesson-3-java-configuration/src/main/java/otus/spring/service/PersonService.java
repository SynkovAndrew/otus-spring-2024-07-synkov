package otus.spring.service;

import otus.spring.domain.Person;

public interface PersonService {

    Person getByName(String name);
}
