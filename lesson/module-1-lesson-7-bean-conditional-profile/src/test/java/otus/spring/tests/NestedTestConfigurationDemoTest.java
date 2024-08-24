package otus.spring.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import otus.spring.tests.family.FamilyMember;
import otus.spring.tests.family.parents.Father;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("В NestedTestConfigurationDemoTest семья должна ")
@SpringBootTest
public class NestedTestConfigurationDemoTest {

    @Autowired
    private Map<String, FamilyMember> family;

    @DisplayName(" содержать маму, папу, сына и собаку ")
    @Test
    void shouldContainAllFamilyWithFather() {
        assertThat(family).containsOnlyKeys("mother", "father", "son", "dog");
    }

    @TestConfiguration
    static class NestedTestConfigurationDemoTest2Configuration {
        @Bean
        public FamilyMember father() {
            return new Father();
        }
    }
}
