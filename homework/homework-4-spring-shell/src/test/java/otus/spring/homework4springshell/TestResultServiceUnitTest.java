package otus.spring.homework4springshell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import otus.spring.homework4springshell.configuration.TestingConfiguration;
import otus.spring.homework4springshell.domain.Answer;
import otus.spring.homework4springshell.domain.Question;
import otus.spring.homework4springshell.domain.Student;
import otus.spring.homework4springshell.domain.TestResult;
import otus.spring.homework4springshell.service.IOService;
import otus.spring.homework4springshell.service.StreamsIOService;
import otus.spring.homework4springshell.service.TestResultService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TestResultServiceUnitTest extends IntegrationTest {
    private final static OutputStream outputStream = new ByteArrayOutputStream();
    private final static InputStream inputStream = new ByteArrayInputStream("".getBytes());
    private final static PrintStream printStream = new PrintStream(outputStream);

    @Autowired
    private TestResultService testResultService;

    @SpyBean
    private TestingConfiguration testingConfiguration;

    @Test
    void showResultSuccessTest() {
        when(testingConfiguration.getMinimalCorrectAnswerCount()).thenReturn(1);
        var testResult = getTestResult();

        testResultService.showResult(testResult);

        assertThat(outputStream.toString())
                .contains("########### Results ###########")
                .contains("Student: Ivan Petrov")
                .contains("Total questions: 2")
                .contains("Total correct answers: 2")
                .contains("Congratulations! You passed the test!");
    }

    @Test
    void showResultFailTest() {
        when(testingConfiguration.getMinimalCorrectAnswerCount()).thenReturn(3);
        var testResult = getTestResult();

        testResultService.showResult(testResult);

        assertThat(outputStream.toString())
                .contains("########### Results ###########")
                .contains("Student: Ivan Petrov")
                .contains("Total questions: 2")
                .contains("Total correct answers: 2")
                .contains("Sorry, You didn't pass the test");
    }

    private TestResult getTestResult() {
        var testResult = new TestResult(new Student("Ivan", "Petrov"));
        testResult.answerQuestion(
                new Question("What?", List.of(new Answer(1, "That", true))),
                true
        );
        testResult.answerQuestion(
                new Question("Why?", List.of(new Answer(2, "Because", true))),
                true
        );
        return testResult;
    }

    @TestConfiguration
    static class TestServiceConfiguration {

        @Bean
        IOService streamsIOService() {
            return new StreamsIOService(printStream, inputStream);
        }
    }
}
