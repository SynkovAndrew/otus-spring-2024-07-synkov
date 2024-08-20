package otus.spring.homework3springboot.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

@Data
@ConfigurationProperties("application")
public class ApplicationProperties implements QuestionFileNameProvider, TestConfiguration, LocaleProvider {
    private final String fileName;

    private final int minimalCorrectAnswerCount;

    private final Locale locale;
}
