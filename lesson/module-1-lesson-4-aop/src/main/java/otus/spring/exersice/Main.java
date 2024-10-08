package otus.spring.exersice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import otus.spring.exersice.domain.Person;
import otus.spring.exersice.service.PersonService;


@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Main.class);

		PersonService service = context.getBean(PersonService.class);

		Person ivan = service.getByName("Ivan");
		System.out.println("name: " + ivan.name() + " age: " + ivan.age());
	}
}
