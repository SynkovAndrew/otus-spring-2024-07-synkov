package otus.spring.homework4springshell.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Data
@ConfigurationProperties("application")
public class ApplicationProperties implements QuestionFileNameProvider, TestingConfiguration, LocaleProvider {
    private final Map<Locale, String> fileNameByLocale;

    private final int minimalCorrectAnswerCount;

    private final Locale locale;

    @Override
    public String getFileName(Locale locale) {
        return Optional.ofNullable(fileNameByLocale.get(locale))
                .orElseThrow(() -> new IllegalArgumentException("Locale not supported: " + locale));
    }
}
