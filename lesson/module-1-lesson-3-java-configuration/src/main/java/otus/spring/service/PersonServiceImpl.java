package otus.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import otus.spring.dao.PersonDao;
import otus.spring.domain.Person;

@Component
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(@Qualifier("personDaoSimple") PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
