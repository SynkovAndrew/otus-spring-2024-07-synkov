package otus.spring.service;

import lombok.RequiredArgsConstructor;
import otus.spring.dao.QuestionDao;
import otus.spring.domain.Question;
import otus.spring.domain.Student;
import otus.spring.domain.TestResult;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final IOService ioService;

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
        ioService.printLine("Please answer the questions below:");
        ioService.printLine("");
        questionDao.findAll()
                .forEach(question -> processQuestion(question, testResult));
    }

    void processQuestion(Question question, TestResult testResult) {
        ioService.printLine(question.value());

        question.answers()
                .forEach(answer -> ioService.printFormattedLine("\t- %s\n", answer.value()));

        var answer = ioService.readLine();

        testResult.answerQuestion(question, answer);
    }
}
