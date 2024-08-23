package otus.spring.lifecycles.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(CustomBeanDefinitionRegistrar.class)
@Configuration
public class SpringConfiguration {
}
