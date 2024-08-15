package otus.spring.dao;

import org.junit.jupiter.api.Test;
import otus.spring.domain.Answer;
import otus.spring.domain.Question;
import otus.spring.exception.QuestionReadException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionDaoIntegrationTest {

    @Test
    public void getQuestionsSuccessfully() {
        var dao = new QuestionCsvDao(() -> "test-questions.csv");

        assertThat(dao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(getExpectedQuestions());
    }

    @Test
    public void failedToReadQuestionsWhenFileNotFound() {
        var dao = new QuestionCsvDao(() -> "not-found.csv");

        assertThatThrownBy(dao::findAll)
                .hasMessage("File not-found.csv not found")
                .isInstanceOf(QuestionReadException.class);
    }

    private List<Question> getExpectedQuestions() {
        return Arrays.asList(
                new Question(
                        "What is the largest country in the world?",
                        Arrays.asList(
                                new Answer(1, "Russia", true),
                                new Answer(2, "India", false)
                        )
                ),
                new Question(
                        "Which country has largest population?",
                        Arrays.asList(
                                new Answer(1, "China", true),
                                new Answer(2, "Thailand", false)
                        )
                )
        );
    }
}
