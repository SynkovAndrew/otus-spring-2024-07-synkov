package otus.spring.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class TestResult {
    private final Student student;

    private final List<Question> questions;

    private int correctAnswerCount;

    public TestResult(Student student) {
        this.student = student;
        this.questions = new ArrayList<>();
    }

    public void answerQuestion(Question question, String answer) {
        questions.add(question);

        if (Objects.equals(question.getCorrectAnswer().value(), answer)) {
            correctAnswerCount++;
        }
    }
}
