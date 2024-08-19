package otus.spring.homework3springboot;

import org.junit.jupiter.api.Test;
import otus.spring.homework3springboot.domain.Answer;
import otus.spring.homework3springboot.domain.Question;
import otus.spring.homework3springboot.domain.Student;
import otus.spring.homework3springboot.domain.TestResult;
import otus.spring.homework3springboot.service.IOService;
import otus.spring.homework3springboot.service.StreamsIOService;
import otus.spring.homework3springboot.service.TestResultService;
import otus.spring.homework3springboot.service.TestResultServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestResultServiceUnitTest {

    @Test
    void showResultSuccessTest() {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final InputStream inputStream = new ByteArrayInputStream("".getBytes());
        final PrintStream printStream = new PrintStream(outputStream);
        final var ioService = new StreamsIOService(printStream, inputStream);
        final TestResultService testResultService = new TestResultServiceImpl(ioService, () -> 1);

        var testResult = getTestResult();

        testResultService.showResult(testResult);

        assertThat(outputStream.toString())
                .contains("########### Results ###########")
                .contains("Student: Ivan Petrov")
                .contains("Total questions: 2")
                .contains("Total correct answer: 2")
                .contains("Congratulations! You passed the test!");
    }

    @Test
    void showResultFailTest() {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final InputStream inputStream = new ByteArrayInputStream("".getBytes());
        final PrintStream printStream = new PrintStream(outputStream);
        final IOService ioService = new StreamsIOService(printStream, inputStream);
        final TestResultService testResultService = new TestResultServiceImpl(ioService, () -> 3);

        var testResult = getTestResult();

        testResultService.showResult(testResult);

        assertThat(outputStream.toString())
                .contains("########### Results ###########")
                .contains("Student: Ivan Petrov")
                .contains("Total questions: 2")
                .contains("Total correct answer: 2")
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
}
