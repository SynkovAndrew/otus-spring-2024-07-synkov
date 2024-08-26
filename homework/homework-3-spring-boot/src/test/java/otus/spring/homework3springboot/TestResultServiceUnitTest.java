package otus.spring.homework3springboot;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import otus.spring.homework3springboot.domain.Answer;
import otus.spring.homework3springboot.domain.Question;
import otus.spring.homework3springboot.domain.Student;
import otus.spring.homework3springboot.domain.TestResult;
import otus.spring.homework3springboot.service.LocalizedIOServiceImpl;
import otus.spring.homework3springboot.service.LocalizedMessageServiceImpl;
import otus.spring.homework3springboot.service.StreamsIOService;
import otus.spring.homework3springboot.service.TestResultService;
import otus.spring.homework3springboot.service.TestResultServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class TestResultServiceUnitTest {

    private final MessageSource messageSource = createMessageSource();

    @Test
    void showResultSuccessTest() {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final TestResultService testResultService = getTestResultService(
                outputStream,
                messageSource,
                1
        );

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
        final OutputStream outputStream = new ByteArrayOutputStream();
        final TestResultService testResultService = getTestResultService(
                outputStream,
                messageSource,
                3
        );

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

    private TestResultService getTestResultService(
            OutputStream outputStream,
            MessageSource messageSource,
            Integer correctAnswerCountThreshold
    ) {
        final InputStream inputStream = new ByteArrayInputStream("".getBytes());
        final PrintStream printStream = new PrintStream(outputStream);
        final var ioService = new StreamsIOService(printStream, inputStream);
        final var localizedMessageService = new LocalizedMessageServiceImpl(() -> Locale.ENGLISH, messageSource);
        final var localizedIOService = new LocalizedIOServiceImpl(ioService, localizedMessageService);
        return new TestResultServiceImpl(localizedIOService, () -> correctAnswerCountThreshold);
    }

    private static MessageSource createMessageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
