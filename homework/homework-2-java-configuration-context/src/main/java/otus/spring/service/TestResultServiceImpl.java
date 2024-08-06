package otus.spring.service;

import lombok.RequiredArgsConstructor;
import otus.spring.domain.TestResult;

@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {
    private final IOService ioService;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLine("Results");
        ioService.printFormattedLine("Student: %s\n", testResult.getStudent().getFullName());
        ioService.printFormattedLine("Total questions: %s\n", testResult.getQuestions().size());
        ioService.printFormattedLine("Total correct answer: %s\n", testResult.getCorrectAnswerCount());

        if (testResult.getCorrectAnswerCount() >= 2) {
            ioService.printLine("Congratulations! You passed the test!");
            return;
        }
        ioService.printLine("Sorry, You didn't pass the test");
    }
}
