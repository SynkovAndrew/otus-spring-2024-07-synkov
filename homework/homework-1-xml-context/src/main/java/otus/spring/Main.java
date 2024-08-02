package otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.spring.service.PersonService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        var context = new ClassPathXmlApplicationContext("./application-context.xml");
        var personService = context.getBean(PersonService.class);

        System.out.println(personService.getByFirstName("Andrew"));

        context.close();
    }
}