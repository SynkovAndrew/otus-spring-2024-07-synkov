package otus.spring.homework3springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.homework3springboot.domain.TestResult;
import otus.spring.homework3springboot.configuration.TestConfiguration;

@Component
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {
    private final IOService ioService;

    private final TestConfiguration testConfiguration;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLine("########### Results ###########");
        ioService.printFormattedLine("Student: %s\n", testResult.getStudent().getFullName());
        ioService.printFormattedLine("Total questions: %s\n", testResult.getQuestions().size());
        ioService.printFormattedLine("Total correct answer: %s\n", testResult.getCorrectAnswerCount());

        if (testResult.getCorrectAnswerCount() >= testConfiguration.getMinimalCorrectAnswerCount()) {
            ioService.printLine("Congratulations! You passed the test!");
            return;
        }
        ioService.printLine("Sorry, You didn't pass the test");
    }
}
