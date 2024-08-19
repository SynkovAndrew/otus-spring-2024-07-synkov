package otus.spring.homework3springboot.dao.dto;

import otus.spring.homework3springboot.domain.Answer;

public record AnswerDto(Integer number, String value, Boolean correct) {

    Answer toDomain() {
        return new Answer(number, value, correct);
    }
}
