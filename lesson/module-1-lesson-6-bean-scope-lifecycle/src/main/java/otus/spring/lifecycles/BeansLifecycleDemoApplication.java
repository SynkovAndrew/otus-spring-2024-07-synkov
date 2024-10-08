package otus.spring.lifecycles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import otus.spring.lifecycles.domain.Phone;

@SpringBootApplication
public class BeansLifecycleDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BeansLifecycleDemoApplication.class, args);
        Phone phone = ctx.getBean(Phone.class);
        phone.callFavoriteNumber();
    }

}
