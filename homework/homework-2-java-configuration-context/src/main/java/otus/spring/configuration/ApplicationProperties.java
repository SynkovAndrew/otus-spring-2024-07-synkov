package otus.spring.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource("classpath:application.properties")
@Configuration
public class ApplicationProperties implements QuestionFileNameProvider, TestConfiguration {
    private final String fileName;

    private final int minimalCorrectAnswerCount;

    public ApplicationProperties(
            @Value("${file.name}") String fileName,
            @Value("${minimal.correct.answer.count}") int minimalCorrectAnswerCount
    ) {
        this.fileName = fileName;
        this.minimalCorrectAnswerCount = minimalCorrectAnswerCount;
    }
}
