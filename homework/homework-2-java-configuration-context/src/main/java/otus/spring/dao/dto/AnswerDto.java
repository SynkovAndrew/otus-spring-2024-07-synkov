package otus.spring.dao.dto;

import otus.spring.domain.Answer;

public record AnswerDto(Integer number, String value, Boolean correct) {

    Answer toDomain() {
        return new Answer(number, value, correct);
    }
}
