package otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.spring.dao.PersonDao;
import otus.spring.service.PersonService;
import otus.spring.service.PersonServiceImpl;

@Configuration
public class ServicesConfig {

    @Bean
    public PersonService personService(PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
