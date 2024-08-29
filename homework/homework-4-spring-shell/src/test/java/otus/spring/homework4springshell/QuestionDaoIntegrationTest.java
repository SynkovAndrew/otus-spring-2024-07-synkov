package otus.spring.homework4springshell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.homework4springshell.configuration.ApplicationProperties;
import otus.spring.homework4springshell.configuration.QuestionFileNameProvider;
import otus.spring.homework4springshell.dao.QuestionDao;
import otus.spring.homework4springshell.domain.Answer;
import otus.spring.homework4springshell.domain.Question;
import otus.spring.homework4springshell.exception.QuestionReadException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class QuestionDaoIntegrationTest extends IntegrationTest {

    @MockBean
    private ApplicationProperties applicationProperties;

    @Autowired
    private QuestionDao questionDao;

    @Test
    public void getQuestionsSuccessfully() {
        when(applicationProperties.getFileName(any()))
                .thenReturn("test-questions.csv");

        assertThat(questionDao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(getExpectedQuestions());
    }

    @Test
    public void failedToReadQuestionsWhenFileNotFound() {
        when(applicationProperties.getFileName(any()))
                .thenReturn("not-found.csv");

        assertThatThrownBy(questionDao::findAll)
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
