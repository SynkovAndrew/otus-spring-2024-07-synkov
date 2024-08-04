package otus.spring.service;

import lombok.RequiredArgsConstructor;
import otus.spring.dao.QuestionDao;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printLine("Please answer the questions below:");
        ioService.printLine("");
        questionDao.findAll().forEach(question -> {
            ioService.printLine(question.value());
            question.answers().forEach(answer -> {
                ioService.printFormattedLine("\t- %s\n", answer.value());
            });
        });
        ioService.printLine("");
    }
}
