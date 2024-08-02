package otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.spring.domain.Person;
import otus.spring.service.PersonService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceTest {

    @Test
    void test() {
        var context = new ClassPathXmlApplicationContext("./application-context.xml");
        var personService = context.getBean(PersonService.class);

        assertEquals(
                new Person("Andrew", "Synkov"),
                personService.getByFirstName("Andrew")
        );
    }
}
