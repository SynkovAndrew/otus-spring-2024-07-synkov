package otus.spring.homework4springshell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import otus.spring.homework4springshell.configuration.LocaleProvider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Homework4SpringBootApplicationTests extends IntegrationTest {

    @Autowired
    private LocaleProvider localeProvider;

    @Test
    void contextLoads() {
        assertNotNull(localeProvider);
    }

}
