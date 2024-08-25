package otus.spring.homework4springshell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import otus.spring.homework4springshell.service.TestRunnerService;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Homework4SpringBootApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(Homework4SpringBootApplication.class, args);
        var testRunnerService = context.getBean(TestRunnerService.class);
        //testRunnerService.run();
    }

}
