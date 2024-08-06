package otus.spring.domain;

import java.util.List;

public record Question(String value, List<Answer> answers) {
}
