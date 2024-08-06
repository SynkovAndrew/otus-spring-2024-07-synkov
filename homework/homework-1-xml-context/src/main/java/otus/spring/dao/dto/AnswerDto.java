package otus.spring.dao.dto;

import otus.spring.domain.Answer;

public record AnswerDto(String value, Boolean correct) {

    Answer toDomain() {
        return new Answer(value, correct);
    }
}
