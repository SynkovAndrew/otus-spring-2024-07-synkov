package otus.spring.homework4springshell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.homework4springshell.dao.QuestionDao;
import otus.spring.homework4springshell.domain.Question;
import otus.spring.homework4springshell.domain.TestResult;
import otus.spring.homework4springshell.exception.StudentNotLoggedInException;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    private final TestResultService testResultService;

    private final StudentHolder studentHolder;

    @Override
    public void executeTest() {
        final TestResult testResult = studentHolder.get()
                .map(TestResult::new)
                .orElseThrow(() -> new StudentNotLoggedInException("Student not logged in"));

        processQuestions(testResult);

        testResultService.showResult(testResult);
    }

    void processQuestions(TestResult testResult) {
        ioService.printLine("");
        ioService.printLocalizedLine("answer.question.title");
        ioService.printLine("");
        questionDao.findAll()
                .forEach(question -> processQuestion(question, testResult));
    }

    void processQuestion(Question question, TestResult testResult) {
        ioService.printLine(question.value());

        question.answers()
                .forEach(answer -> ioService.printFormattedLine("\t- %d. %s\n", answer.number(), answer.value()));

        var input = ioService.readInteger();
        var isCorrect = Objects.equals(question.getCorrectAnswer().number(), input);

        testResult.answerQuestion(question, isCorrect);
    }
}
