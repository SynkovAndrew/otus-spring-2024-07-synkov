package otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.spring.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("./application-context.xml");
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();
    }
}