package otus.spring.homework3springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import otus.spring.homework3springboot.service.TestRunnerService;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Homework3SpringBootApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(Homework3SpringBootApplication.class, args);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();
    }

}
