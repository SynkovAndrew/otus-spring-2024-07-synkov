package otus.spring.homework4springshell.configuration;

import java.util.Locale;

public interface QuestionFileNameProvider {

    String getFileName(Locale locale);
}
