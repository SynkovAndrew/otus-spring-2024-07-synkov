package otus.spring.service;

import otus.spring.dao.PersonDao;
import otus.spring.domain.Person;

public class PersonServiceImpl implements PersonService {
    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public Person getByFirstName(String FirstName) {
        return dao.findByFirstName(FirstName);
    }
}
