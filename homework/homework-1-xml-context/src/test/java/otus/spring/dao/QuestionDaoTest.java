package otus.spring.dao;

import org.junit.jupiter.api.Test;
import otus.spring.domain.Answer;
import otus.spring.domain.Question;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDaoTest {

    @Test
    public void getQuestionsSuccessfully() {
        var dao = new QuestionCsvDao(() -> "questions.csv");

        assertThat(dao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(getExpectedQuestions());
    }

    private List<Question> getExpectedQuestions() {
        return Arrays.asList(
                new Question(
                        "What is the largest country in the world?",
                        Arrays.asList(
                                new Answer("Russia", true),
                                new Answer("India", false)
                        )
                ),
                new Question(
                        "Which country has largest population?",
                        Arrays.asList(
                                new Answer("China", true),
                                new Answer("Thailand", false)
                        )
                )
        );
    }
}
