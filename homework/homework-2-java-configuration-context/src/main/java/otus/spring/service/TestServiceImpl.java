package otus.spring.service;

import lombok.RequiredArgsConstructor;
import otus.spring.dao.QuestionDao;
import otus.spring.domain.Question;
import otus.spring.domain.Student;
import otus.spring.domain.TestResult;

import java.util.Objects;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final IOService ioService;

    private final QuestionDao questionDao;

    private final TestResultService testResultService;

    @Override
    public void executeTest() {
        ioService.printFormattedLine("First Name: ");
        var firstName = ioService.readLine();

        ioService.printFormattedLine("Last Name: ");
        var lastName = ioService.readLine();

        var student = new Student (firstName, lastName);
        TestResult testResult = new TestResult(student);

        ioService.printLine("");
        ioService.printLine("Please answer the questions below:");
        ioService.printLine("");
        questionDao.findAll()
                .forEach(question -> processQuestion(question, testResult));

        testResultService.showResult(testResult);
    }

    void processQuestion(Question question, TestResult testResult) {
        ioService.printLine(question.value());

        question.answers()
                .forEach(answer -> ioService.printFormattedLine("\t- %s\n", answer.value()));

        var answer = ioService.readLine();

        testResult.answerQuestion(question, answer);
    }
}
