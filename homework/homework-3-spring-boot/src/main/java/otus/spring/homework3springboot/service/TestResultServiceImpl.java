package otus.spring.homework3springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.homework3springboot.domain.TestResult;
import otus.spring.homework3springboot.configuration.TestConfiguration;

@Component
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {
    private final LocalizedIOService ioService;

    private final TestConfiguration testConfiguration;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLocalizedLine("results.header");
        ioService.printLocalizedFormattedLine("student.title", testResult.getStudent().getFullName());
        ioService.printLocalizedFormattedLine("total.questions.title", testResult.getQuestions().size());
        ioService.printLocalizedFormattedLine("total.correct.answers.title", testResult.getCorrectAnswerCount());

        if (testResult.getCorrectAnswerCount() >= testConfiguration.getMinimalCorrectAnswerCount()) {
            ioService.printLocalizedLine("test.passed.title");
            return;
        }
        ioService.printLocalizedLine("test.failed.title");
    }
}
