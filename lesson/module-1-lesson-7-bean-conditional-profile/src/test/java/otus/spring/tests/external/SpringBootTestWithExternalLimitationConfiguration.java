package otus.spring.tests.external;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import otus.spring.tests.family.FamilyMember;
import otus.spring.tests.family.parents.Father;

@ComponentScan(
        {
                "otus.spring.tests.family.childrens",
                "otus.spring.tests.family.parents",
        }
)
@SpringBootConfiguration
public class SpringBootTestWithExternalLimitationConfiguration {

    @Bean
    public FamilyMember father() {
        return new Father();
    }
}
