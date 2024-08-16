package otus.spring.exersice.dao;


import otus.spring.exersice.domain.Person;

public interface PersonDao {

	Person findByName(String name);
}
