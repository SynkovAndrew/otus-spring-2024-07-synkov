package otus.spring.exersice_1_solution.dao;


import otus.spring.exersice_1_solution.domain.Person;

import java.util.List;

public interface PersonDao {
    int count();

    void insert(Person person);

    Person getById(long id);

    List<Person> getAll();

    void deleteById(long id);
}
