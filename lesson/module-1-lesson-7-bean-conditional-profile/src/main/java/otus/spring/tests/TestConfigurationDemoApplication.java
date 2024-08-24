package otus.spring.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("otus.spring.tests.family")
@SpringBootApplication
public class TestConfigurationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestConfigurationDemoApplication.class, args);
	}

}
