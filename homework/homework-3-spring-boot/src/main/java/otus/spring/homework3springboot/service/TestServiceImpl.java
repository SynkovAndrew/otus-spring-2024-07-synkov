package otus.spring.homework3springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.homework3springboot.dao.QuestionDao;
import otus.spring.homework3springboot.domain.Question;
import otus.spring.homework3springboot.domain.Student;
import otus.spring.homework3springboot.domain.TestResult;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    private final StudentService studentService;

    private final TestResultService testResultService;

    @Override
    public void executeTest() {
        Student student = studentService.readCurrentStudent();
        TestResult testResult = new TestResult(student);

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
