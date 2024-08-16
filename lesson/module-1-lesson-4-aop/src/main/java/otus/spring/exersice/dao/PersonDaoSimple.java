package otus.spring.exersice.dao;

import org.springframework.stereotype.Repository;
import otus.spring.exersice.domain.Person;
import otus.spring.exersice.logging.Loggable;


@Repository
public class PersonDaoSimple implements PersonDao {

	@Override
	@Loggable
	public Person findByName(String name) {
		return new Person(name, 18);
	}
}
