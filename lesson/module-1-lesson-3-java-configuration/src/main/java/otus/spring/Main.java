package otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import otus.spring.config.DaoConfig;
import otus.spring.config.ServicesConfig;
import otus.spring.domain.Person;
import otus.spring.service.PersonService;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.name() + " age: " + ivan.age());
    }
}
