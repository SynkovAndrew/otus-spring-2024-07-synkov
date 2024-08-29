package otus.spring.homework4springshell.domain;


import java.util.List;

public record Question(String value, List<Answer> answers) {

    public Answer getCorrectAnswer() {
        return answers.stream()
                .filter(Answer::correct)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Question \"" + value + "\" has no correct answer"));
    }
}
