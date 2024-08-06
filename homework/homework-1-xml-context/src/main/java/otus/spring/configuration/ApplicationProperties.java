package otus.spring.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationProperties implements QuestionFileNameProvider {
    private final String fileName;
}
