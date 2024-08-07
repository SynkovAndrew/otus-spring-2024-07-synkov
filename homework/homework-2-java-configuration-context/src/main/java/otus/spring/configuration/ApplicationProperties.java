package otus.spring.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationProperties implements QuestionFileNameProvider, TestConfiguration {
    private final String fileName;

    private final int minimalCorrectAnswerCount;
}
