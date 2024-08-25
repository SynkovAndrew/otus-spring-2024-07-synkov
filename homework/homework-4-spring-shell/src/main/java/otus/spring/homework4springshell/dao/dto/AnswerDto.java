package otus.spring.homework4springshell.dao.dto;

import otus.spring.homework4springshell.domain.Answer;

public record AnswerDto(Integer number, String value, Boolean correct) {

    Answer toDomain() {
        return new Answer(number, value, correct);
    }
}
