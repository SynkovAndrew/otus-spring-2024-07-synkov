package otus.spring.homework3springboot.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestResult {
    private final Student student;

    private final List<Question> questions;

    private int correctAnswerCount;

    public TestResult(Student student) {
        this.student = student;
        this.questions = new ArrayList<>();
    }

    public void answerQuestion(Question question, Boolean isCorrect) {
        questions.add(question);

        if (isCorrect) {
            correctAnswerCount++;
        }
    }
}
