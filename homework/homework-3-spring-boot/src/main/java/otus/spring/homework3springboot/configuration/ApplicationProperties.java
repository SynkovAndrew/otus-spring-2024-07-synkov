package otus.spring.homework3springboot.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Data
@ConfigurationProperties("application")
public class ApplicationProperties implements QuestionFileNameProvider, TestConfiguration, LocaleProvider {
    private final Map<Locale, String> fileNameByLocale;

    private final int minimalCorrectAnswerCount;

    private final Locale locale;

    @Override
    public String getFileName(Locale locale) {
        return Optional.ofNullable(fileNameByLocale.get(locale))
                .orElseThrow(() -> new IllegalArgumentException("Locale not supported: " + locale));
    }
}
