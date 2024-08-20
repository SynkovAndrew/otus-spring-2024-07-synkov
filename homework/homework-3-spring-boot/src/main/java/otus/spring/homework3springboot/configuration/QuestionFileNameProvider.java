package otus.spring.homework3springboot.configuration;

import java.util.Locale;

public interface QuestionFileNameProvider {

    String getFileName(Locale locale);
}
