package otus.spring.exersice.service;

import org.springframework.stereotype.Service;
import otus.spring.exersice.dao.PersonDao;
import otus.spring.exersice.domain.Person;
import otus.spring.exersice.logging.Loggable;


@Service
public class PersonServiceImpl implements PersonService {

	private final PersonDao dao;

	public PersonServiceImpl(PersonDao dao) {
		this.dao = dao;
	}

	@Override
	@Loggable
	public Person getByName(String name) {
		return dao.findByName(name);
	}
}
